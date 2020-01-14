package com.scratch.batch.batchdemo.config;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.batch.core.Job;
import org.springframework.batch.test.JobLauncherTestUtils;
import org.springframework.batch.test.context.SpringBatchTest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;
@SpringBatchTest
@RunWith(SpringRunner.class)
@ContextConfiguration(classes = {BatchConfig.class, BatchConfigTest.TestConfiguration.class})
class BatchConfigTest {
    @Autowired
    private JobLauncherTestUtils jobLauncherTestUtils;

    @Test
    void userStep() {
        assertNotNull(jobLauncherTestUtils, "jobLauncherTestUtils should not be null");
    }

    @Configuration
    static class TestConfiguration {
        @Autowired
        @Qualifier("userJob")
        private Job userJob;

        @Bean
        public JobLauncherTestUtils jobLauncherTestUtils() {
            JobLauncherTestUtils utils = new JobLauncherTestUtils();
            utils.setJob(userJob);
            return utils;
        }
    }
}