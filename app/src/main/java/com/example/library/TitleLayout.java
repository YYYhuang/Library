package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import static android.content.Context.MODE_PRIVATE;

public class TitleLayout extends LinearLayout {

    private TextView text;
    private Activity activity;
    private TextView zhu;
    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        if (activity == null) {
            activity = (Activity) context;
        }

        text=findViewById(R.id.title1);

        zhu=findViewById(R.id.zhuxiao);
        zhu.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences.Editor editor=context.getSharedPreferences("data",MODE_PRIVATE).edit();
                editor.clear();
                editor.commit();
                SharedPreferences.Editor editor1=context.getSharedPreferences("borrows",MODE_PRIVATE).edit();
                editor1.clear();
                editor1.commit();
                Intent intent = new Intent(getContext(), LoginActivity.class);
                context.startActivity(intent);
                ((Activity)getContext()).finish();
            }
        });
    }
    public void setTitle(CharSequence title) {
        text.setText(title);
    }

    public void setTitle(int resId) {
        text.setText(resId);
    }
}
