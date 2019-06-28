package com.example.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;

public class BookFragment extends Fragment {

    @BindView(R.id.bh)
    EditText bh;
    @BindView(R.id.sm)
    EditText sm;
    @BindView(R.id.cbs)
    EditText cbs;
    @BindView(R.id.cbnf)
    EditText cbnf;
    @BindView(R.id.jg)
    EditText jg;
    @BindView(R.id.sl)
    EditText sl;
    @BindView(R.id.submit)
    Button submit;
    @BindView(R.id.delete)
    Button delete;
    Unbinder unbinder;

    public BookFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_book, container, false);
        unbinder = ButterKnife.bind(this, view);

        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId=bh.getText().toString();
                String bookName=sm.getText().toString();
                String pubH=cbs.getText().toString();
                String pubD=cbnf.getText().toString();
                String price=jg.getText().toString();
                String num=sl.getText().toString();
                if(TextUtils.isEmpty(bookId)||TextUtils.isEmpty(bookName)||TextUtils.isEmpty(pubH)||TextUtils.isEmpty(pubD)||TextUtils.isEmpty(price)||TextUtils.isEmpty(num)){
                    Toast.makeText(getActivity(),"不能为空",Toast.LENGTH_LONG).show();
                }else {
                    Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl("http://192.168.18.1:8080/Library/")
                            .build();
                    PostInterface postInterface=retrofit.create(PostInterface.class);
//                    Map<String,String> map=new HashMap<>();
//                    map.put("userId",userId);
//                    map.put("userName",userName);
//                    map.put("gender",gender);
//                    map.put("date",date);
                    Call<ResponseBody> call = postInterface.addBook(bookId,bookName,pubH,pubD,price,num);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                            Log.d("success","success");
                            Toast.makeText(getActivity(),"添加成功",Toast.LENGTH_LONG).show();
                            bh.setText("");
                            sm.setText("");
                            cbs.setText("");
                            cbnf.setText("");
                            jg.setText("");
                            sl.setText("");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Log.d("failure","fail");
                            Log.d("message1",t.getMessage());
                        }
                    });
                }
            }
        });

        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String bookId=bh.getText().toString();
                if(TextUtils.isEmpty(bookId)){
                    Toast.makeText(getActivity(),"不能为空",Toast.LENGTH_LONG).show();
                }else {
                    Retrofit retrofit=new Retrofit.Builder()
                            .baseUrl("http://192.168.18.1:8080/Library/")
                            .build();
                    PostInterface postInterface=retrofit.create(PostInterface.class);
//                    Map<String,String> map=new HashMap<>();
//                    map.put("userId",userId);
//                    map.put("userName",userName);
//                    map.put("gender",gender);
//                    map.put("date",date);
                    Call<ResponseBody> call = postInterface.deleteBook(bookId);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                            Log.d("success","success");
                            Toast.makeText(getActivity(),"删除成功",Toast.LENGTH_LONG).show();
                            bh.setText("");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Log.d("failure","fail");
                            Log.d("message1",t.getMessage());
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
