package com.yt.bishe.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yt.bishe.dao.BookDao;
import com.yt.bishe.entity.Book;
import com.yt.bishe.service.BookService;
import com.yt.bishe.utils.Page.PageRequest;
import com.yt.bishe.utils.Page.PageResult;
import com.yt.bishe.utils.Page.PageUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookServiceImpl implements BookService {
    @Autowired
    BookDao bookDao;

    @Override
    public List<Book> getShopBooks(String shopId){
        return bookDao.findBooksByShopId(shopId);
    }
    @Override
    public Book getBookDetails(int bookId){
        return bookDao.selectBookByBookId(bookId);
    }

    @Override
    public boolean saveBookInfo(Book book) {
        return bookDao.insertBookInfo(book);
    }

    @Override
    public List<Book> findBooks(String bookName) {
        return bookDao.selectBooksByName(bookName);
    }

    @Override
    public List<Book> findBooksByCategory() {
        return null;
    }

    @Override
    public List<Book> findBooksByHot() {

        return bookDao.selectBookBySales();
    }

    @Override
    public List<Book> findBooksByNew() {
        return null;
    }

    @Override
    public boolean reviseBookCount(int bookId) {
        return false;
    }

    @Override
    public boolean reviseBookState(int bookId) {
        return false;
    }

    @Override
    public boolean reviseBookInfo(Book book){
       return bookDao.updateBookInfo(book);
    }

    @Override
    public boolean deleteBook(int bookId){
        return bookDao.deleteBook(bookId);
    }

    @Override
    public void reviseBookPic(int bookId, String bookPic){
        try {bookDao.updateBookPic(bookId, bookPic);
        }catch (Exception e){
            System.out.println(e);
        }
    }

    @Override
    public PageResult findAllBooksByPage(PageRequest pageRequest){
        return PageUtils.getPageResult(pageRequest,getPageInfo(pageRequest));
    }

    @Override
    public String getShopIdByBookId(int bookId) {
        return bookDao.selectShopIdByBookId(bookId);
    }

    private PageInfo<Book> getPageInfo(PageRequest pageRequest){
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Book> bookMenus = bookDao.selectBookByPage();
        return new PageInfo<Book>(bookMenus);
    }
}
