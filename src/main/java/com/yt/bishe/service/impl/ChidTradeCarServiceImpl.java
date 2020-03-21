package com.yt.bishe.service.impl;

import com.yt.bishe.dao.ChidTradeCarDao;
import com.yt.bishe.dao.TradeCarDao;
import com.yt.bishe.entity.ChidTradeCar;
import com.yt.bishe.entity.TradeCar;
import com.yt.bishe.service.ChidTradeCarService;
import com.yt.bishe.service.TradeCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ChidTradeCarServiceImpl implements ChidTradeCarService {
    @Autowired
    private ChidTradeCarDao chidTradeCarDao;

    @Override
    public List<ChidTradeCar> getCTradeCarByPId(String tradeCarId) {
        return chidTradeCarDao.selectCTradeCarByPId(tradeCarId);
    }

    @Override
    public void addCTradeCar(ChidTradeCar chidTradeCar) {
        chidTradeCarDao.insertCTradeCar(chidTradeCar);
    }

    @Override
    public Integer findChidTradeCarId(int bookId, String tradeCarId) {
        return chidTradeCarDao.selectChidTradeCarId(bookId,tradeCarId);
    }

    @Override
    public boolean reviseChidTradeCar(int id, int count) {
        return chidTradeCarDao.updateChidTradeCar(id,count);
    }
}
