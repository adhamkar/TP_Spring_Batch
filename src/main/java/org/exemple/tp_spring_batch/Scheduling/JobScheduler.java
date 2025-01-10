package org.exemple.tp_spring_batch.Scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class JobScheduler {
    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job importHospitalJob;

    @Scheduled(cron = "0 0 0 * * ?")
    public void runJob() throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addLong("time", System.currentTimeMillis())
                .toJobParameters();

        jobLauncher.run(importHospitalJob, jobParameters);
    }
}
