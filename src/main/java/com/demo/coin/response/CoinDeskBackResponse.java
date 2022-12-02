package com.demo.coin.response;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CoinDeskBackResponse {
    private TimeResponse time;
    private String disclaimer;
    private String chartName;
    private BPIResponse bpi;
}
