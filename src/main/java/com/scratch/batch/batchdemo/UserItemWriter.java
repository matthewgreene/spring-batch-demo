package com.scratch.batch.batchdemo;

import org.springframework.batch.item.ItemWriter;

import java.util.List;

public class UserItemWriter implements ItemWriter<String> {
    @Override
    public void write(List<? extends String> items) {
        items.forEach(System.out::println);
    }
}
