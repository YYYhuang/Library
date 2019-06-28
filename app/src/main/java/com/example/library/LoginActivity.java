package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class LoginActivity extends AppCompatActivity {

    @BindView(R.id.account)
    EditText account;
    @BindView(R.id.password)
    EditText password;
    @BindView(R.id.login)
    Button login;

    private  SharedPreferences pref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar=getSupportActionBar();
        if(actionBar!=null){
            actionBar.hide();
        }
        ButterKnife.bind(this);
        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String acco=account.getText().toString();
                String pass=password.getText().toString();
                if(TextUtils.isEmpty(acco)||TextUtils.isEmpty(pass)){
                    Toast.makeText(LoginActivity.this,"不能为空",Toast.LENGTH_LONG).show();
                }else {
                    getRetrofit(acco,pass);
                }
            }
        });
    }
    public void getRetrofit(String acc,String pass){
        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.18.1:8080/Library/").addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface postInterface=retrofit.create(PostInterface.class);
//                    Map<String,String> map=new HashMap<>();
//                    map.put("userId",userId);
//                    map.put("userName",userName);
//                    map.put("gender",gender);
//                    map.put("date",date);
        Call<User> call = postInterface.getUser(acc,pass);
        call.enqueue(new Callback<User>() {
            @Override
            public void onResponse(Call<User> call, Response<User> response) {
                response.body().show(LoginActivity.this);
                //判断是否注册
                 pref=getSharedPreferences("data",MODE_PRIVATE);
                String code=pref.getString("code","");
                if(code.equals("0")){
                    Toast.makeText(LoginActivity.this,"密码错误",Toast.LENGTH_LONG).show();
                }else if(code.equals("1")){
                    response.body().getData(LoginActivity.this);
                    Intent intent = new Intent(LoginActivity.this, PerActivity.class);
                    startActivity(intent);
                    finish();
                }else if(code.equals("2")){
                    Toast.makeText(LoginActivity.this,"用户不存在",Toast.LENGTH_LONG).show();
                }
            }

            @Override
            public void onFailure(Call<User> call, Throwable t) {

            }
        });
    }

}
