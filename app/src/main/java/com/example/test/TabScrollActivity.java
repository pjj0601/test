package com.example.test;

import android.content.Context;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Toast;

import com.loopj.android.http.AsyncHttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class TabScrollActivity extends AppCompatActivity {

    private TabLayout tabLayout;
    private CustomScrollView scrollView;
    private LinearLayout container;
////    private String[] tabTxt = {"客厅", "卧室", "餐厅", "书房", "阳台", "儿童房"};
//    //内容块view的集合
    private List<AnchorView> anchorList = new ArrayList<>();
    //判读是否是scrollview主动引起的滑动，true-是，false-否，由tablayout引起的
    private boolean isScroll;
    //记录上一次位置，防止在同一内容块里滑动 重复定位到tablayout
    private int lastPos = 0;
    //监听判断最后一个模块的高度，不满一屏时让最后一个模块撑满屏幕
    private ViewTreeObserver.OnGlobalLayoutListener listener;

    private int length;
    private String t;
    private String pileno;
    private String testtype;
    private String recordcount;
    private String starttime;
    private String createtime;
    private String updatetime;
    private String creatername;
    private String T;
    private long time;
    private Date date= new Date();
    private SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tab_scroll);
        tabLayout = findViewById(R.id.tablayout);
        scrollView = findViewById(R.id.scrollView);
        container = findViewById(R.id.container);

//        //模拟数据，填充scrollview
//        for (int i = 0; i < tabTxt.length; i++) {
//            AnchorView anchorView = new AnchorView(this);
//            anchorView.setAnchorTxt(tabTxt[i]);
//            anchorView.setContentTxt(tabTxt[i]);
//            anchorList.add(anchorView);
//            container.addView(anchorView);
//        }
//
//        //tablayout设置标签
//        for (int i = 0; i < tabTxt.length; i++) {
//            tabLayout.addTab(tabLayout.newTab().setText(tabTxt[i]));
//        }
        //测试

        String synum = getIntent().getStringExtra("SYNUM");
        String xmnum = getIntent().getStringExtra("XMNUM");
        AsyncHttpClient client = new AsyncHttpClient();
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
                    setTitle("流水线："+t);
                    length = jingZaiList.length()+1;
                    Toast.makeText(TabScrollActivity.this,String.valueOf(length),Toast.LENGTH_LONG).show();
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
                            tabLayout.addTab(tabLayout.newTab().setText("桩号："+pileno));
                            AnchorView anchorView = new AnchorView(TabScrollActivity.this);
                            anchorView.setPilenoTxt("桩号："+pileno);
                            anchorView.setTesttypeTxt(testtype);
                            anchorView.setRecordcount(recordcount);
                            anchorView.setStarttimeTxt(starttime);
                            anchorView.setCreaternameTxt(createtime);
                            anchorView.setUpdatetimeTxt(updatetime);
                            anchorView.setCreaternameTxt(creatername);
                            anchorList.add(anchorView);
                            container.addView(anchorView);
                            listener = new ViewTreeObserver.OnGlobalLayoutListener() {
                                @Override
                                public void onGlobalLayout() {
                                    int screenH = getScreenHeight();
                                    int statusBarH = getStatusBarHeight(TabScrollActivity.this);
                                    int tabH = tabLayout.getHeight();
                                    //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度-内容container的padding 16dp
                                    int lastH = screenH - statusBarH - tabH - 16 * 3;
                                    AnchorView lastView = anchorList.get(anchorList.size() - 1);
                                    //当最后一个view 高度小于内容块高度时，设置其高度撑满
                                    if (lastView.getHeight() < lastH) {
                                        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                                        params.height = lastH;
                                        lastView.setLayoutParams(params);
                                    }
                                    container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);

                                }
                            };
                            container.getViewTreeObserver().addOnGlobalLayoutListener(listener);


                            tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
                                @Override
                                public void onTabSelected(TabLayout.Tab tab) {
                                    //点击标签，使scrollview滑动，isScroll置false
                                    isScroll = false;
                                    int pos = tab.getPosition();
                                    int top = anchorList.get(pos).getTop();
                                    scrollView.smoothScrollTo(0, top);
                                }

                                @Override
                                public void onTabUnselected(TabLayout.Tab tab) {

                                }

                                @Override
                                public void onTabReselected(TabLayout.Tab tab) {

                                }
                            });

                            scrollView.setOnTouchListener(new View.OnTouchListener() {
                                @Override
                                public boolean onTouch(View v, MotionEvent event) {
                                    //当滑动由scrollview触发时，isScroll 置true
                                    if (event.getAction() == MotionEvent.ACTION_DOWN) {
                                        isScroll = true;
                                    }
                                    return false;
                                }
                            });

                            scrollView.setCallbacks(new CustomScrollView.Callbacks() {
                                @Override
                                public void onScrollChanged(int x, int y, int oldx, int oldy) {
                                    if (isScroll) {
                                        for (int i = length - 1; i >= 0; i--) {
                                            //根据滑动距离，对比各模块距离父布局顶部的高度判断
                                            if (y > anchorList.get(i).getTop() - 10) {
                                                setScrollPos(i);
                                                break;
                                            }
                                        }
                                    }
                                }
                            });
                        }
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }


            }

            public void onFailure(int statusCode, Header[] headers, Throwable t, JSONObject e)  {
                // Handle the failure and alert the user to retry
                Log.e("ERROR", e.toString());
                Toast.makeText(TabScrollActivity.this,"查询失败，请检查网络，如网络无问题请与管理员联系。",Toast.LENGTH_LONG).show();
            }
        });

