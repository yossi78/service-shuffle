package com.example.serviceshuffle.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;


@Data
public class NumericRequest {


    @JsonProperty("limit")
    @NotNull(message = "Limit can't be null")
    @Min(value = 1, message = "The min value for limit is 1")
    @Max(value = 1000, message = "The max value for limit is 1000")
    private Integer limit;




}
