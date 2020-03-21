package com.yt.bishe.controller;

import com.yt.bishe.entity.Book;
import com.yt.bishe.entity.ChidTradeCar;
import com.yt.bishe.entity.TradeCar;
import com.yt.bishe.service.BookService;
import com.yt.bishe.service.ChidTradeCarService;
import com.yt.bishe.service.TradeCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/tradeCar")
public class TradeCarController {
    @Autowired
    private TradeCarService tradeCarService;
    @Autowired
    private ChidTradeCarService ctcService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/getTradeCar")
    @ResponseBody
    public ModelAndView getTradeCar(HttpServletRequest request, ModelAndView modelAndView){
        String userName =(String)request.getSession().getAttribute("userName");
        TradeCar tradeCar = tradeCarService.getTradeCarByUserName(userName);
        List<ChidTradeCar> chidTradeCars = ctcService.getCTradeCarByPId(tradeCar.getTradeCarId());
        System.out.println(chidTradeCars);
        modelAndView.addObject("ctcs",chidTradeCars);
        modelAndView.setViewName("cart");
        return modelAndView;
    }

    @RequestMapping("/addToCart")
    @ResponseBody
    public String addToCart(HttpServletRequest request,@RequestParam int bookId,@RequestParam int count){
        String userName = (String) request.getSession().getAttribute("userName");
        String tradeCarId = tradeCarService.getTradeCarByUserName(userName).getTradeCarId();
        Integer id = ctcService.findChidTradeCarId(bookId,tradeCarId);
        if (id == null){
            Book book = bookService.getBookDetails(bookId);
            ChidTradeCar chidTradeCar =new ChidTradeCar();
            chidTradeCar.setpTradeCarId(tradeCarId);
            chidTradeCar.setBookName(book.getBookName());
            chidTradeCar.setBookPic(book.getBookAdress());
            chidTradeCar.setCount(count);
            chidTradeCar.setPrice(book.getPrice());
            chidTradeCar.setBookId(book.getBookId());
            ctcService.addCTradeCar(chidTradeCar);
            return "1";
        }else if(ctcService.reviseChidTradeCar(id,count)){
            return "1";
        }else return "0";



    }
}
