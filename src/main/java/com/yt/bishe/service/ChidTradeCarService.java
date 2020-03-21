package com.yt.bishe.service;

import com.yt.bishe.entity.ChidTradeCar;

import java.util.List;

public interface ChidTradeCarService {

    List<ChidTradeCar> getCTradeCarByPId(String tradeCarId);

    void addCTradeCar(ChidTradeCar chidTradeCar);

    /**
     * 根据购物车id和书名查询子购物车
     * @param bookId
     * @param tradeCarId
     * @return
     */
    Integer findChidTradeCarId(int bookId,String tradeCarId);

    boolean reviseChidTradeCar(int id,int count);
}
