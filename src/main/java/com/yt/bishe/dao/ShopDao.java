package com.yt.bishe.dao;

import com.yt.bishe.entity.Shop;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

@Repository
@Mapper
public interface ShopDao {

    /**
     * 更新商铺信息
     * @param shop
     * @return
     */
    boolean updateShopInfo(Shop shop);

    /**
     * 通过userID查询所属商铺
     * @param userName
     * @return
     */
    Shop selectShopInfo(String userName);

    Shop selectShopInfoByshopId(String shopId);

    boolean insertShopInfo(Shop shop);
}
