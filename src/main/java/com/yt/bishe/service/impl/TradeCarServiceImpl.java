package com.yt.bishe.service.impl;

import com.yt.bishe.dao.TradeCarDao;
import com.yt.bishe.entity.TradeCar;
import com.yt.bishe.service.TradeCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TradeCarServiceImpl implements TradeCarService {
    @Autowired
    private TradeCarDao tradeCarDao;

    @Override
    public void registerTradeCar(TradeCar tradeCar) {
        tradeCarDao.insertTradeCar(tradeCar);
    }

    @Override
    public TradeCar getTradeCarByUserName(String userName) {
        return tradeCarDao.selectTradeCarByUserName(userName);
    }
}
