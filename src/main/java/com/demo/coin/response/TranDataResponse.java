package com.demo.coin.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class TranDataResponse {
    private String updateTime;
    private List<CoinTranResponse> coinTranList;

    public static TranDataResponse valueOf(CoinDeskBackResponse backResponse) {
        TranDataResponse response = new TranDataResponse();
        DateTimeFormatter df = DateTimeFormatter.ISO_DATE_TIME;
        LocalDateTime time = LocalDateTime.parse(backResponse.getTime().getUpdatedISO(),df);
        response.setUpdateTime(time.format(DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss")));

        List<CoinTranResponse> coinTranList = new ArrayList<>();
        coinTranList.add(CoinTranResponse.valueOf(backResponse.getBpi().getUsd()));
        coinTranList.add(CoinTranResponse.valueOf(backResponse.getBpi().getGbp()));
        coinTranList.add(CoinTranResponse.valueOf(backResponse.getBpi().getEur()));
        response.setCoinTranList(coinTranList);
        return  response;
    }
}
