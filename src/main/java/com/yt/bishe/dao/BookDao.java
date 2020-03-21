package com.yt.bishe.dao;

import com.yt.bishe.entity.Book;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@Mapper
public interface BookDao {
    /**
     * 插入书本信息
     * @param book
     * @return
     */
    boolean insertBookInfo(Book book);

    List<Book> findBooksByShopId(String shopId);
    /**
     * 查询图书信息
     * @return
     */
    List<Book> selectBooksByName(String bookName);

    /**
     * 热门书目查询
     * @return
     */
    List<Book> selectBookBySales();

    List<Book> selectBookByPage();

    Book selectBookByBookId(int bookId);

    /**
     * 更新图书信息
     * @param book
     * @return
     */
    boolean updateBookInfo(Book book);

    boolean deleteBook(int bookId);

    void updateBookPic(int bookId, String bookAdress);

    /**
     * 根据图书ID查询商店ID
     * @param bookId
     * @return
     */
    String selectShopIdByBookId(int bookId);
}
