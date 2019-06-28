package com.example.library;

import android.content.SharedPreferences;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class BorrowActivity extends AppCompatActivity {

    @BindView(R.id.title_layout)
    TitleLayout title;

    private List<Book> bookList=new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_borrow);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        ButterKnife.bind(this);

        title.setTitle("借阅情况");

        inits();
        RecyclerView recyclerView=findViewById(R.id.recycler_view1);
        LinearLayoutManager layoutManager=new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        BookAdapter adapter=new BookAdapter(bookList);
        recyclerView.setAdapter(adapter);
    }
    public void inits() {
        SharedPreferences pref = getSharedPreferences("borrows", MODE_PRIVATE);
        int count = pref.getInt("num", 0);
        for (int j = 1; j <= count; j++) {
            String id = Integer.toString(j);
            String bId="bid"+id;
            String bName="bname"+id;
            String bDate="date"+id;
            String bookId = pref.getString(bId, "");
            String bookName = pref.getString(bName, "");
            String borrowDate=pref.getString(bDate,"");
            Book book=new Book(bookId,bookName,borrowDate);
            bookList.add(book);
        }
    }
}
