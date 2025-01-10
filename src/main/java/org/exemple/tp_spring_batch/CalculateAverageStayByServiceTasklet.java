package org.exemple.tp_spring_batch;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

//@Component
public class CalculateAverageStayByServiceTasklet implements Tasklet {
    private final JdbcTemplate jdbcTemplate;

    public CalculateAverageStayByServiceTasklet(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        List<Map<String, Object>> results = jdbcTemplate.queryForList(
                "SELECT service, AVG(dureeSejour) AS averageStay FROM hospital GROUP BY service");

        // Afficher les résultats
        for (Map<String, Object> row : results) {
            String service = (String) row.get("service");
            Double averageStay = (Double) row.get("averageStay");
            System.out.println("Service: " + service + ", Durée moyenne des séjours: " + averageStay + " jours");
        }

        return RepeatStatus.FINISHED;
    }
}
