package com.scratch.batch.batchdemo.config;

import com.scratch.batch.batchdemo.UserItemProcessor;
import com.scratch.batch.batchdemo.UserItemWriter;
import com.scratch.batch.batchdemo.user.User;
import com.scratch.batch.batchdemo.util.ClassUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import java.io.File;
import java.nio.file.Path;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private final StepBuilderFactory stepBuilderFactory;
    private final JobBuilderFactory jobBuilderFactory;

    @Value("${inputFile}")
    private File inputFile;
    @Value("${output-directory}")
    private Path outputDirectory;

    public BatchConfig(StepBuilderFactory stepBuilderFactory, JobBuilderFactory jobBuilderFactory) {
        this.stepBuilderFactory = stepBuilderFactory;
        this.jobBuilderFactory = jobBuilderFactory;
    }

    @Bean
    public FlatFileItemReader<User> userFlatFileItemReader() {
        return new FlatFileItemReaderBuilder<User>()
                .name("userItemReader")
                .resource(new FileSystemResource(inputFile))
                .delimited()
                .delimiter(",")
                .names(ClassUtils.getFieldNames(User.class))
                .fieldSetMapper(new BeanWrapperFieldSetMapper<User>() {
                    {
                        setTargetType(User.class);
                    }
                })
                .build();
    }

    @Bean
    public ItemProcessor<User, String> userItemProcessor() {
        return new UserItemProcessor();
    }

    @Bean
    public ItemWriter<String> userItemWriter() {
        return new UserItemWriter();
    }

    @Bean
    public Step userStep(FlatFileItemReader<User> reader,
                         ItemProcessor<User, String> processor,
                         ItemWriter<String> writer) {
        return stepBuilderFactory.get("userStep")
                .<User, String>chunk(50_000)
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .faultTolerant()
                .build();
    }

    @Bean
    public Job userJob(Step userStep) {
        return jobBuilderFactory.get("userJob")
                .start(userStep)
                .build();
    }
}
