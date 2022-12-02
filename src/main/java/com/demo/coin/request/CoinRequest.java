package com.demo.coin.request;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
public class CoinRequest {
    @NotBlank(message = "Please enter the code.")
    private String code;
    private String name;
    private String symbol;
    private String rate;
    private String description;
    private double rateFloat;
}
