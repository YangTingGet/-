package com.yt.bishe.dao;

import com.yt.bishe.entity.ChidTradeCar;
import com.yt.bishe.entity.TradeCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface ChidTradeCarDao {
    List<ChidTradeCar> selectCTradeCarByPId(String pTradeCarId);

    void insertCTradeCar(ChidTradeCar chidTradeCar);

    Integer selectChidTradeCarId(int bookId, String pTradeCarId);

    boolean updateChidTradeCar(int chidTradeCarId, int count);
}
