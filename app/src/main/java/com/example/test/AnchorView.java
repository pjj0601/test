package com.example.test;

/**
 * Created by 潘俊杰 on 2018/9/11.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.Random;

public class AnchorView extends LinearLayout {

//    private TextView tvAnchor;
//    private TextView tvContent;
    private TextView pileno;
    private TextView testtype;
    private TextView recordcount;
    private TextView starttime;
    private TextView createtime;
    private TextView updatetime;
    private TextView creatername;

    public AnchorView(Context context) {
        this(context, null);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public AnchorView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context) {
        View view = LayoutInflater.from(context).inflate(R.layout.layout, this, true);
//        tvAnchor = view.findViewById(R.id.tv_anchor);
//        tvContent = view.findViewById(R.id.tv_content);
        pileno = view.findViewById(R.id.pileno);
        testtype = view.findViewById(R.id.testtype);
        recordcount = view.findViewById(R.id.recordcount);
        starttime = view.findViewById(R.id.starttime);
        createtime = view.findViewById(R.id.createtime);
        updatetime = view.findViewById(R.id.updatetime);
        creatername = view.findViewById(R.id.creatername);
//        Random random = new Random();
//        int r = random.nextInt(256);
//        int g = random.nextInt(256);
//        int b = random.nextInt(256);
//        tvContent.setBackgroundColor(Color.rgb(r, g, b));
    }

//    public void setAnchorTxt(String txt) {
//        tvAnchor.setText(txt);
//    }
//
//    public void setContentTxt(String txt) {
//        tvContent.setText(txt);
//    }

    public void setPilenoTxt(String txt) {
        pileno.setText(txt);
    }
    public void setTesttypeTxt(String txt) {
        testtype.setText(txt);
    }
    public void setRecordcount(String txt) {
        recordcount.setText(txt);
    }
    public void setStarttimeTxt(String txt) {
        starttime.setText(txt);
    }
    public void setCreatetimeTxt(String txt) {
        createtime.setText(txt);
    }
    public void setUpdatetimeTxt(String txt) {
        updatetime.setText(txt);
    }
    public void setCreaternameTxt(String txt) {
        creatername.setText(txt);
    }


}
