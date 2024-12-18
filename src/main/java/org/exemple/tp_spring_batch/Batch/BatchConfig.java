package org.exemple.tp_spring_batch.Batch;


import org.exemple.tp_spring_batch.Processor.OrderItemProcessor;
import org.exemple.tp_spring_batch.Tables.Order; // Utilisez votre propre classe Order ici
import org.exemple.tp_spring_batch.lisntener.JobCpmletNotificationListener;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

@Configuration
public class BatchConfig {
    @Bean
    public FlatFileItemReader<Order> Reader() {
        return new FlatFileItemReaderBuilder<Order>()
                .name("Orders")
                .resource(new ClassPathResource("order.csv"))
                .delimited()
                .names("id","customerName", "amount")
                .linesToSkip(1)
                .targetType(Order.class)
                .build();

    }
    @Bean
    public JdbcBatchItemWriter<Order> Writer(DataSource dataSource) {
        return new JdbcBatchItemWriterBuilder<Order>()
                .sql("INSERT INTO orders (customerName, amount) VALUES (:customerName,:amount)")
                .dataSource(dataSource)
                .beanMapped()
                .build();
    }
    @Bean
    public OrderItemProcessor Processor(){
        return new OrderItemProcessor();
    }

    @Bean
    public Step step1(JobRepository jobRepository, DataSourceTransactionManager transactionManager,
                      FlatFileItemReader<Order> reader, JdbcBatchItemWriter<Order> writer,
                      OrderItemProcessor processor) {
        return new StepBuilder("step1",jobRepository)
                .<Order,Order> chunk(3,transactionManager)
                .reader(reader)
                .writer(writer)
                .processor(processor)
                .build();
    }
    @Bean
    public Job importOrderJob(JobRepository jobRepository, Step step1,
                                JobCpmletNotificationListener listener) {
        return new JobBuilder("importOrderJob",jobRepository)
                .listener(listener)
                .start(step1)
                .build();

    }
}
