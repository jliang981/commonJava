package com.step.jliang.quartz.job;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import java.util.Date;

public class HelloJob implements Job {
    // 每个job都只有一个execute方法。
    public void execute(JobExecutionContext context)
            throws JobExecutionException {
        System.out.println("Hello, Quartz! - executing its JOB at " +
                new Date() + " by ");
    }

}
