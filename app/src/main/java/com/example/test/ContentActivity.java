package com.example.test;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.loopj.android.http.*;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import cz.msebera.android.httpclient.Header;



/**
 * Created by 潘俊杰 on 2018/9/3.
 */

public class ContentActivity extends AppCompatActivity{
    private static List<Datashow> datashowList = new ArrayList<Datashow>();

    String t;
    String pileno;
    String testtype;
    String recordcount;
    String starttime;
    String createtime;
    String updatetime;
    String creatername;
    String T;
    long time;
    Date date= new Date();
    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.content_main);
        datashowList.clear();
        final TextView test = findViewById(R.id.test);
        String synum = getIntent().getStringExtra("SYNUM");
        String xmnum = getIntent().getStringExtra("XMNUM");
        AsyncHttpClient  client = new AsyncHttpClient();
        RequestParams params = new RequestParams();
        String url = "http://27.45.232.195:8101/app/zhuang/getDetail?syNum="+synum+"&xmNum="+xmnum;
        client.get( url, params,new JsonHttpResponseHandler() {
            public void onSuccess(int statusCode, Header[] headers, JSONObject json) {
                // Response is automatically parsed into a JSONArray
                // json.getJSONObject(0).getLong("id");
                // Here we want to process the json data into Java models.
                try {
                    JSONArray d = json.getJSONArray("data");
//                    String t =d.getJSONObject(0).getString("jingZaiList");
                    JSONArray jingZaiList =d.getJSONObject(0).getJSONArray("jingZaiList");

                    t = jingZaiList.getJSONObject(0).getString("serialno");
                    test.setText("流水线："+t);
                    for (int i=0;i<d.length();i++){
                        jingZaiList=d.getJSONObject(i).getJSONArray("jingZaiList");
                        for (int j=0;j<jingZaiList.length();j++){
                            pileno = jingZaiList.getJSONObject(j).getString("pileno");
                            testtype = jingZaiList.getJSONObject(j).getString("testtype");
                            recordcount = jingZaiList.getJSONObject(j).getString("recordcount");
                            time = jingZaiList.getJSONObject(j).getLong("starttime");
                            date.setTime(time);
                            starttime = sdf.format(date);
                            T = jingZaiList.getJSONObject(j).getString("createtime");
//                            Toast.makeText(ContentActivity.this,T,Toast.LENGTH_LONG).show();
                            if (!T .equals("null") ){
                                time = jingZaiList.getJSONObject(j).getLong("createtime");
                                date.setTime(time);
                                createtime = sdf.format(date);
                            }else {
                                createtime = null;
                            }

                            time = jingZaiList.getJSONObject(j).getLong("updatetime");
                            date.setTime(time);
                            updatetime = sdf.format(date);
                            creatername = jingZaiList.getJSONObject(j).getString("creatername");
                            datashowList.add(new Datashow("桩号："+pileno,testtype,recordcount,starttime,createtime,updatetime,creatername));
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
                Datashowadapter dsa = new Datashowadapter(ContentActivity.this,R.layout.layout,datashowList);
                ListView listView = findViewById(R.id.list_view);
                dsa.notifyDataSetChanged();
                listView.setAdapter(dsa);
                dsa.notifyDataSetChanged();
            }

            public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject e)  {
                // Handle the failure and alert the user to retry
                Log.e("ERROR", e.toString());
                Toast.makeText(ContentActivity.this,"查询失败，请检查网络，如网络无问题请与管理员联系。",Toast.LENGTH_LONG).show();
            }
        });


    }



}
