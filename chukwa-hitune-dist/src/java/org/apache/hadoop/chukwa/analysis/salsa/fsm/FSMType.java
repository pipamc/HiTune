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

package org.apache.hadoop.chukwa.analysis.salsa.fsm;

public class FSMType {
	public static final int MAPREDUCE_FSM = 0;
	public static final int FILESYSTEM_FSM = 1;
	public static final int MAPREDUCE_FSM_INCOMPLETE = 2;
	public static final int FILESYSTEM_FSM_INCOMPLETE = 3;
	public static final String [] NAMES = { "MAPREDUCE_FSM", "FILESYSTEM_FSM", "MAPREDUCE_FSM_INCOMPLETE", "FILESYSTEM_FSM_INCOMPLETE" };
	public FSMType() { this.val = 0; }
	public FSMType(int newval) { this.val = newval; }
	public int val;
	public String toString() { assert(this.val < NAMES.length && this.val >= 0); return new String(NAMES[this.val]); }		
}