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

package org.apache.hadoop.chukwa.rest.objects;

// Generated May 28, 2009 3:39:53 PM by Hibernate Tools 3.2.4.GA

import java.sql.Timestamp;

/**
 * MrJob generated by hbm2java
 */
public class MrJob implements java.io.Serializable {
    static final long serialVersionUID = 5160319783805070798L;

    private String jobId;
    private String user;
    private String queue;
    private String status;
    private Timestamp submitTime;
    private Timestamp launchTime;
    private Timestamp finishTime;
    private Long hdfsBytesRead;
    private Long hdfsBytesWritten;
    private Long localBytesRead;
    private Long localBytesWritten;
    private Long launchedMapTasks;
    private Long launchedReduceTasks;
    private Long dataLocalMapTasks;
    private Long dataLocalReduceTasks;
    private Long mapInputBytes;
    private Long mapOutputBytes;
    private Long mapInputRecords;
    private Long mapOutputRecords;
    private Long combineInputRecords;
    private Long combineOutputRecords;
    private Long spilledRecords;
    private Long reduceInputGroups;
    private Long reduceOutputGroups;
    private Long reduceInputRecords;
    private Long reduceOutputRecords;
    private String jobconf;
    private Long finishedMaps;
    private Long finishedReduces;
    private Long failedMaps;
    private Long failedReduces;
    private Long totalMaps;
    private Long totalReduces;
    private Long reduceShuffleBytes;

    public MrJob() {
    }

    public MrJob(String jobId, Timestamp submitTime, Timestamp launchTime,
		 Timestamp finishTime) {
	this.jobId = jobId;
	this.submitTime = submitTime;
	this.launchTime = launchTime;
	this.finishTime = finishTime;
    }

    public MrJob(String jobId, String user, String queue,
		 String status, Timestamp submitTime, Timestamp launchTime, Timestamp finishTime,
		 Long hdfsBytesRead, Long hdfsBytesWritten, Long localBytesRead,
		 Long localBytesWritten, Long launchedMapTasks,
		 Long launchedReduceTasks, Long dataLocalMapTasks,
		 Long dataLocalReduceTasks, Long mapInputBytes, Long mapOutputBytes,
		 Long mapInputRecords, Long mapOutputRecords,
		 Long combineInputRecords, Long combineOutputRecords,
		 Long spilledRecords, Long reduceInputGroups,
		 Long reduceOutputGroups, Long reduceInputRecords,
		 Long reduceOutputRecords, String jobconf, Long finishedMaps,
		 Long finishedReduces, Long failedMaps, Long failedReduces,
		 Long totalMaps, Long totalReduces, Long reduceShuffleBytes) {
	this.jobId = jobId;
	this.user = user;
	this.queue = queue;
	this.status = status;
	this.submitTime = submitTime;
	this.launchTime = launchTime;
	this.finishTime = finishTime;
	this.hdfsBytesRead = hdfsBytesRead;
	this.hdfsBytesWritten = hdfsBytesWritten;
	this.localBytesRead = localBytesRead;
	this.localBytesWritten = localBytesWritten;
	this.launchedMapTasks = launchedMapTasks;
	this.launchedReduceTasks = launchedReduceTasks;
	this.dataLocalMapTasks = dataLocalMapTasks;
	this.dataLocalReduceTasks = dataLocalReduceTasks;
	this.mapInputBytes = mapInputBytes;
	this.mapOutputBytes = mapOutputBytes;
	this.mapInputRecords = mapInputRecords;
	this.mapOutputRecords = mapOutputRecords;
	this.combineInputRecords = combineInputRecords;
	this.combineOutputRecords = combineOutputRecords;
	this.spilledRecords = spilledRecords;
	this.reduceInputGroups = reduceInputGroups;
	this.reduceOutputGroups = reduceOutputGroups;
	this.reduceInputRecords = reduceInputRecords;
	this.reduceOutputRecords = reduceOutputRecords;
	this.jobconf = jobconf;
	this.finishedMaps = finishedMaps;
	this.finishedReduces = finishedReduces;
	this.failedMaps = failedMaps;
	this.failedReduces = failedReduces;
	this.totalMaps = totalMaps;
	this.totalReduces = totalReduces;
	this.reduceShuffleBytes = reduceShuffleBytes;
    }

    public String getJobId() {
	return this.jobId;
    }

    public void setJobId(String jobId) {
	this.jobId = jobId;
    }

    public String getUser() {
	return this.user;
    }

    public void setUser(String user) {
	this.user = user;
    }

    public String getQueue() {
	return this.queue;
    }

    public void setQueue(String queue) {
	this.queue = queue;
    }

    public String getStatus() {
	return this.status;
    }

    public void setStatus(String status) {
	this.status = status;
    }

    public Timestamp getSubmitTime() {
	return this.submitTime;
    }

    public void setSubmitTime(Timestamp submitTime) {
	this.submitTime = submitTime;
    }

    public Timestamp getLaunchTime() {
	return this.launchTime;
    }

    public void setLaunchTime(Timestamp launchTime) {
	this.launchTime = launchTime;
    }

    public Timestamp getFinishTime() {
	return this.finishTime;
    }

    public void setFinishTime(Timestamp finishTime) {
	this.finishTime = finishTime;
    }

    public Long getHdfsBytesRead() {
	return this.hdfsBytesRead;
    }

