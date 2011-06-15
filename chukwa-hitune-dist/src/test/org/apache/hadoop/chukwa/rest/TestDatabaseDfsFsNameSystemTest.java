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

package org.apache.hadoop.chukwa.rest;

import junit.framework.TestCase;

import java.util.*;
import java.sql.*;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.hadoop.chukwa.database.DatabaseConfig;
import org.apache.hadoop.chukwa.database.Macro;
import org.apache.hadoop.chukwa.util.DatabaseWriter;

public class TestDatabaseDfsFsNameSystemTest extends DatabaseRestServerSetup {
    /* testing setup */
    public TestDatabaseDfsFsNameSystemTest() {
	super();
	this.tableName="dfs_fsnamesystem";
	this.insertString="insert into [dfs_fsnamesystem] ("+
	    "timestamp, host, blocks_total"+
	    ") values (\""+this.dateString+"\", \"host\", 1)";
	this.deleteString="delete from [dfs_fsnamesystem] where "+
	    "timestamp=\""+this.dateString+"\" and host=\"host\" ";
    }

    /* run the test */
    public void test() { 
	// test xml
	String string = resource.path("dfs_fsnamesystem/timestamp/"+time.getTime()).accept("application/xml").get(String.class);
	checkXML(string);

	string = resource.path("dfs_fsnamesystem/timestamp/"+time.getTime()).accept("application/json").get(String.class);
	checkJson(string);

	string = resource.path("dfs_fsnamesystem/timestamp/"+time.getTime()).accept("text/csv").get(String.class);
	checkCsv(string);
    }
}
