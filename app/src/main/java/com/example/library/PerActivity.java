package com.example.library;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v4.content.FileProvider;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import de.hdodenhof.circleimageview.CircleImageView;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PerActivity extends AppCompatActivity implements View.OnClickListener {

    @BindView(R.id.image)
    CircleImageView image;
    @BindView(R.id.user_id)
    TextView userId;
    @BindView(R.id.user_name)
    TextView userName;
    @BindView(R.id.date)
    TextView date;
    @BindView(R.id.go_to)
    TextView goTo;

    public static final int TAKE_PHOTO = 1;
    @BindView(R.id.permission)
    TextView permission;
    private Uri imageUri;

    private SharedPreferences pref;
    private String perm;
    String uId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_per);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.hide();
        }
        ButterKnife.bind(this);
        pref = getSharedPreferences("data", MODE_PRIVATE);
       uId = pref.getString("userId", "");
        String uName = pref.getString("userName", "");
        String date1 = pref.getString("date", "");
        String permission1 = pref.getString("permission", "");
        String per = "";
        if (permission1.equals("normal")) {
            perm = "0";
            per = "普通用户";
        } else if (permission1.equals("vip")) {
            perm = "1";
            per = "系统管理员";
        } else if (permission1.equals("manage")) {
            perm = "2";
            per = "图书管理员";
        }

        userId.setText(uId);
        userName.setText(uName);
        date.setText(date1);
        permission.setText(per);
        image.setOnClickListener(this);
        goTo.setOnClickListener(this);
        image.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                File outputImage = new File(getExternalCacheDir(), "output_iamge.jpg");
                try {
                    if (outputImage.exists()) {
                        outputImage.delete();
                    }
                    outputImage.createNewFile();
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (Build.VERSION.SDK_INT >= 24) {
                    imageUri = FileProvider.getUriForFile(PerActivity.this,
                            "com.example.library.fileprovider", outputImage);
                } else {
                    imageUri = Uri.fromFile(outputImage);
                }
                Intent intent = new Intent("android.media.action.IMAGE_CAPTURE");
                intent.putExtra(MediaStore.EXTRA_OUTPUT, imageUri);
                startActivityForResult(intent, TAKE_PHOTO);
                return false;
            }
        });

        Retrofit retrofit=new Retrofit.Builder()
                .baseUrl("http://192.168.18.1:8080/Library/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        PostInterface postInterface=retrofit.create(PostInterface.class);
        Call<BorrowList> call=postInterface.getBorrow(uId);
        call.enqueue(new Callback<BorrowList>() {
            @Override
            public void onResponse(Call<BorrowList> call, Response<BorrowList> response) {
                Log.d("success","success");
                response.body().show(PerActivity.this);
                Log.d("success1",response.body().toString());
                BorrowList borrowList = response.body();
                List<BorrowList.borrow> borrows=borrowList.getBorrows();
                if(borrows!=null){
                    int i = 0;
                    SharedPreferences.Editor editor =getSharedPreferences("borrows", MODE_PRIVATE).edit();
                    for(BorrowList.borrow borrow1:borrows){
                        i++;
                        String id = Integer.toString(i);
                        String bId="bid"+id;
                        String bName="bname"+id;
                        String bDate="date"+id;
                        editor.putString(bId,borrow1.getBookId());
                        editor.putString(bName,borrow1.getBookName());
                        editor.putString(bDate,borrow1.getBorrDate());
                        editor.putInt("num",i);
                        editor.apply();
                    }
                }
            }

            @Override
            public void onFailure(Call<BorrowList> call, Throwable t) {

                Log.d("fail","success");
            }
        });

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.image: {
                Intent intent = new Intent(Intent.ACTION_PICK, null);
                intent.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
                startActivityForResult(intent, 0x2);
                break;
            }
            case R.id.go_to: {
                goTo.setTextColor(Color.parseColor("#90EE90"));

                if(perm.equals("0")){

                    Intent intent = new Intent(PerActivity.this, BorrowActivity.class);
                    startActivity(intent);
                }else if(perm.equals("1")){
                    Intent intent = new Intent(PerActivity.this, SystemActivity.class);
                    startActivity(intent);
                }else if(perm.equals("2")){
                    Intent intent = new Intent(PerActivity.this, ManagerActivity.class);
                    startActivity(intent);
                }

            }
        }
    }

    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        // TODO Auto-generated method stub
        if (requestCode == 0x2 && resultCode == RESULT_OK) {
            if (data != null) {
                image.setImageURI(data.getData());
            }
        }
        if (requestCode == TAKE_PHOTO && resultCode == RESULT_OK) {
            try {
                Bitmap bitmap = BitmapFactory.decodeStream(getContentResolver().openInputStream(imageUri));
                image.setImageBitmap(bitmap);
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
        }
        super.onActivityResult(requestCode, resultCode, data);
    }
}
