package com.yt.bishe.service;

import com.yt.bishe.entity.Book;
import com.yt.bishe.utils.Page.PageRequest;
import com.yt.bishe.utils.Page.PageResult;

import java.util.List;

public interface BookService {
    /**
     * 图书信息录入
     */
    boolean saveBookInfo(Book book);
    /**
     * 删除图书
     */
    boolean deleteBook(int bookId);

    /**
     *获取商家自己的图书信息
     */
    List<Book> getShopBooks(String shopId);
    /**
     * 获取书本详细信息
     */
    Book getBookDetails(int bookId);
    /**
     * 图书查询
     */
    List<Book> findBooks(String bookName);

    /**
     * 图书类别查询
     */
    List<Book> findBooksByCategory();

    /**
     * 图书热度查询
     */
    List<Book> findBooksByHot();

    /**
     * 新书查询
     */
    List<Book> findBooksByNew();

    /**
     * 更新图书库存
     */
    boolean reviseBookCount(int bookId);

    /**
     * 更新图书状态
     */
    boolean reviseBookState(int bookId);

    /**
     * 修改图书图片
     */
    void reviseBookPic(int bookId, String bookPic);

    /**
     *修改图书信息
     */
    boolean reviseBookInfo(Book book);

    PageResult findAllBooksByPage(PageRequest pageRequest);


    /**
     * 根据图书ID查询商店ID
     */
    String getShopIdByBookId(int bookId);
}
