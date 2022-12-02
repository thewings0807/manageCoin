package com.demo.coin.controller;

import com.demo.coin.controller.exception.CodeExistException;
import com.demo.coin.controller.exception.NoDataException;
import com.demo.coin.request.CoinRequest;
import com.demo.coin.response.CoinDeskBackResponse;
import com.demo.coin.response.CoinResponse;
import com.demo.coin.response.TranDataResponse;
import com.demo.coin.service.CoinService;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/coin")
public class CoinController {

    final CoinService coinService;

    public CoinController(CoinService coinService) {
        this.coinService = coinService;
    }

    @PostMapping("/add")
    public CoinResponse addCoin(@Validated @RequestBody CoinRequest request) throws CodeExistException {
        return coinService.addCoin(request);
    }

    @PostMapping("/update")
    public CoinResponse updateCoin(@Validated @RequestBody CoinRequest request) throws NoDataException {
        return coinService.updateCoin(request);
    }

    @DeleteMapping("/delete")
    public void deleteCoin(@RequestParam String code) throws NoDataException {
        coinService.deleteCoin(code);
    }

    @GetMapping("/get")
    public CoinResponse getCoin(@RequestParam String code) throws NoDataException {
        return coinService.getCoin(code);
    }

    @GetMapping("/callCoinDesk")
    public CoinDeskBackResponse callCoinDesk(){
        return coinService.callCoinDesk();
    }

    @GetMapping("/tranData")
    public TranDataResponse tranData(){
        return coinService.tranData();
    }
}