package com.yt.bishe.entity;

public class ChidOrder {
    private int chidOrderId;
    private String pOrderId;
    private String bookName;
    private String bookPic;
    private double price;
    private int count;

    public int getChidOrderId() {
        return chidOrderId;
    }

    public void setChidOrderId(int chidId) {
        this.chidOrderId = chidOrderId;
    }

    public String getpOrderId() {
        return pOrderId;
    }

    public void setpOrderId(String pOrderId) {
        this.pOrderId = pOrderId;
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
