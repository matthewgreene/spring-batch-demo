package com.scratch.batch.batchdemo;

import com.scratch.batch.batchdemo.user.User;
import org.springframework.batch.item.ItemProcessor;

public class UserItemProcessor implements ItemProcessor<User, String> {
    @Override
    public String process(User item) {
        return item.toString();
    }
}
