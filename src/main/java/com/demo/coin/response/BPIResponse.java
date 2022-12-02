package com.demo.coin.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BPIResponse {
    @JsonProperty("USD")
    private CoinDetailResponse usd;
    @JsonProperty("GBP")
    private CoinDetailResponse gbp;
    @JsonProperty("EUR")
    private CoinDetailResponse eur;
}
