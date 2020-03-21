package com.yt.bishe.entity;

import java.util.Date;

public class Book {
    private int bookId;  //书本id
    private String bookName;  //书名
    private String bookAdress;  //图片地址
    private String press;  //出版社
    private String publicationDate;  //出版时间
    private String author;  //作者
    private String category;  //类别
    private double price;  //价格
    private String quality; //成色
    private int count;  //数量
    private Date saletime;  //上架时间
    private String shopId;  //所属商铺id
    private int sales;   //图书销量

    public int getSales() {
        return sales;
    }

    public void setSales(int sales) {
        this.sales = sales;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAdress() {
        return bookAdress;
    }

    public void setBookAdress(String bookAdress) {
        this.bookAdress = bookAdress;
    }

    public String getPress() {
        return press;
    }

    public void setPress(String press) {
        this.press = press;
    }

    public String getPublicationDate() {
        return publicationDate;
    }

    public void setPublicationDate(String publicationDate) {
        this.publicationDate = publicationDate;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String auther) {
        this.author = auther;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getQuality() {
        return quality;
    }

    public void setQuality(String quality) {
        this.quality = quality;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Date getSaletime() {
        return saletime;
    }

    public void setSaletime(Date saletime) {
        this.saletime = saletime;
    }

    public String getShopId() {
        return shopId;
    }

    public void setShopId(String shopId) {
        this.shopId = shopId;
    }
}
