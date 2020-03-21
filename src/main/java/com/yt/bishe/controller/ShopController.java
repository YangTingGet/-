package com.yt.bishe.controller;

import com.yt.bishe.entity.Book;
import com.yt.bishe.entity.Shop;
import com.yt.bishe.service.BookService;
import com.yt.bishe.service.ShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@Controller
@RequestMapping("/shop")
public class ShopController {
    @Autowired
    ShopService shopService;
    @Autowired
    private BookService bookService;

    @RequestMapping("/getShopInfo")
    @ResponseBody
    public ModelAndView getShopInfo(ModelAndView modelAndView, HttpServletRequest request){

        String userName = (String)request.getSession().getAttribute("userName");
        Shop shop = shopService.getShopInfo(userName);
        request.getSession().setAttribute("shopId",shop.getShopId());
        List<Book> books = bookService.getShopBooks(shop.getShopId());
        modelAndView.addObject("books",books);
        modelAndView.addObject("shop",shop);
        modelAndView.setViewName("shop");
        return modelAndView;
    }

    /**
     * 修改商铺的信息
     */
    @RequestMapping("/reviseShopInfo")
    @ResponseBody
    public boolean reviseShopInfo(Shop shop){
        return shopService.reviseShopInfo(shop);
    }

}
