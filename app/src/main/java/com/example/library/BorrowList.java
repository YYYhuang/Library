package com.example.library;

import android.content.Context;
import android.content.SharedPreferences;

import java.util.List;

public class BorrowList {
    private String code;
    private String msg;
    private List<borrow> borrows;

    public List<borrow> getBorrows() {
        return borrows;
    }

    public static  class borrow{
        private String userId;
        private String bookName;
        private String borrDate;
        private String bookId;

        public void setUserId(String userId) {
            this.userId = userId;
        }

        public void setBookName(String bookName) {
            this.bookName = bookName;
        }

        public void setBorrDate(String borrDate) {
            this.borrDate = borrDate;
        }

        public void setBookId(String bookId) {
            this.bookId = bookId;
        }

        public String getUserId() {
            return userId;
        }

        public String getBookId() {
            return bookId;
        }

        public String getBookName() {
            return bookName;
        }

        public String getBorrDate() {
            return borrDate;
        }
    }
    public void show(Context context){
        SharedPreferences.Editor editor = context.getSharedPreferences("borrows", Context.MODE_PRIVATE).edit();
        editor.putString("code", code);
        editor.putString("msg",msg);
        editor.apply();
    }
}
