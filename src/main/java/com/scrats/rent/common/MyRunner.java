package com.scrats.rent.common;

import lombok.extern.slf4j.Slf4j;
import org.quartz.SchedulerException;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class MyRunner implements CommandLineRunner {
    /**
     * 重启之后运行数据库中保存的任务
     * @throws SchedulerException
     */
    @Override
    public void run(String... strings) throws SchedulerException {
        // do something here ...
        log.info("this class runs before the application is running");
    }

}
