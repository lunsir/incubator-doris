// Copyright (c) 2017, Baidu.com, Inc. All Rights Reserved

// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//   http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing,
// software distributed under the License is distributed on an
// "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
// KIND, either express or implied.  See the License for the
// specific language governing permissions and limitations
// under the License.

package com.baidu.palo.transaction;

import com.baidu.palo.common.io.Writable;
import com.baidu.palo.thrift.TTabletCommitInfo;

import com.google.common.collect.Lists;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;
import java.util.List;

public class TabletCommitInfo implements Writable {

    private long tabletId;
    private long backendId;

    public TabletCommitInfo(long tabletId, long backendId) {
        super();
        this.tabletId = tabletId;
        this.backendId = backendId;
    }

    public long getTabletId() {
        return tabletId;
    }

    public long getBackendId() {
        return backendId;
    }

    public static List<TabletCommitInfo> fromThrift(List<TTabletCommitInfo> tTabletCommitInfos) {
        List<TabletCommitInfo> commitInfos = Lists.newArrayList();
        for (TTabletCommitInfo tTabletCommitInfo : tTabletCommitInfos) {
            commitInfos.add(new TabletCommitInfo(tTabletCommitInfo.getTabletId(), tTabletCommitInfo.getBackendId()));
        }
        return commitInfos;
    }

    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(tabletId);
        out.writeLong(backendId);
    }

    @Override
    public void readFields(DataInput in) throws IOException {
        tabletId = in.readLong();
        backendId = in.readLong();
    }
}