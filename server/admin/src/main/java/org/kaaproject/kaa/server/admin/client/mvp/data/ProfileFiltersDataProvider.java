/*
 * Copyright 2014 CyberVision, Inc.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.kaaproject.kaa.server.admin.client.mvp.data;

import java.util.List;

import org.kaaproject.avro.ui.gwt.client.widget.grid.AbstractGrid;
import org.kaaproject.kaa.common.dto.ProfileFilterDto;
import org.kaaproject.kaa.common.dto.StructureRecordDto;
import org.kaaproject.kaa.server.admin.client.KaaAdmin;
import org.kaaproject.kaa.server.admin.client.mvp.activity.grid.AbstractDataProvider;
import org.kaaproject.kaa.server.admin.client.util.HasErrorMessage;

import com.google.gwt.user.client.rpc.AsyncCallback;

public class ProfileFiltersDataProvider extends AbstractDataProvider<StructureRecordDto<ProfileFilterDto>>{

    private String endpointGroupId;
    private boolean includeDeprecated = false;

    public ProfileFiltersDataProvider(AbstractGrid<StructureRecordDto<ProfileFilterDto>,?> dataGrid,
                                      HasErrorMessage hasErrorMessage,
                                      String endpointGroupId, boolean includeDeprecated) {
        super(dataGrid, hasErrorMessage, false);
        this.endpointGroupId = endpointGroupId;
        this.includeDeprecated = includeDeprecated;
        addDataDisplay();
    }

    public void setIncludeDeprecated(boolean includeDeprecated) {
        this.includeDeprecated = includeDeprecated;
    }

    @Override
    protected void loadData(final LoadCallback callback) {
        KaaAdmin.getDataSource().loadProfileFilterRecords(endpointGroupId, includeDeprecated, new AsyncCallback<List<StructureRecordDto<ProfileFilterDto>>>() {
            @Override
            public void onFailure(Throwable caught) {
                callback.onFailure(caught);

            }
            @Override
            public void onSuccess(List<StructureRecordDto<ProfileFilterDto>> result) {
                callback.onSuccess(result);
            }
        });
    }

}

