package com.example.serviceshuffle.services;

import com.example.serviceshuffle.dto.NumericRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.*;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.*;


@Service
public class NumericService {


    @Value("${shuffle.api.serviceLog.baseUrl}")
    private String LOG_BASE_URL;

    @Value("${shuffle.api.serviceLog.log}")
    private String LOG_PATH;

    private final RestTemplate restTemplate;

    private final TaskRunnerService taskRunnerService;

    @Autowired
    public NumericService(RestTemplate restTemplate, TaskRunnerService taskRunnerService) {
        this.restTemplate = restTemplate;
        this.taskRunnerService = taskRunnerService;
    }


    public List<Integer> getShuffleArray(NumericRequest numericRequest) {
        Random random = new Random();
        Integer limit = numericRequest.getLimit();
        Set<Integer> set = new HashSet<>();
        while (set.size()<limit) {
            Integer randNum = random.nextInt(limit) + 1;
            set.add(randNum);
        }
        List<Integer> shuffleArray = new ArrayList<>(set);
        Collections.shuffle(shuffleArray);
        // Run async log operation.
        this.taskRunnerService.runAsyncTask(getLogRunnable(numericRequest));
        return shuffleArray;
    }


    private Runnable getLogRunnable(NumericRequest numericRequest) {
        return () -> logRequestToLogSvc(numericRequest);
    }

    @Async("threadPoolTaskExecutor")
    public void logRequestToLogSvc(NumericRequest numericRequest) {
        String url = LOG_BASE_URL + LOG_PATH;
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<Object> entity = new HttpEntity<>(numericRequest, headers);
        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.POST, entity, String.class);
        HttpStatus httpStatus = responseEntity.getStatusCode();
        if (httpStatus != null) {
            System.out.println("CALL TO LOG SERVICE RETURN HTTP STATUS OF:" + httpStatus);
        }
    }


}
