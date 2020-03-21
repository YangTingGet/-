package com.yt.bishe.service;

import com.yt.bishe.entity.Order;

import java.util.List;

public interface OrderService {
    String createOrder(int bookId,String userName,int count,String shopId);
    Order getOrderInfo(String orderId);
    void reviseOrderState(String orderId,int state);
    boolean reviseOrderAddressAndTotalPrice(String address,String orderId,double totalPrice);

    List<Order> getUserOrders(String userName);

}
