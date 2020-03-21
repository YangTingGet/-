package com.yt.bishe.dao;

import com.yt.bishe.entity.Order;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository
public interface OrderDao {
    boolean insertOrder(Order order);
    Order selectOrderByOrderId(String orderId);
    void updateOrderState(String orderId,int state);
    boolean updateOrderAddressAndTotalPrice(String address,String orderId,double totalPrice);

    List<Order> selectOrdersByUserName(String userName);
}
