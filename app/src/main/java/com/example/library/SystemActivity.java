package com.example.library;

import android.os.Bundle;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SystemActivity extends AppCompatActivity {

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
    }
}
