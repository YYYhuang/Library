package com.example.library;

public class Book {
    private String num;
    private String BookName;
    private String cbs;

    public Book(String num,String BookName,String cbs){
        this.num=num;
        this.BookName=BookName;
        this.cbs=cbs;
    }

    public String getNum() {
        return num;
    }

    public void setNum(String num) {
        this.num = num;
    }

    public String getBookName() {
        return BookName;
    }

    public void setBookName(String bookName) {
        BookName = bookName;
    }

    public String getCbs() {
        return cbs;
    }

    public void setCbs(String cbs) {
        this.cbs = cbs;
    }
}
