package com.yt.bishe.service.impl;

import com.yt.bishe.dao.ShopDao;
import com.yt.bishe.entity.Shop;
import com.yt.bishe.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ShopServiceImpl implements ShopService {
    @Autowired
    ShopDao shopDao;

    @Override
    public Shop getShopInfo(String userName) {
        return shopDao.selectShopInfo(userName);
    }

    @Override
    public Shop getShopInfoByShopId(String shopId){
        return shopDao.selectShopInfoByshopId(shopId);
    }

    @Override
    public boolean reviseShopInfo(Shop shop) {
        return shopDao.updateShopInfo(shop);
    }

    @Override
    public boolean saveShopInfo(Shop shop){
        return shopDao.insertShopInfo(shop);
    }
}
