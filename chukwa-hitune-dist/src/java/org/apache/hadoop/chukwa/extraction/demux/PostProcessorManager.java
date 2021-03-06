/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.hadoop.chukwa.extraction.demux;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;

import org.apache.hadoop.chukwa.conf.ChukwaConfiguration;
import org.apache.hadoop.chukwa.dataloader.DataLoaderFactory;
import org.apache.hadoop.chukwa.extraction.CHUKWA_CONSTANT;
import org.apache.hadoop.chukwa.util.DaemonWatcher;
import org.apache.hadoop.chukwa.util.ExceptionUtil;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.fs.PathFilter;
import org.apache.log4j.Logger;
import org.apache.hadoop.chukwa.util.HierarchyDataType;

public class PostProcessorManager implements CHUKWA_CONSTANT{
  static Logger log = Logger.getLogger(PostProcessorManager.class);
  
  protected static HashMap<String, String> dataSources = new HashMap<String, String>();
  public static int errorCount = 0;
  
  protected int ERROR_SLEEP_TIME = 60;
  protected ChukwaConfiguration conf = null;
  protected FileSystem fs = null;
  protected volatile boolean isRunning = true;
  
  final private static PathFilter POST_PROCESS_DEMUX_DIR_FILTER = new PathFilter() {
    public boolean accept(Path file) {
      return ( file.getName().startsWith("demuxOutputDir") || file.getName().startsWith("pigOutputDir"));
    }     
  };

  
  public PostProcessorManager() throws Exception {
    this.conf = new ChukwaConfiguration();
    init();
  }
  
  public PostProcessorManager(ChukwaConfiguration conf) throws Exception {
    this.conf = conf;
    init();
  }
  
  protected void init() throws IOException, URISyntaxException {
    String fsName = conf.get(HDFS_DEFAULT_NAME_FIELD);
    fs = FileSystem.get(new URI(fsName), conf);
  }
  
  public static void main(String[] args) throws Exception {
 
    DaemonWatcher.createInstance("PostProcessorManager");
    

    
    PostProcessorManager postProcessorManager = new PostProcessorManager();
    postProcessorManager.start();
  }
  
  public void shutdown() {
    this.isRunning = false;
  }
  
  public void start() {
    
    String chukwaRootDir = conf.get(CHUKWA_ROOT_DIR_FIELD, "/chukwa/");
    if ( ! chukwaRootDir.endsWith("/") ) {
      chukwaRootDir += "/";
    }
    log.info("chukwaRootDir:" + chukwaRootDir);
    
    String postProcessDir = conf.get(CHUKWA_POST_PROCESS_DIR_FIELD, chukwaRootDir +DEFAULT_CHUKWA_POSTPROCESS_DIR_NAME);
    if ( ! postProcessDir.endsWith("/") ) {
      postProcessDir += "/";
    }
    
    String chukwaRootReposDir = conf.get(CHUKWA_ROOT_REPOS_DIR_FIELD, chukwaRootDir +DEFAULT_REPOS_DIR_NAME);
    if ( ! chukwaRootReposDir.endsWith("/") ) {
      chukwaRootReposDir += "/";
    }
 
    String chukwaPostProcessInErrorDir = conf.get(CHUKWA_POSTPROCESS_IN_ERROR_DIR_FIELD, chukwaRootDir +DEFAULT_POSTPROCESS_IN_ERROR_DIR_NAME);
    if ( ! chukwaPostProcessInErrorDir.endsWith("/") ) {
      chukwaPostProcessInErrorDir += "/";
    }
 
    
    dataSources = new HashMap<String, String>();
    Path postProcessDirectory = new Path(postProcessDir);
    while (isRunning) {
      
      if (errorCount >= 4 ) {
        // it's better to exit, Watchdog will re-strat it
        log.warn("Too many error - bail out!");
        DaemonWatcher.bailout(-1);
      }
      
      try {
        FileStatus[] demuxOutputDirs = fs.listStatus(postProcessDirectory,POST_PROCESS_DEMUX_DIR_FILTER);
        List<String> directories = new ArrayList<String>();
        for (FileStatus fstatus : demuxOutputDirs) {
          directories.add(fstatus.getPath().getName());
        }
        
        if (demuxOutputDirs.length == 0) {
          try { Thread.sleep(10*1000);} catch(InterruptedException e) { /* do nothing*/}
          continue;
        }
        
        Collections.sort(directories);
        
        String directoryToBeProcessed = null;
        long start = 0;
        
        for(String directory : directories) {
          directoryToBeProcessed = postProcessDirectory + "/"+ directory;
          
          log.info("PostProcess Start, directory:" + directoryToBeProcessed);
          start = System.currentTimeMillis();
         
          try {
            if ( processDemuxPigOutput(directoryToBeProcessed) == true) {
              if (movetoMainRepository(directoryToBeProcessed,chukwaRootReposDir) == true) {
                deleteDirectory(directoryToBeProcessed);
                log.info("PostProcess Stop, directory:" + directoryToBeProcessed);
                log.info("processDemuxOutput Duration:" + (System.currentTimeMillis() - start));
                continue;
              }
            }
            // if we are here it's because something bad happen during processing
            log.warn("Error in processDemuxOutput for :" + directoryToBeProcessed);
            moveToInErrorDirectory(directoryToBeProcessed,directory,chukwaPostProcessInErrorDir); 
          } catch (Throwable e) {
            log.warn("Error in processDemuxOutput:" ,e);
          } 
        }
       
      } catch (Throwable e) {
        errorCount ++;
        log.warn(e);
        try { Thread.sleep(ERROR_SLEEP_TIME * 1000); } 
        catch (InterruptedException e1) {/*do nothing*/ }
      }
    }
  }
  
