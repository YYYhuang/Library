package com.example.library;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.LinearLayout;
import android.widget.TextView;

public class TitleLayout extends LinearLayout {

    private TextView text;
    private Activity activity;
    public TitleLayout(final Context context, AttributeSet attrs) {
        super(context, attrs);
        LayoutInflater.from(context).inflate(R.layout.title, this);
        if (activity == null) {
            activity = (Activity) context;
        }

        text=findViewById(R.id.title1);

    }
    public void setTitle(CharSequence title) {
        text.setText(title);
    }

    public void setTitle(int resId) {
        text.setText(resId);
    }
}