    public void setHdfsBytesRead(Long hdfsBytesRead) {
	this.hdfsBytesRead = hdfsBytesRead;
    }

    public Long getHdfsBytesWritten() {
	return this.hdfsBytesWritten;
    }

    public void setHdfsBytesWritten(Long hdfsBytesWritten) {
	this.hdfsBytesWritten = hdfsBytesWritten;
    }

    public Long getLocalBytesRead() {
	return this.localBytesRead;
    }

    public void setLocalBytesRead(Long localBytesRead) {
	this.localBytesRead = localBytesRead;
    }

    public Long getLocalBytesWritten() {
	return this.localBytesWritten;
    }

    public void setLocalBytesWritten(Long localBytesWritten) {
	this.localBytesWritten = localBytesWritten;
    }

    public Long getLaunchedMapTasks() {
	return this.launchedMapTasks;
    }

    public void setLaunchedMapTasks(Long launchedMapTasks) {
	this.launchedMapTasks = launchedMapTasks;
    }

    public Long getLaunchedReduceTasks() {
	return this.launchedReduceTasks;
    }

    public void setLaunchedReduceTasks(Long launchedReduceTasks) {
	this.launchedReduceTasks = launchedReduceTasks;
    }

    public Long getDataLocalMapTasks() {
	return this.dataLocalMapTasks;
    }

    public void setDataLocalMapTasks(Long dataLocalMapTasks) {
	this.dataLocalMapTasks = dataLocalMapTasks;
    }

    public Long getDataLocalReduceTasks() {
	return this.dataLocalReduceTasks;
    }

    public void setDataLocalReduceTasks(Long dataLocalReduceTasks) {
	this.dataLocalReduceTasks = dataLocalReduceTasks;
    }

    public Long getMapInputBytes() {
	return this.mapInputBytes;
    }

    public void setMapInputBytes(Long mapInputBytes) {
	this.mapInputBytes = mapInputBytes;
    }

    public Long getMapOutputBytes() {
	return this.mapOutputBytes;
    }

    public void setMapOutputBytes(Long mapOutputBytes) {
	this.mapOutputBytes = mapOutputBytes;
    }

    public Long getMapInputRecords() {
	return this.mapInputRecords;
    }

    public void setMapInputRecords(Long mapInputRecords) {
	this.mapInputRecords = mapInputRecords;
    }

    public Long getMapOutputRecords() {
	return this.mapOutputRecords;
    }

    public void setMapOutputRecords(Long mapOutputRecords) {
	this.mapOutputRecords = mapOutputRecords;
    }

    public Long getCombineInputRecords() {
	return this.combineInputRecords;
    }

    public void setCombineInputRecords(Long combineInputRecords) {
	this.combineInputRecords = combineInputRecords;
    }

    public Long getCombineOutputRecords() {
	return this.combineOutputRecords;
    }

    public void setCombineOutputRecords(Long combineOutputRecords) {
	this.combineOutputRecords = combineOutputRecords;
    }

    public Long getSpilledRecords() {
	return this.spilledRecords;
    }

    public void setSpilledRecords(Long spilledRecords) {
	this.spilledRecords = spilledRecords;
    }

    public Long getReduceInputGroups() {
	return this.reduceInputGroups;
    }

    public void setReduceInputGroups(Long reduceInputGroups) {
	this.reduceInputGroups = reduceInputGroups;
    }

    public Long getReduceOutputGroups() {
	return this.reduceOutputGroups;
    }

    public void setReduceOutputGroups(Long reduceOutputGroups) {
	this.reduceOutputGroups = reduceOutputGroups;
    }

    public Long getReduceInputRecords() {
	return this.reduceInputRecords;
    }

    public void setReduceInputRecords(Long reduceInputRecords) {
	this.reduceInputRecords = reduceInputRecords;
    }

    public Long getReduceOutputRecords() {
	return this.reduceOutputRecords;
    }

    public void setReduceOutputRecords(Long reduceOutputRecords) {
	this.reduceOutputRecords = reduceOutputRecords;
    }

    public String getJobconf() {
	return this.jobconf;
    }

    public void setJobconf(String jobconf) {
	this.jobconf = jobconf;
    }

    public Long getFinishedMaps() {
	return this.finishedMaps;
    }

    public void setFinishedMaps(Long finishedMaps) {
	this.finishedMaps = finishedMaps;
    }

    public Long getFinishedReduces() {
	return this.finishedReduces;
    }

    public void setFinishedReduces(Long finishedReduces) {
	this.finishedReduces = finishedReduces;
    }

    public Long getFailedMaps() {
	return this.failedMaps;
    }

    public void setFailedMaps(Long failedMaps) {
	this.failedMaps = failedMaps;
    }

    public Long getFailedReduces() {
	return this.failedReduces;
    }

    public void setFailedReduces(Long failedReduces) {
	this.failedReduces = failedReduces;
    }

    public Long getTotalMaps() {
	return this.totalMaps;
    }

    public void setTotalMaps(Long totalMaps) {
	this.totalMaps = totalMaps;
    }

    public Long getTotalReduces() {
	return this.totalReduces;
    }

    public void setTotalReduces(Long totalReduces) {
	this.totalReduces = totalReduces;
    }

    public Long getReduceShuffleBytes() {
	return this.reduceShuffleBytes;
    }

    public void setReduceShuffleBytes(Long reduceShuffleBytes) {
	this.reduceShuffleBytes = reduceShuffleBytes;
    }

}
