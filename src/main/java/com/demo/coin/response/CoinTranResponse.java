package com.demo.coin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinTranResponse {
    private String code;
    private String name;
    private double rate;

    public static CoinTranResponse valueOf(CoinDetailResponse coinDetail) {
        CoinTranResponse response = new CoinTranResponse();
        response.setCode(coinDetail.getCode());
        response.setRate(coinDetail.getRateFloat());
        return response;
    }
}
