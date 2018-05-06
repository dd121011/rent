package com.scrats.rent.common.job;

import org.apache.log4j.Logger;
import org.quartz.SchedulerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

@Component
public class MesPlanRunner implements CommandLineRunner {
    private final Logger logger = Logger.getLogger(this.getClass());

    /**
     * 重启之后运行数据库中保存的任务
     * @throws SchedulerException
     */
    @Override
    public void run(String... strings) throws SchedulerException {
        // do something here ...
        logger.info("this class runs before the application is running");
    }

}
