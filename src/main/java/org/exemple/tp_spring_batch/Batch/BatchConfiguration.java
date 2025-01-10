package org.exemple.tp_spring_batch.Batch;

import org.exemple.tp_spring_batch.Processor.HospitaltemProcessor;
import org.exemple.tp_spring_batch.Tables.Hospital;
import org.exemple.tp_spring_batch.lisntener.JobCompleteNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfiguration {

    @Autowired
    private DataSource dataSource;
    @Bean
    public FlatFileItemReader<Hospital> Reader() {
        return new FlatFileItemReaderBuilder<Hospital>()
                .name("Hospital")
                .resource(new ClassPathResource("hospitalizations.csv"))
                .delimited()
                .names("id", "nom", "service", "dateAdmission", "dateSortie") // Match CSV columns
                .linesToSkip(1)
                .targetType(Hospital.class)
                .build();

    }
    @Bean
    public JdbcBatchItemWriter<Hospital> Writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Hospital>()
                .sql("INSERT INTO hospital (nom, service, dateAdmission, dateSortie) VALUES ( :nom, :service, :dateAdmission, :dateSortie)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
    @Bean
    public HospitaltemProcessor Processor(){
        return new HospitaltemProcessor();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<Hospital> reader, JdbcBatchItemWriter<Hospital> writer,
                      HospitaltemProcessor processor) {
        return new StepBuilder("step1",jobRepository)
                .<Hospital,Hospital> chunk(3,transactionManager)
                .reader(reader)
                .writer(writer)
                .processor(processor)
                .build();
    }

    @Bean
    public Job importOrderJob(JobRepository jobRepository, Step step1,
                              JobCompleteNotificationListener listener) {
        return new JobBuilder("importHospitalJob",jobRepository)
                .listener(listener)
                .start(step1)
                .build();

    }

}
