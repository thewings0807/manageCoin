package com.demo.coin.service.impl;

import com.demo.coin.controller.exception.CodeExistException;
import com.demo.coin.controller.exception.NoDataException;
import com.demo.coin.entity.Coin;
import com.demo.coin.repository.CoinRepository;
import com.demo.coin.request.CoinRequest;
import com.demo.coin.response.CoinDeskBackResponse;
import com.demo.coin.response.CoinResponse;
import com.demo.coin.response.CoinTranResponse;
import com.demo.coin.response.TranDataResponse;
import com.demo.coin.service.CoinService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import java.time.LocalDateTime;
import java.util.Optional;

@Service
public class CoinServiceImpl implements CoinService {

    private final CoinRepository coinRepository;

    public CoinServiceImpl(CoinRepository coinRepository) {
        this.coinRepository = coinRepository;
    }

    @Override
    public CoinResponse getCoin(String code) throws NoDataException {
        Coin coin = coinRepository.findByCode(code).orElseThrow(()
                -> new NoDataException("Can't find this coin."));
        return CoinResponse.valueOf(coin);
    }

    @Override
    @Transactional
    public CoinResponse addCoin(CoinRequest request) throws CodeExistException {

        Optional<Coin> existCode = coinRepository.findByCode(request.getCode());
        if (existCode.isPresent()) {
            throw new CodeExistException("code value is already exist.");
        }

        Coin coin = new Coin();
        coin.setCode(request.getCode());
        coin.setName(request.getName());
        coin.setDescription(request.getDescription());
        coin.setSymbol(request.getSymbol());
        coin.setRate(request.getRate());
        coin.setRateFloat(request.getRateFloat());
        coin.setUpdateTime(LocalDateTime.now());
        coinRepository.save(coin);
        return CoinResponse.valueOf(coin);
    }

    @Override
    @Transactional
    public CoinResponse updateCoin(CoinRequest request) throws NoDataException {

        Coin coin = coinRepository.findByCode(request.getCode()).orElseThrow(()
                -> new NoDataException("Can't find coin data."));

        coin.setName(request.getName());
        coin.setDescription(request.getDescription());
        coin.setSymbol(request.getSymbol());
        coin.setRate(request.getRate());
        coin.setRateFloat(request.getRateFloat());
        coin.setUpdateTime(LocalDateTime.now());
        Coin save = coinRepository.save(coin);
        return CoinResponse.valueOf(save);
    }

    @Override
    @Transactional
    public void deleteCoin(String code) throws NoDataException {
        Coin coin = coinRepository.findByCode(code).orElseThrow(()
                -> new NoDataException("Can't find coin data."));
        coinRepository.delete(coin);
    }

    @Override
    public CoinDeskBackResponse callCoinDesk() {
        Client client = ClientBuilder.newClient();
        WebTarget baseTarget = client.target("https://api.coindesk.com/v1/bpi/currentprice.json");
        return baseTarget.request().get(CoinDeskBackResponse.class);
    }

    @Override
    public TranDataResponse tranData() {
        CoinDeskBackResponse coinDeskBack = this.callCoinDesk();
        TranDataResponse tranData = TranDataResponse.valueOf(coinDeskBack);
        for (CoinTranResponse coinData : tranData.getCoinTranList()) {
            Optional<Coin> findCoin = coinRepository.findByCode(coinData.getCode());
            if (findCoin.isPresent()) {
                coinData.setName(findCoin.get().getName());
            }
        }
        return tranData;
    }
}
