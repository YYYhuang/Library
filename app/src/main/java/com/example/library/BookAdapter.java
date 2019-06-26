package com.example.library;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {

    private List<Book> mbooks;

    public BookAdapter(List<Book> books){
        mbooks=books;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view= LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.book_item,viewGroup,false);
        ViewHolder holder=new ViewHolder(view);
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder, int i) {
        Book book=mbooks.get(i);
        viewHolder.bh1.setText(book.getNum());
        viewHolder.sm1.setText(book.getBookName());
        viewHolder.cbs1.setText(book.getCbs());
    }

    @Override
    public int getItemCount() {
        return mbooks.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder{

        TextView bh1;
        TextView sm1;
        TextView cbs1;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            bh1=itemView.findViewById(R.id.bh1);
            sm1=itemView.findViewById(R.id.sm1);
            cbs1=itemView.findViewById(R.id.cbs1);
        }
    }
}
