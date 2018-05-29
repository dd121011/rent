package com.scrats.rent.common.jpush;

import cn.jiguang.common.ClientConfig;
import cn.jpush.api.JPushClient;
import cn.jpush.api.push.PushResult;
import cn.jpush.api.push.model.Options;
import cn.jpush.api.push.model.Platform;
import cn.jpush.api.push.model.PushPayload;
import cn.jpush.api.push.model.audience.Audience;
import cn.jpush.api.push.model.notification.AndroidNotification;
import cn.jpush.api.push.model.notification.IosNotification;
import cn.jpush.api.push.model.notification.Notification;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by scrat on 2017/12/1.
 */
@Repository
public class JPushManager {
    private final Logger logger = Logger.getLogger(this.getClass());
    @Value("${edc.jpush.key}")
    private String APP_KEY;
    @Value("${edc.jpush.secret}")
    private String MASTER_SECRET;

    public boolean pushCard(String title, String description, String cardContent, String... regIds) {
        return sendPush(title, description, "card", cardContent, regIds);
    }

    public boolean pushReport(String title, String description, String planId, String... regIds) {
        return sendPush(title, description, "report", planId, regIds);
    }

    private boolean sendPush(String title, String description, String type, String extraContent, String... regIds) {
        ClientConfig clientConfig = ClientConfig.getInstance();
        final JPushClient jpushClient = new JPushClient(MASTER_SECRET, APP_KEY, null, clientConfig);
        Map<String, String> extras = new HashMap<String, String>();
        extras.put("type", type);
        extras.put("data", extraContent);
        final PushPayload payload = buildPushPayload(title, description, extras, regIds);
        try {
            PushResult result = jpushClient.sendPush(payload);
            logger.info(result);
            return result.isResultOK();
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }

    private PushPayload buildPushPayload(
            String title, String description, Map<String, String> extras, String... regIds) {
        return PushPayload.newBuilder()
                .setPlatform(Platform.android_ios())
                .setAudience(Audience.registrationId(regIds))
                .setNotification(Notification.newBuilder()
                        .setAlert(description)
                        .addPlatformNotification(
                                AndroidNotification.newBuilder()
                                        .setTitle(title)
                                        .addExtras(extras)
                                        .build()
                        )
                        .addPlatformNotification(
                                IosNotification.newBuilder()
                                        .incrBadge(1)
                                        .addExtras(extras)
                                        .build()
                        )

                        .build())
                .setOptions(Options.newBuilder()
                        .setApnsProduction(true)
                        .build())
                .build();

    }
}
