package com.example.library;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

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

        return view;

    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }
}
