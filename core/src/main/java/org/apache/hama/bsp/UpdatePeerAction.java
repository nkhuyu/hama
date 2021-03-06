/**
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
package org.apache.hama.bsp;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

import org.apache.hadoop.io.Text;

/**
 * Represents a directive from the {@link org.apache.hama.bsp.BSPMaster} to the
 * {@link org.apache.hama.bsp.GroomServer} to kill a task.
 */
class UpdatePeerAction extends GroomServerAction {
  TaskAttemptID taskId;
  TaskAttemptID peerTaskId;
  Text groomName;

  public UpdatePeerAction() {
    super(ActionType.UPDATE_PEER);
    taskId = new TaskAttemptID();
    groomName = new Text("");
  }

  public UpdatePeerAction(TaskAttemptID taskId, TaskAttemptID peerTaskId,
      String groom) {
    super(ActionType.UPDATE_PEER);
    this.taskId = taskId;
    this.peerTaskId = peerTaskId;
    this.groomName = new Text(groom);
  }

  public TaskAttemptID getTaskID() {
    return taskId;
  }

  public TaskAttemptID getPeerTaskID() {
    return peerTaskId;
  }

  public String getGroomName() {
    return groomName.toString();
  }

  @Override
  public void write(DataOutput out) throws IOException {
    taskId.write(out);
    peerTaskId.write(out);
    groomName.write(out);
  }

  @Override
  public void readFields(DataInput in) throws IOException {
    taskId.readFields(in);
    peerTaskId.readFields(in);
    groomName.readFields(in);
  }
}
