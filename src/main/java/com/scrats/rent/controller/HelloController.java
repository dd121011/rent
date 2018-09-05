package com.scrats.rent.controller;

import com.scrats.rent.base.service.RedisService;
import com.scrats.rent.common.JsonResult;
import com.scrats.rent.common.exception.BusinessException;
import com.scrats.rent.common.exception.ErrorInfo;
import com.scrats.rent.common.exception.NotAuthorizedException;
import com.scrats.rent.common.job.PushJob;
import lombok.extern.slf4j.Slf4j;
import org.quartz.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @Created with jointstarc.
 * @Email: 262297088@qq.com
 * @Description:
 * @User: lol.
 * @Date: 2018/1/3 18:18.
 */
@Slf4j
@Controller
public class HelloController {

    @Autowired
    private RedisService redisService;

    @Autowired
    private SchedulerFactoryBean schedulerFactoryBean;

    @RequestMapping("/hello")
    public String demo(Map<String, Object> map) {
        map.put("descrip", "Hello, it's a springboot integrate freemarker's demo!!!!");
        map.put("tdate", new Date());
        System.out.println("345678i");
        log.info("this is hello");
        throw new NotAuthorizedException("非法请求");
//        return "hello";
    }

    @RequestMapping("/goHome")
    public String home(Map<String, Object> map) {
        System.out.println("home");
        return "home";
    }

    @RequestMapping("/redisTest")
    @ResponseBody
    public void redisTest(Map<String, Object> map) {
        redisService.set("test", "tt");
        Object reidsValue = redisService.get("test");
        System.out.println(reidsValue);
    }

    @RequestMapping("/redisSessionTest")
    @ResponseBody
    public JsonResult<Map> redisSessionTest(HttpServletRequest request) {
        Map map = new HashMap();
        map.put("sessionId", request.getSession().getId());
        map.put("message", request.getSession().getAttribute("requestUrl"));
//        if(!msgService.checkReportAvaliable(166)){
//            return new JsonResult("推送的报表已经被禁用");
//        }
        return new JsonResult<>(map);
    }

    @RequestMapping("/jobTest")
    @ResponseBody
    public void jobTest(Map<String, Object> map) throws Exception {

        JobDataMap jobDataMap = new JobDataMap();
        jobDataMap.put("jobKey1", "jobValue1");

        Scheduler scheduler = schedulerFactoryBean.getScheduler();

        //构建job信息
        JobDetail jobDetail = JobBuilder.newJob(PushJob.class).setJobData(jobDataMap)
                .withDescription("report").withIdentity("jobClassName", "jobGroupName").build();

        //表达式调度构建器(即任务执行的时间)
        String cronExpression = "0 * * * * ?";
        CronScheduleBuilder scheduleBuilder = CronScheduleBuilder.cronSchedule(cronExpression);

        //按新的cronExpression表达式构建一个新的trigger
        CronTrigger trigger = TriggerBuilder.newTrigger().forJob(jobDetail).withSchedule(scheduleBuilder).withIdentity("jobClassName", "jobGroupName")
                .build();

        try {
            scheduler.scheduleJob(jobDetail, trigger);

        } catch (SchedulerException e) {
            System.out.println("创建定时任务失败" + e);
            throw new Exception("创建定时任务失败");
        }

        // 启动调度器
        scheduler.start();
    }

    @RequestMapping("/BusinessExceptionTest")
    @ResponseBody
    public void ExceptionTest(){
        throw new BusinessException(ErrorInfo.STATUS_CODE_BUSINESS_ERROR,"this is BusinessException");
    }
    @RequestMapping("/AuthorTest")
    @ResponseBody
    public void AuthorTest(){
        throw new NotAuthorizedException("非法请求");
    }
}
