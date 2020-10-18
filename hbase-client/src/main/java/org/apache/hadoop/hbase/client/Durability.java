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

package org.apache.hadoop.hbase.client;

import org.apache.yetus.audience.InterfaceAudience;

/**
 * Enum describing the durability guarantees for tables and {@link Mutation}s
 * Note that the items must be sorted in order of increasing durability
 */
@InterfaceAudience.Public
public enum Durability {
  /* Developer note: Do not rename the enum field names. They are serialized in HTableDescriptor */
  /**
   * 如果用户没有指定持久化登记，默认HBase使用SYNC_WAL等级处久化数据
   * If this is for tables durability, use HBase's global default value (SYNC_WAL).
   * Otherwise, if this is for mutation, use the table's default setting to determine durability.
   * This must remain the first option.
   */
  USE_DEFAULT,
  /**
   * 只写缓存，不写HLog日志
   * 因为只写内存，因此这种方式可以极大的提升写入性能，但是数据有丢失的风险
   * Do not write the Mutation to the WAL
   */
  SKIP_WAL,
  /**
   * 异步将数据写入HLog日志中
   * Write the Mutation to the WAL asynchronously
   */
  ASYNC_WAL,
  /**
   * 同步将数据写入日志文件中
   * Write the Mutation to the WAL synchronously.
   * The data is flushed to the filesystem implementation, but not necessarily to disk.
   * For HDFS this will flush the data to the designated number of DataNodes.
   * See <a href="https://issues.apache.org/jira/browse/HADOOP-6313">HADOOP-6313</a>
   */
  SYNC_WAL,
  /**
   * 同步将数据写入日志文件并强制落盘
   * Write the Mutation to the WAL synchronously and force the entries to disk.
   * (Note: this is currently not supported and will behave identical to {@link #SYNC_WAL})
   * See <a href="https://issues.apache.org/jira/browse/HADOOP-6313">HADOOP-6313</a>
   */
  FSYNC_WAL
}
