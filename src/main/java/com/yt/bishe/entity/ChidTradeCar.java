package com.yt.bishe.entity;

public class ChidTradeCar {
    private int chidTradeCarId;
    private String pTradeCarId;
    private String bookName;
    private String bookPic;
    private double price;
    private int count;
    private int bookId;

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public int getChidTradeCarId() {
        return chidTradeCarId;
    }

    public void setChidTradeCarId(int chidTradeCarId) {
        this.chidTradeCarId = chidTradeCarId;
    }

    public String getpTradeCarId() {
        return pTradeCarId;
    }

    public void setpTradeCarId(String pTradeCarId) {
        this.pTradeCarId = pTradeCarId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookPic() {
        return bookPic;
    }

    public void setBookPic(String bookPic) {
        this.bookPic = bookPic;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }
}
