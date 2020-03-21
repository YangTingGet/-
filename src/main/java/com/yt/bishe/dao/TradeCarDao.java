package com.yt.bishe.dao;

import com.yt.bishe.entity.TradeCar;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Mapper
@Repository
public interface TradeCarDao {
    boolean insertTradeCar(TradeCar tradeCar);

    TradeCar selectTradeCarByUserName(String userName);
}
