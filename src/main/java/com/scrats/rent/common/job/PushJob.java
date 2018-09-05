package com.scrats.rent.common.job;

import com.scrats.rent.util.DateUtils;
import com.scrats.rent.util.weixin.qyapi.WxPushManager;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;

import java.util.Date;

/**
 * Created by scrat on 2017/11/30.
 */
@PersistJobDataAfterExecution
@DisallowConcurrentExecution
@Slf4j
public class PushJob implements Job {

    @Autowired
    private WxPushManager wxPushManager;


    @Value("${wx.qy.corp.id}")
    private String corpId;

    @Value("${wx.qy.report.url}")
    private String reportUrl;

    private static final String PUSH_URL = "https://open.weixin.qq.com/connect/oauth2/authorize?appid=%s&redirect_uri=%s&response_type=code&scope=snsapi_base&state=cj#wechat_redirect";

    @Override
    public void execute(JobExecutionContext jobExecutionContext) throws JobExecutionException {

        JobDataMap dataMap = jobExecutionContext.getJobDetail().getJobDataMap();
        String jobValue = dataMap.getString("jobKey1");

        log.info("Job开始时间为:" + DateUtils.getTime(new Date()) + ",值为:" + jobValue);
    }
}


