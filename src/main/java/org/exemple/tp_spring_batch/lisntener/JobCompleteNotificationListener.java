package org.exemple.tp_spring_batch.lisntener;

import org.exemple.tp_spring_batch.Tables.Hospital;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
@Component
public class JobCompleteNotificationListener implements JobExecutionListener {
    private static final Logger LOGGER = Logger.getLogger(JobCompleteNotificationListener.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public JobCompleteNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

 /*   @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED) LOGGER.info("le Job est terminé avec succés");
        jdbcTemplate.query("select * from orders",new DataClassRowMapper<>(Order.class))
        .forEach(System.out::println);

    }*/
    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED) LOGGER.info("le Job est terminé avec succés");
        jdbcTemplate.query("select * from hospital",new DataClassRowMapper<>(Hospital.class))
                .forEach(System.out::println);

    }
}
