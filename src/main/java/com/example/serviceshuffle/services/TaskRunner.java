package com.example.serviceshuffle.services;


import org.springframework.scheduling.annotation.EnableAsync;

@EnableAsync
public interface TaskRunner {
    void runAsyncTask(Runnable runnable);
}

