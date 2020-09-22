package com.example.serviceshuffle.api;

import com.example.serviceshuffle.dto.NumericRequest;
import com.example.serviceshuffle.services.NumericService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


@RestController
@RequestMapping(value = "/v1")
public class NumericController {

    private NumericService numericService;


    @Autowired
    public NumericController(NumericService numericService) {
        this.numericService = numericService;
    }



    @PostMapping(value = "/numbers")
    public ResponseEntity getArrayByLimitNumber(@Valid @RequestBody NumericRequest numericRequest) {
        try {
            List<Integer> randomList =numericService.getShuffleArray(numericRequest);
            return new ResponseEntity(randomList, HttpStatus.OK);
        }catch (Exception e){
            e.printStackTrace();
            return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



    @GetMapping(value = "/health")
    public ResponseEntity healthCheck() {
        try {
            return new ResponseEntity("Shuffle Service Health is OK", HttpStatus.OK);
        }catch (Exception e){
            return new ResponseEntity<>(e.getCause(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }



}
