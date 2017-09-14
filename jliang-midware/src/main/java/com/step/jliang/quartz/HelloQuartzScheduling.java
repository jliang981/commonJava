package com.step.jliang.quartz;

import com.step.jliang.quartz.job.HelloJob;
import org.quartz.*;
import org.quartz.impl.JobDetailImpl;
import org.quartz.impl.StdSchedulerFactory;

import static org.quartz.SimpleScheduleBuilder.simpleSchedule;

public class HelloQuartzScheduling {

    public static void main(String[] args) throws SchedulerException {

        SchedulerFactory schedulerFactory = new StdSchedulerFactory();
        Scheduler scheduler = schedulerFactory.getScheduler();

        JobDetail helloJob = new JobDetailImpl("helloQuartzJob",
                Scheduler.DEFAULT_GROUP, HelloJob.class);

        Trigger trigger = TriggerBuilder.newTrigger()
                .withIdentity("trigger1", "group1")
                .startNow()
                .withSchedule(simpleSchedule().
                        repeatSecondlyForever())
                .build();

        scheduler.scheduleJob(helloJob, trigger);
        scheduler.start();
        try {
            Thread.sleep(1000 * 60 * 3);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
