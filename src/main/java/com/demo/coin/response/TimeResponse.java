package com.demo.coin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TimeResponse {
    private String updated;
    private String updatedISO;
    @JsonProperty("updateduk")
    private String updatedUK;
}