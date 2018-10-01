/*
 *  Copyright 2009-2016 Weibo, Inc.
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.weibo.api.motan.filter;

import com.weibo.api.motan.common.MotanConstants;
import com.weibo.api.motan.core.extension.SpiMeta;
import com.weibo.api.motan.rpc.*;
import com.weibo.api.motan.util.ExceptionUtil;
import com.weibo.api.motan.util.MotanFrameworkUtil;
import com.weibo.api.motan.util.StatsUtil;
import com.weibo.api.motan.util.StatsUtil.AccessStatus;

/**
 * @author maijunsheng
 * @version 创建时间：2013-6-14
 */
@SpiMeta(name = "statistic")
public class AccessStatisticFilter implements Filter {
    protected static Application RPC_SERVICES = new Application(ApplicationInfo.STATISTIC, "rpc_service");

    @Override
    public Response filter(Caller<?> caller, Request request) {
        long start = System.currentTimeMillis();
        AccessStatus accessStatus = AccessStatus.NORMAL;
        boolean specialException = true;
        long bizProcessTime = 0;

        try {
            Response response = caller.call(request);

            if (response == null) {
                accessStatus = AccessStatus.OTHER_EXCEPTION;
            } else {
                if (response.getException() != null) {
                    if (ExceptionUtil.isBizException(response.getException())) {
                        accessStatus = AccessStatus.BIZ_EXCEPTION;
                    } else {
                        accessStatus = AccessStatus.OTHER_EXCEPTION;
                    }
                }

                specialException = false;
                bizProcessTime = response.getProcessTime();
            }

            return response;
        } finally {
            long end = System.currentTimeMillis();

            if (specialException) {
                accessStatus = AccessStatus.OTHER_EXCEPTION;
                bizProcessTime = end - start;
            }

            Application application;
            String statName =
                    caller.getUrl().getProtocol() + MotanConstants.PROTOCOL_SEPARATOR + MotanFrameworkUtil.getGroupMethodString(request);
            if (caller instanceof Provider) {
                application = RPC_SERVICES;
                StatsUtil.accessStatistic(statName, application, end, end - start, bizProcessTime, accessStatus);
            }
            application = ApplicationInfo.getApplication(caller.getUrl());
            StatsUtil.accessStatistic(statName, application, end, end - start, bizProcessTime, accessStatus);

        }
    }
}