  public boolean processDemuxPigOutput(String directory) throws IOException {
    long start = System.currentTimeMillis();
    try {
      String[] classes = conf.get(POST_DEMUX_DATA_LOADER).split(",");
      for(String dataLoaderName : classes) {
        Class<? extends DataLoaderFactory> dl = (Class<? extends DataLoaderFactory>) Class.forName(dataLoaderName);
        java.lang.reflect.Constructor<? extends DataLoaderFactory> c =
            dl.getConstructor();
        DataLoaderFactory dataloader = c.newInstance();
        
          //DataLoaderFactory dataLoader = (DataLoaderFactory) Class.
          //    forName(dataLoaderName).getConstructor().newInstance();
        log.info(dataLoaderName+" processing: "+directory);
        StringBuilder dirSearch = new StringBuilder();
        dirSearch.append(directory);
        dirSearch.append("/*/*");
        log.debug("dirSearch: " + dirSearch);
        Path demuxDir = new Path(dirSearch.toString());
        PathFilter filter = new PathFilter()
        {public boolean accept(Path file) {
          return file.getName().endsWith(".evt");
        }  };
        List<FileStatus> eventfiles = HierarchyDataType.globStatus(fs, demuxDir,filter,true);
        FileStatus[] events = eventfiles.toArray(new FileStatus[eventfiles.size()]);
        dataloader.load(conf, fs, events);
      }
    } catch(Exception e) {
      log.error(ExceptionUtil.getStackTrace(e));
      return false;
    }
    log.info("loadData Duration:" + (System.currentTimeMillis() - start));
    return true;
  }
  
  public boolean movetoMainRepository(String sourceDirectory,String repoRootDirectory) throws Exception {
    String[] args = {sourceDirectory,repoRootDirectory};
    long start = System.currentTimeMillis();
    MoveToRepository.main(args);
    log.info("movetoMainRepository Duration:" + (System.currentTimeMillis() - start));
    return true;
  }
  
  public boolean moveToInErrorDirectory(String sourceDirectory,String dirName,String inErrorDirectory) throws Exception {
    Path inErrorDir = new Path(inErrorDirectory);
    if (!fs.exists(inErrorDir)) {
      fs.mkdirs(inErrorDir);
    }
    
    if (inErrorDirectory.endsWith("/")) {
      inErrorDirectory += "/";
    }
    String finalInErrorDirectory = inErrorDirectory + dirName + "_" + System.currentTimeMillis();
    fs.rename(new Path(sourceDirectory), new Path(finalInErrorDirectory));
    log.warn("Error in postProcess  :" + sourceDirectory + " has been moved to:" + finalInErrorDirectory);
    return true;
  }
  
  public boolean deleteDirectory(String directory) throws IOException {
   return fs.delete(new Path(directory), true);
  }
  
}
