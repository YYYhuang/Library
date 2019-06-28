package com.example.library;

import java.util.Map;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;

public interface PostInterface {
    @GET("addUser")
    Call<ResponseBody> addUser(@Query("userId")String userId,@Query("userName")String userName,@Query("gender")String gender,@Query("date") String date);

    @GET("getUser")
    Call<User> getUser(@Query("userId")String userId,@Query("password")String password);

    @GET("addBook")
    Call<ResponseBody> addBook(@Query("bookId")String bookId,
                               @Query("bookName")String bookName,
                               @Query("publishHouse")String publishHouse,
                               @Query("publishDate")String publishDate,
                               @Query("price")String price,
                               @Query("num")String num);

    @GET("deleteBook")
    Call<ResponseBody> deleteBook(@Query("bookId")String bookId);

    @GET("addBorrow")
    Call<Borrow> addBorrow(@Query("userId")String userId,@Query("bookId")String bookId,@Query("date")String date);

    @GET("deleteBorrow")
    Call<Borrow> deleteBorrow(@Query("userId")String userId,@Query("bookId")String bookId);

    @GET("getBorrow")
    Call<BorrowList> getBorrow(@Query("userId")String userId);
}
