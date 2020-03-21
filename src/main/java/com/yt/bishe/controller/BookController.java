package com.yt.bishe.controller;


import com.yt.bishe.entity.Book;
import com.yt.bishe.entity.Shop;
import com.yt.bishe.service.BookService;
import com.yt.bishe.service.ShopService;
import com.yt.bishe.utils.Page.PageRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/book")
public class BookController {

    @Autowired
    private BookService bookService;
    @Autowired
    private ShopService shopService;


    @RequestMapping("/getBookDetails")
    @ResponseBody
    public ModelAndView getBookDetails(@RequestParam int bookId,ModelAndView modelAndView){
        Book book = bookService.getBookDetails(bookId);
        modelAndView.addObject("book",book);
        Shop shop = shopService.getShopInfoByShopId(book.getShopId());
        modelAndView.addObject("shopName",shop.getShopName());
        modelAndView.setViewName("shopbookinfo");
        return modelAndView;
    }

    @RequestMapping("/reviseBook")
    @ResponseBody
    public ModelAndView reviseBook(ModelAndView modelAndView,Book book){
        if (bookService.reviseBookInfo(book)){
            modelAndView.addObject("book",book);
            modelAndView.setViewName("shopbookinfo");
        }
        return modelAndView;
    }

    @RequestMapping("/addBook")
    @ResponseBody
    public boolean addBook(Book book,HttpServletRequest request){
        String shopId = (String)request.getSession().getAttribute("shopId");
        book.setShopId(shopId);
        return bookService.saveBookInfo(book);
    }

    @Value("D:\\Program Files\\ideaProject\\bishe\\src\\main\\resources\\static\\images")
    private String filePath;
    @RequestMapping("/addBookPic")
    @ResponseBody
    public ModelAndView addBookPic(@RequestParam("file") MultipartFile file,HttpServletRequest request,ModelAndView modelAndView) throws IOException {
        if(file.isEmpty()){
            throw new NullPointerException("文件为空");
        }
        //获取原始图片的拓展名
        String originalFileName = file.getOriginalFilename();
        String prefix = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        //新的文件名
        String newFileName = UUID.randomUUID() +"."+ prefix;
        //封装上传文件位置的全路径
        File targetFile = new File(filePath,newFileName);
        //把本地文件上传到封装上传文件位置的全路径
        file.transferTo(targetFile);
        int bookId = (int)request.getSession().getAttribute("bookId");
        bookService.reviseBookPic(bookId,newFileName);
//        if (bookService.reviseBookPic(bookId,newFileName)){
//            return "redirect:/book/getBookDetails";
//        }else
//            return "redirect:/error";
        modelAndView.setViewName("redirect:/book/getBookDetails?bookId="+bookId);
        return  modelAndView;


    }
    @RequestMapping("/getUserBookDetails")
    @ResponseBody
    public ModelAndView getUserBookDetails(@RequestParam int bookId,ModelAndView modelAndView,HttpServletRequest request){
        request.getSession().setAttribute("bookId",bookId);
        Book book = bookService.getBookDetails(bookId);
        modelAndView.addObject("book",book);
        Shop shop = shopService.getShopInfoByShopId(book.getShopId());
        modelAndView.addObject("shopName",shop.getShopName());
        modelAndView.setViewName("goodsinfo");
        return modelAndView;
    }



    @RequestMapping("/deleteBook")
    @ResponseBody
    public boolean deleteBook(int bookId){
        if (bookService.deleteBook(bookId)){
            return true;
        }else return false;
    }

    @RequestMapping("/getHotBooks")
    @ResponseBody
    public List<Book> getHotBooks(){

        return bookService.findBooksByHot();
    }


    @RequestMapping("/findAllBooks")
    @ResponseBody
    public Object findAllBooks(){
        PageRequest pageRequest = new PageRequest();
        pageRequest.setPageNum(1);
        pageRequest.setPageSize(2);
        System.out.println(bookService.findAllBooksByPage(pageRequest));
        return bookService.findAllBooksByPage(pageRequest);
    }
}
