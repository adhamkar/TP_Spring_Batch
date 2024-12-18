package org.exemple.tp_spring_batch.Scheduling;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class OrderJobScheduler {
    @Autowired
    private JobLauncher jobLauncher;
    @Autowired
    private Job processOrderJob;

    @Scheduled(fixedRate = 5000)
    public void runJob(){
        try {
            JobParameters parameters = new JobParametersBuilder()
                    .addLong("start Time",System.currentTimeMillis())
                    .toJobParameters();
            jobLauncher.run(processOrderJob,parameters);
            System.out.println("Job finished");
        }catch (Exception e){
            e.printStackTrace();
        }

    }
}
