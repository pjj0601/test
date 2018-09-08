package com.example.test;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

/**
 * Created by 潘俊杰 on 2018/9/6.
 */

public class Datashowadapter extends ArrayAdapter {
    private final int resourceId;

    public Datashowadapter(Context context, int textViewResourceId, List<Datashow> objects) {
        super(context, textViewResourceId, objects);
        resourceId = textViewResourceId;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        Datashow ds = (Datashow) getItem(position); // 获取当前项的Datashow实例
        View view = LayoutInflater.from(getContext()).inflate(resourceId, null);//实例化一个对象
        TextView pileno = (TextView) view.findViewById(R.id.pileno);//桩号
        pileno.setText(ds.getPileno());
        TextView testtype = (TextView) view.findViewById(R.id.testtype);//检测方法
        testtype.setText(ds.getTesttype());
        TextView recordcount = (TextView) view.findViewById(R.id.recordcount);//采样次数
        recordcount.setText(ds.getRecordcount());
        TextView starttime = (TextView) view.findViewById(R.id.starttime);//开始检验时间
        starttime.setText(ds.getStarttime());
        TextView createtime = (TextView) view.findViewById(R.id.createtime);//上传时间
        createtime.setText(ds.getCreatetime());
        TextView updatetime = (TextView) view.findViewById(R.id.updatetime);//最后更新时间
        updatetime.setText(ds.getUpdatetime());
        TextView creatername = (TextView) view.findViewById(R.id.creatername);//上传厂商设备标识
        creatername.setText(ds.getCreatername());
        return view;
    }
}
