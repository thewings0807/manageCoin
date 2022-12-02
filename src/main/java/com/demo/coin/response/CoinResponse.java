package com.demo.coin.response;

import com.demo.coin.entity.Coin;
import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.format.DateTimeFormatter;

@Getter
@Setter
public class CoinResponse {
    private String code;
    private String name;
    private String symbol;
    private String rate;
    private String description;
    private double rateFloat;
    @JsonFormat(pattern = "yyyy/MM/dd HH:mm:ss")
    private String updateTime;

    public static CoinResponse valueOf(Coin coin) {
        CoinResponse response = new CoinResponse();
        response.setCode(coin.getCode());
        response.setName(coin.getName());
        response.setSymbol(coin.getSymbol());
        response.setDescription(coin.getDescription());
        response.setRate(coin.getRate());
        response.setRateFloat(coin.getRateFloat());
        response.setUpdateTime(coin.getUpdateTime().format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));
        return response;
    }
}
