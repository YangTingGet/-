package com.yt.bishe.controller;

import com.yt.bishe.entity.*;
import com.yt.bishe.service.BookService;
import com.yt.bishe.service.ChidTradeCarService;
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
    @Autowired
    private ChidTradeCarService ctcService;

    @RequestMapping("/createOrder")
    @ResponseBody
    public String createOrder(@RequestParam int bookId, @RequestParam int count, HttpServletRequest request){
        String userName = (String)request.getSession().getAttribute("userName");
        Book book =bookService.getBookDetails(bookId);
        ChidOrder chidOrder =new ChidOrder();
        chidOrder.setBookId(bookId);
        chidOrder.setCount(count);
        chidOrder.setPrice(count*book.getPrice());
        chidOrder.setBookPic(book.getBookAdress());
        chidOrder.setBookName(book.getBookName());

        String result = orderService.createOrder(userName,count*book.getPrice());
        chidOrder.setpOrderId(result);
        orderService.createChidOrder(chidOrder);
        return result;

    }
    @RequestMapping("/createOrderinCart")
    @ResponseBody
    public String createOrderinCart(@RequestParam int[] ids,HttpServletRequest request){
        double totalPrice =0.00;
        String userName = (String)request.getSession().getAttribute("userName");
        for (int i =0 ;i<ids.length;i++){
            ChidTradeCar ctc=ctcService.getCTradeCarById(ids[i]);
            totalPrice=totalPrice+ctc.getPrice()*ctc.getCount();
        }
        String PorderId =orderService.createOrder(userName,totalPrice);
        for (int i =0 ;i<ids.length;i++){
            ChidTradeCar ctc=ctcService.getCTradeCarById(ids[i]);
            ChidOrder chidOrder=new ChidOrder();
            chidOrder.setBookName(ctc.getBookName());
            chidOrder.setBookPic(ctc.getBookPic());
            chidOrder.setCount(ctc.getCount());
            chidOrder.setPrice(ctc.getPrice());
            chidOrder.setpOrderId(PorderId);
            chidOrder.setBookId(ctc.getBookId());
            orderService.createChidOrder(chidOrder);
        }
        return PorderId;
    }

    @RequestMapping("/getOrderInfo")
    @ResponseBody
    public ModelAndView getOrderInfo(String orderId,ModelAndView modelAndView,HttpServletRequest request){
        User user =userService.getUserInfo((String)request.getSession().getAttribute("userName"));
        Double totalPeice=0.00;
        List<ChidOrder> chidOrders = orderService.getChidOrderInfo(orderId);
        Iterator<ChidOrder> iterator =chidOrders.iterator();
        while (iterator.hasNext()){
            ChidOrder chidOrder =iterator.next();
            totalPeice+=chidOrder.getPrice()*chidOrder.getCount();
        }
        modelAndView.addObject("chidOrders",chidOrders);
        modelAndView.addObject("totalPrice",totalPeice);
        modelAndView.addObject("user",user);
        modelAndView.setViewName("jiesuan");
        return modelAndView;
    }

    @RequestMapping("/reviseOrderAddress")
    @ResponseBody
    public String reviseOrderAddress(@RequestParam String address,@RequestParam String orderId){
        int state = orderService.getOrderInfo(orderId).getPayState();

        if (state != 3) {
            if (orderService.reviseOrderAddress(address, orderId)) {
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
