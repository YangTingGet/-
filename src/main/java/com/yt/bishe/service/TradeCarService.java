package com.yt.bishe.service;

import com.yt.bishe.entity.TradeCar;

public interface TradeCarService {
    void registerTradeCar(TradeCar tradeCar);

    TradeCar getTradeCarByUserName(String userName);
}
