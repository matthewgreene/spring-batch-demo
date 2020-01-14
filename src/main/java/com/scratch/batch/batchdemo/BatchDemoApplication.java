package com.scratch.batch.batchdemo;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class BatchDemoApplication implements CommandLineRunner {
	@Autowired
	private JobLauncher jobLauncher;

	@Autowired
	@Qualifier("userJob")
	private Job userJob;

	public static void main(String[] args) {
		SpringApplication.run(BatchDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		JobParametersBuilder jobProperties = new JobParametersBuilder();
		jobLauncher.run(userJob, jobProperties.toJobParameters());
	}
}
