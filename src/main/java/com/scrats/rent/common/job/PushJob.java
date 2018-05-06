package com.scrats.rent.common.job;

import com.scrats.rent.common.weixin.WxPushManager;
import com.scrats.rent.util.DateUtil;
import org.apache.log4j.Logger;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by scrat on 2017/11/30.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
public class PushJob implements Job {
    private final Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    private WxPushManager wxPushManager;


    @Value("${edc.wx.corp.id}")
    private String corpId;

    @Value("${edc.wx.report.url}")
    private String reportUrl;

    private static final String PUSH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=cj#wechat_redirect";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobValue = dataMap.getString("jobKey1");

        logger.info("Job开始时间为:" + DateUtil.getTime(new Date()) + ",值为:" + jobValue);
    }
}


