package com.demo.coin.service;

import com.demo.coin.controller.exception.CodeExistException;
import com.demo.coin.controller.exception.NoDataException;
import com.demo.coin.request.CoinRequest;
import com.demo.coin.response.CoinDeskBackResponse;
import com.demo.coin.response.CoinResponse;
import com.demo.coin.response.TranDataResponse;

public interface CoinService {

    /**
     * 查詢幣別資料
     *
     * @param code 幣別代碼
     * @return
     * @throws NoDataException
     */
    CoinResponse getCoin(String code) throws NoDataException;

    /**
     * 新增幣別資料
     *
     * @param request
     * @return
     * @throws CodeExistException
     */
    CoinResponse addCoin(CoinRequest request) throws CodeExistException;

    /**
     * 更新幣別資料
     *
     * @param request
     * @return
     * @throws NoDataException
     */
    CoinResponse updateCoin(CoinRequest request) throws NoDataException;

    /**
     * 刪除幣別資料
     *
     * @param code
     * @throws NoDataException
     */
    void deleteCoin(String code) throws NoDataException;

    /**
     * 呼叫 coindesk API
     * @return
     */
    CoinDeskBackResponse callCoinDesk();

    /**
     * 呼叫 coindesk API後將資料轉換
     * @return
     */
    TranDataResponse tranData();
}
