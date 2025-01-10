package org.exemple.tp_spring_batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

//@Component
public class CalculateAverageStayTasklet implements Tasklet {
    private final JdbcTemplate jdbcTemplate;

    public CalculateAverageStayTasklet(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        Double averageStay = jdbcTemplate.queryForObject(
                "SELECT AVG(dureeSejour) FROM hospital", Double.class);

        System.out.println("Durée moyenne des séjours : " + averageStay + " jours");

        // Stocker le résultat dans le contexte d'exécution
        chunkContext.getStepContext().getStepExecution().getJobExecution()
                .getExecutionContext().put("averageStay", averageStay);

        return RepeatStatus.FINISHED;
    }
}
