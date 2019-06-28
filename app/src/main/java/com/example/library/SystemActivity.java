package com.example.library;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.HashMap;
import java.util.Map;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Response;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Retrofit;



public class SystemActivity extends AppCompatActivity implements View.OnClickListener{

    @BindView(R.id.title_layout)
    TitleLayout title;
    @BindView(R.id.gh)
    EditText gh;
    @BindView(R.id.xm)
    EditText xm;
    @BindView(R.id.xb)
    EditText xb;
    @BindView(R.id.nf)
    EditText nf;
    @BindView(R.id.send)
    Button send;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_system);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        ButterKnife.bind(this);

        title.setTitle("系统管理员");
        send.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.send:{
                String userId=gh.getText().toString();
                String userName=xm.getText().toString();
                String gender=xb.getText().toString();
                String date=nf.getText().toString();
                if(TextUtils.isEmpty(userId)||TextUtils.isEmpty(userName)||TextUtils.isEmpty(gender)||TextUtils.isEmpty(date)){
                    Toast.makeText(SystemActivity.this,"不能为空",Toast.LENGTH_LONG).show();
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
                    Call<ResponseBody> call = postInterface.addUser(userId,userName,gender,date);
                    call.enqueue(new Callback<ResponseBody>() {
                        @Override
                        public void onResponse(Call<ResponseBody> call, retrofit2.Response<ResponseBody> response) {

                            Log.d("success","success");
                            Toast.makeText(SystemActivity.this,"添加成功",Toast.LENGTH_LONG).show();
                            gh.setText("");
                            xm.setText("");
                            xb.setText("");
                            nf.setText("");
                        }

                        @Override
                        public void onFailure(Call<ResponseBody> call, Throwable t) {

                            Log.d("failure","fail");
                            Log.d("message1",t.getMessage());
                        }
                    });
                }
                break;
            }
        }
    }


}
