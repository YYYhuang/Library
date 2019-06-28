package com.example.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.text.SimpleDateFormat;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class BorrowFragment extends Fragment {

    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.delete)
    Button delete;
    Unbinder unbinder;
    @BindView(R.id.yhgh)
    EditText yhgh;
    @BindView(R.id.tsbh)
    EditText tsbh;
    private String date;
    public BorrowFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_borrow, container, false);
        unbinder = ButterKnife.bind(this, view);
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
        date=sdf.format(new java.util.Date());
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId= yhgh.getText().toString();
                String bookId=tsbh.getText().toString();

                if (TextUtils.isEmpty(bookId)||TextUtils.isEmpty(userId)) {
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_LONG).show();
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.18.1:8080/Library/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    PostInterface postInterface = retrofit.create(PostInterface.class);

                    Call<Borrow> call = postInterface.addBorrow(userId,bookId,date);
                    call.enqueue(new Callback<Borrow>() {
                        @Override
                        public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                            String result=response.body().show();
                            if(result.equals("0")){
                                Toast.makeText(getActivity(),"用户不存在",Toast.LENGTH_LONG).show();
                            }else if(result.equals("1")){
                                Toast.makeText(getActivity(),"书不存在",Toast.LENGTH_LONG).show();
                            }else if(result.equals("2")){
                                Toast.makeText(getActivity(),"借书成功",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Borrow> call, Throwable t) {

                        }
                    });

                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userId= yhgh.getText().toString();
                String bookId=tsbh.getText().toString();

                if (TextUtils.isEmpty(bookId)||TextUtils.isEmpty(userId)) {
                    Toast.makeText(getActivity(), "不能为空", Toast.LENGTH_LONG).show();
                } else {
                    Retrofit retrofit = new Retrofit.Builder()
                            .baseUrl("http://192.168.18.1:8080/Library/")
                            .addConverterFactory(GsonConverterFactory.create())
                            .build();
                    PostInterface postInterface = retrofit.create(PostInterface.class);

                    Call<Borrow> call = postInterface.deleteBorrow(userId,bookId);
                    call.enqueue(new Callback<Borrow>() {
                        @Override
                        public void onResponse(Call<Borrow> call, Response<Borrow> response) {
                            String result=response.body().show();
                            if(result.equals("0")){
                                Toast.makeText(getActivity(),"记录不存在",Toast.LENGTH_LONG).show();
                            }else if(result.equals("1")){
                                Toast.makeText(getActivity(),"还书成功",Toast.LENGTH_LONG).show();
                            }
                        }

                        @Override
                        public void onFailure(Call<Borrow> call, Throwable t) {

                        }
                    });

                }
            }

        });
        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
