package org.exemple.tp_spring_batch;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
public class TpSpringBatchApplication {

    public static void main(String[] args) {
        SpringApplication.run(TpSpringBatchApplication.class, args);
    }

}