//        listener = new ViewTreeObserver.OnGlobalLayoutListener() {
//            @Override
//            public void onGlobalLayout() {
//                int screenH = getScreenHeight();
//                int statusBarH = getStatusBarHeight(TabScrollActivity.this);
//                int tabH = tabLayout.getHeight();
//                //计算内容块所在的高度，全屏高度-状态栏高度-tablayout的高度-内容container的padding 16dp
//                int lastH = screenH - statusBarH - tabH - 16 * 3;
//                AnchorView lastView = anchorList.get(anchorList.size() - 1);
//                //当最后一个view 高度小于内容块高度时，设置其高度撑满
//                if (lastView.getHeight() < lastH) {
//                    LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
//                    params.height = lastH;
//                    lastView.setLayoutParams(params);
//                }
//                container.getViewTreeObserver().removeOnGlobalLayoutListener(listener);
//
//            }
//        };
//        container.getViewTreeObserver().addOnGlobalLayoutListener(listener);
//
//
//        tabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
//            @Override
//            public void onTabSelected(TabLayout.Tab tab) {
//                //点击标签，使scrollview滑动，isScroll置false
//                isScroll = false;
//                int pos = tab.getPosition();
//                int top = anchorList.get(pos).getTop();
//                scrollView.smoothScrollTo(0, top);
//            }
//
//            @Override
//            public void onTabUnselected(TabLayout.Tab tab) {
//
//            }
//
//            @Override
//            public void onTabReselected(TabLayout.Tab tab) {
//
//            }
//        });
//
//        scrollView.setOnTouchListener(new View.OnTouchListener() {
//            @Override
//            public boolean onTouch(View v, MotionEvent event) {
//                //当滑动由scrollview触发时，isScroll 置true
//                if (event.getAction() == MotionEvent.ACTION_DOWN) {
//                    isScroll = true;
//                }
//                return false;
//            }
//        });
//
//        scrollView.setCallbacks(new CustomScrollView.Callbacks() {
//            @Override
//            public void onScrollChanged(int x, int y, int oldx, int oldy) {
//                if (isScroll) {
//                    for (int i = length - 1; i >= 0; i--) {
//                        //根据滑动距离，对比各模块距离父布局顶部的高度判断
//                        if (y > anchorList.get(i).getTop() - 10) {
//                            setScrollPos(i);
//                            break;
//                        }
//                    }
//                }
//            }
//        });

    }

    //tablayout对应标签的切换
    private void setScrollPos(int newPos) {
        if (lastPos != newPos) {
            //该方法不会触发tablayout 的onTabSelected 监听
            tabLayout.setScrollPosition(newPos, 0, true);
        }
        lastPos = newPos;
    }

    private int getScreenHeight() {
        return getResources().getDisplayMetrics().heightPixels;
    }

    public int getStatusBarHeight(Context context) {
        int result = 0;
        int resourceId = context.getResources()
                .getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            result = context.getResources().getDimensionPixelSize(resourceId);
        }
        return result;
    }

}
