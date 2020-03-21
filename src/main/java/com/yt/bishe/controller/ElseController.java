package com.yt.bishe.controller;

import com.yt.bishe.service.BookService;
import com.yt.bishe.utils.Page.PageRequest;
import com.yt.bishe.utils.Page.PageResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller

public class ElseController {
    @Autowired
    private BookService bookService;
    @RequestMapping("/index")
    @ResponseBody
    public ModelAndView index(ModelAndView modelAndView){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(1);
        Object result = bookService.findAllBooksByPage(pageRequest);
        modelAndView.addObject("books",((PageResult) result).getContent());
        modelAndView.addObject("result",result);
        modelAndView.setViewName("index");
        return modelAndView;
//        modelAndView.setViewName("index");
//        System.out.println("跳转成功");
//        return modelAndView;
    }


}
