package com.yt.bishe.service.impl;

import com.yt.bishe.dao.OrderDao;
import com.yt.bishe.entity.Order;
import com.yt.bishe.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDao orderDao;
    @Override
    public String createOrder(int bookId,String userName,int count,String shopId){
        Order order =new Order();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMddHHmmSSsss");
        String time = simpleDateFormat.format(Calendar.getInstance().getTime());
        String orderId = time+shopId+bookId;
        order.setOrderId(orderId);
        order.setBookId(bookId);
        order.setCount(count);
        order.setShopId(shopId);
        order.setUserName(userName);
        if (orderDao.insertOrder(order)){
            return orderId;
        }else return "false";
    }

    @Override
    public Order getOrderInfo(String orderId) {
        return orderDao.selectOrderByOrderId(orderId);
    }

    @Override
    public void reviseOrderState(String orderId,int state) {
        orderDao.updateOrderState(orderId,state);
    }

    @Override
    public boolean reviseOrderAddressAndTotalPrice(String address, String orderId,double totalPrice) {
        return orderDao.updateOrderAddressAndTotalPrice(address,orderId,totalPrice);
    }

    @Override
    public List<Order> getUserOrders(String userName) {
        return orderDao.selectOrdersByUserName(userName);
    }
}
