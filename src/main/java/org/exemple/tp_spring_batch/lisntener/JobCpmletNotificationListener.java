package org.exemple.tp_spring_batch.lisntener;

import org.exemple.tp_spring_batch.Tables.Order;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.jdbc.core.DataClassRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.logging.Logger;
@Component
public class JobCpmletNotificationListener implements JobExecutionListener {
    private static final Logger LOGGER = Logger.getLogger(JobCpmletNotificationListener.class.getName());
    private final JdbcTemplate jdbcTemplate;

    public JobCpmletNotificationListener(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if(jobExecution.getStatus()== BatchStatus.COMPLETED) LOGGER.info("le Job est terminé avec succés");
        jdbcTemplate.query("select * from orders",new DataClassRowMapper<>(Order.class))
        .forEach(System.out::println);

    }
}
