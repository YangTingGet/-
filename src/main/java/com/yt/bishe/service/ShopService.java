package com.yt.bishe.service;

import com.yt.bishe.entity.Shop;

public interface ShopService {

    Shop getShopInfo(String userName);

    boolean reviseShopInfo(Shop shop);

    boolean saveShopInfo(Shop shop);

    Shop getShopInfoByShopId(String shopId);
}
