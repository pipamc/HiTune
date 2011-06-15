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
register $chukwaCore
register $chukwaPig
define chukwaLoader org.apache.hadoop.chukwa.ChukwaStorage();
define timePartition_SystemMetrics_$timePeriod org.apache.hadoop.chukwa.TimePartition('$timePeriod');
define seqWriter_SystemMetrics_$timePeriod org.apache.hadoop.chukwa.ChukwaStorage('c_timestamp','c_recordtype', 'c_application', 'c_cluster','c_source' , 'cpu_si%', 'mem_buffers_pcnt', 'tasks_stopped', 'sdb.rkb/s', 'cpu_hi%', 'eth0.rxdrop/s', '%idle', 'eth0.rxbyt/s', 'sdb.wkb/s', '%sys', 'eth1.txpck/s', 'mem_cached_pcnt', 'swap_used_pcnt', 'sdd.%util', 'eth1.rxdrop/s', '%system', '%memused', 'sdc.%util', 'sda.rkb/s', 'sda.wkb/s', 'mem_free', 'ldavg-5', 'tasks_total', 'tasks_zombie', 'sdd.wkb/s', 'sdb.%util', 'tasks_running', 'eth0.txbyt/s', '%nice', 'sdd.rkb/s', 'eth1.txdrop/s', 'sda.%util', 'eth0.rxpck/s', 'mem_total', 'sdc.wkb/s', 'eth0.txdrop/s', 'mem_used', 'mem_shared', 'eth1.txbyt/s', '%iowait', 'kbcached', 'eth1.rxpck/s', 'eth1.txerr/s', 'sdc.rkb/s', 'ldavg-1', 'eth0.txerr/s', 'eth1.rxerr/s', 'eth0.rxerr/s', 'mem_buffers', '%user', 'eth0.txpck/s', 'ldavg-15', 'tasks_sleeping', 'eth0_busy_pcnt', 'eth1_busy_pcnt', 'eth1.rxbyt/s');
A_SystemMetrics_$timePeriod = load '$input' using  chukwaLoader as (ts: long,fields);
B_SystemMetrics_$timePeriod = FOREACH A_SystemMetrics_$timePeriod GENERATE timePartition_SystemMetrics_$timePeriod(ts) as time ,fields#'csource' as g0 , fields#'cpu_si%' as f0, fields#'mem_buffers_pcnt' as f1, fields#'tasks_stopped' as f2, fields#'sdb.rkb/s' as f3, fields#'cpu_hi%' as f4, fields#'eth0.rxdrop/s' as f5, fields#'%idle' as f6, fields#'eth0.rxbyt/s' as f7, fields#'sdb.wkb/s' as f8, fields#'%sys' as f9, fields#'eth1.txpck/s' as f10, fields#'mem_cached_pcnt' as f11, fields#'swap_used_pcnt' as f12, fields#'sdd.%util' as f13, fields#'eth1.rxdrop/s' as f14, fields#'%system' as f15, fields#'%memused' as f16, fields#'sdc.%util' as f17, fields#'sda.rkb/s' as f18, fields#'sda.wkb/s' as f19, fields#'mem_free' as f20, fields#'ldavg-5' as f21, fields#'tasks_total' as f22, fields#'tasks_zombie' as f23, fields#'sdd.wkb/s' as f24, fields#'sdb.%util' as f25, fields#'tasks_running' as f26, fields#'eth0.txbyt/s' as f27, fields#'%nice' as f28, fields#'sdd.rkb/s' as f29, fields#'eth1.txdrop/s' as f30, fields#'sda.%util' as f31, fields#'eth0.rxpck/s' as f32, fields#'mem_total' as f33, fields#'sdc.wkb/s' as f34, fields#'eth0.txdrop/s' as f35, fields#'mem_used' as f36, fields#'mem_shared' as f37, fields#'eth1.txbyt/s' as f38, fields#'%iowait' as f39, fields#'kbcached' as f40, fields#'eth1.rxpck/s' as f41, fields#'eth1.txerr/s' as f42, fields#'sdc.rkb/s' as f43, fields#'ldavg-1' as f44, fields#'eth0.txerr/s' as f45, fields#'eth1.rxerr/s' as f46, fields#'eth0.rxerr/s' as f47, fields#'mem_buffers' as f48, fields#'%user' as f49, fields#'eth0.txpck/s' as f50, fields#'ldavg-15' as f51, fields#'tasks_sleeping' as f52, fields#'eth0_busy_pcnt' as f53, fields#'eth1_busy_pcnt' as f54, fields#'eth1.rxbyt/s' as f55;
C_SystemMetrics_$timePeriod = group B_SystemMetrics_$timePeriod by (time,g0 );
D_SystemMetrics_$timePeriod = FOREACH C_SystemMetrics_$timePeriod generate group.time as ts, '$recType', 'downsampling $timePeriod', '$cluster', group.g0 , AVG(B_SystemMetrics_$timePeriod.f0) as f0, AVG(B_SystemMetrics_$timePeriod.f1) as f1, AVG(B_SystemMetrics_$timePeriod.f2) as f2, AVG(B_SystemMetrics_$timePeriod.f3) as f3, AVG(B_SystemMetrics_$timePeriod.f4) as f4, AVG(B_SystemMetrics_$timePeriod.f5) as f5, AVG(B_SystemMetrics_$timePeriod.f6) as f6, AVG(B_SystemMetrics_$timePeriod.f7) as f7, AVG(B_SystemMetrics_$timePeriod.f8) as f8, AVG(B_SystemMetrics_$timePeriod.f9) as f9, AVG(B_SystemMetrics_$timePeriod.f10) as f10, AVG(B_SystemMetrics_$timePeriod.f11) as f11, AVG(B_SystemMetrics_$timePeriod.f12) as f12, AVG(B_SystemMetrics_$timePeriod.f13) as f13, AVG(B_SystemMetrics_$timePeriod.f14) as f14, AVG(B_SystemMetrics_$timePeriod.f15) as f15, AVG(B_SystemMetrics_$timePeriod.f16) as f16, AVG(B_SystemMetrics_$timePeriod.f17) as f17, AVG(B_SystemMetrics_$timePeriod.f18) as f18, AVG(B_SystemMetrics_$timePeriod.f19) as f19, AVG(B_SystemMetrics_$timePeriod.f20) as f20, AVG(B_SystemMetrics_$timePeriod.f21) as f21, AVG(B_SystemMetrics_$timePeriod.f22) as f22, AVG(B_SystemMetrics_$timePeriod.f23) as f23, AVG(B_SystemMetrics_$timePeriod.f24) as f24, AVG(B_SystemMetrics_$timePeriod.f25) as f25, AVG(B_SystemMetrics_$timePeriod.f26) as f26, AVG(B_SystemMetrics_$timePeriod.f27) as f27, AVG(B_SystemMetrics_$timePeriod.f28) as f28, AVG(B_SystemMetrics_$timePeriod.f29) as f29, AVG(B_SystemMetrics_$timePeriod.f30) as f30, AVG(B_SystemMetrics_$timePeriod.f31) as f31, AVG(B_SystemMetrics_$timePeriod.f32) as f32, AVG(B_SystemMetrics_$timePeriod.f33) as f33, AVG(B_SystemMetrics_$timePeriod.f34) as f34, AVG(B_SystemMetrics_$timePeriod.f35) as f35, AVG(B_SystemMetrics_$timePeriod.f36) as f36, AVG(B_SystemMetrics_$timePeriod.f37) as f37, AVG(B_SystemMetrics_$timePeriod.f38) as f38, AVG(B_SystemMetrics_$timePeriod.f39) as f39, AVG(B_SystemMetrics_$timePeriod.f40) as f40, AVG(B_SystemMetrics_$timePeriod.f41) as f41, AVG(B_SystemMetrics_$timePeriod.f42) as f42, AVG(B_SystemMetrics_$timePeriod.f43) as f43, AVG(B_SystemMetrics_$timePeriod.f44) as f44, AVG(B_SystemMetrics_$timePeriod.f45) as f45, AVG(B_SystemMetrics_$timePeriod.f46) as f46, AVG(B_SystemMetrics_$timePeriod.f47) as f47, AVG(B_SystemMetrics_$timePeriod.f48) as f48, AVG(B_SystemMetrics_$timePeriod.f49) as f49, AVG(B_SystemMetrics_$timePeriod.f50) as f50, AVG(B_SystemMetrics_$timePeriod.f51) as f51, AVG(B_SystemMetrics_$timePeriod.f52) as f52, AVG(B_SystemMetrics_$timePeriod.f53) as f53, AVG(B_SystemMetrics_$timePeriod.f54) as f54, AVG(B_SystemMetrics_$timePeriod.f55) as f55;
-- describe D_SystemMetrics_$timePeriod;
-- dump D_SystemMetrics_$timePeriod;
store D_SystemMetrics_$timePeriod into '$output' using seqWriter_SystemMetrics_$timePeriod;
