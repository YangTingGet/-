package com.yt.bishe.controller;

import com.yt.bishe.entity.Book;
import com.yt.bishe.entity.Order;
import com.yt.bishe.entity.User;
import com.yt.bishe.service.BookService;
import com.yt.bishe.service.OrderService;
import com.yt.bishe.service.UserService;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;


@Controller
@RequestMapping("/order")
public class OrderController {

    @Autowired
    private BookService bookService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private UserService userService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(@RequestParam int bookId, @RequestParam int count, HttpServletRequest request){
        String userName = (String)request.getSession().getAttribute("userName");
        String shopId = bookService.getShopIdByBookId(bookId);
        String result = orderService.createOrder(bookId,userName,count,shopId);
        return result;

    }

    @RequestMapping("/getOrderInfo")
    @ResponseBody
    public ModelAndView getOrderInfo(String orderId,ModelAndView modelAndView){
        Order order = orderService.getOrderInfo(orderId);
        Book book = bookService.getBookDetails(order.getBookId());
        User user =userService.getUserInfo(order.getUserName());
        modelAndView.addObject("user",user);
        modelAndView.addObject("book",book);
        modelAndView.addObject("order",order);
        modelAndView.setViewName("jiesuan");
        return modelAndView;
    }

    @RequestMapping("/reviseOrderAddressAndTotalPrice")
    @ResponseBody
    public String reviseOrderAddressAndTotalPrice(@RequestParam String address,@RequestParam String orderId,@RequestParam String totalPrice){
        int state = orderService.getOrderInfo(orderId).getPayState();

        if (state != 3) {
            if (orderService.reviseOrderAddressAndTotalPrice(address, orderId, Double.parseDouble(totalPrice))) {
                return  "1";
            }else return "0";
        }else return "1";

    }
    public void toPayforBook(@RequestParam int bookId){

    }

    @RequestMapping("/getUserOrders")
    @ResponseBody
    public ModelAndView getUserOrders(HttpServletRequest request,ModelAndView modelAndView){
        String userName = (String)request.getSession().getAttribute("userName");
        List<Order> orders = orderService.getUserOrders(userName);
        List<Order> orders0 = new ArrayList<>();
        List<Order> orders1 = new ArrayList<>();
        Iterator<Order> iterator =orders.iterator();
        while (iterator.hasNext()){
            Order order = iterator.next();
            if (order.getPayState() == 0){
                orders0.add(order);
            }else if (order.getPayState() == 1){
                orders1.add(order);
            }
        }
        modelAndView.addObject("order0",orders0);
        modelAndView.addObject("order1",orders1);
        modelAndView.addObject("orders",orders);
        modelAndView.setViewName("order");
        return modelAndView;
    }
}
