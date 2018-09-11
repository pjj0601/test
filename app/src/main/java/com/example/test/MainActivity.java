package com.example.test;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.Toast;


public class MainActivity extends AppCompatActivity {
    EditText et1;
    EditText et2;
    Spinner xm;
    String synum;
    String xmnum;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
//        xm = findViewById(R.id.xm);
//        xm.setOnItemSelectedListener(new OnItemSelectedListener() {
//            @Override
//            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
//                xmnum = (String) xm.getItemAtPosition(i);
//                Toast.makeText(MainActivity.this,xmnum,Toast.LENGTH_LONG).show();
//                if (xmnum=="竖向抗压检测")
//                    xmnum = "ZXDZJZ_GROU";
//                else if (xmnum=="竖向抗拔检测")
//                    xmnum = "ZXDZJZ2_GROU";
//                else if (xmnum=="水平静载检测")
//                    xmnum = "ZXDZJZ3_GROU";
//                else if (xmnum=="压板载荷试验")
//                    xmnum = "ZXDJYBHZ_GROU";
//                else if (xmnum=="高应变")
//                    xmnum = "ZXGYB_GROU";
//                else if (xmnum=="低应变")
//                    xmnum = "ZXDYB_GROU";
//                else
//                    xmnum = null;
//            }
//
//            @Override
//            public void onNothingSelected(AdapterView<?> adapterView) {
//
//            }
//        });
        Button btn1=(Button)findViewById(R.id.submit_get);
        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1 = (EditText) findViewById(R.id.syNUM);
                et2 = (EditText) findViewById(R.id.xmNUM);
                synum = et1.getText().toString();
                xmnum = et2.getText().toString();
                Intent intent = new Intent(MainActivity.this,ContentActivity.class);
                if (synum.isEmpty()&&xmnum.isEmpty()){
                    AlertDialog.Builder builder  = new AlertDialog.Builder(MainActivity.this);
                    builder.setTitle("提示" ) ;
                    builder.setMessage("流水线号和监管代号不能为空。" ) ;
                    builder.setPositiveButton("返回",null );
                    builder.show();
                }else {
                    intent.putExtra("SYNUM",synum);
                    intent.putExtra("XMNUM",xmnum);
                    startActivity(intent);
                }

            }
        });
        Button btn2 = findViewById(R.id.tabtest);
        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                et1 = (EditText) findViewById(R.id.syNUM);
                et2 = (EditText) findViewById(R.id.xmNUM);
                synum = et1.getText().toString();
                xmnum = et2.getText().toString();
                Intent intent2 = new Intent(MainActivity.this,TabScrollActivity.class);
                if (synum.isEmpty()&&xmnum.isEmpty()){
                    AlertDialog.Builder builder2  = new AlertDialog.Builder(MainActivity.this);
                    builder2.setTitle("提示" ) ;
                    builder2.setMessage("流水线号和监管代号不能为空。" ) ;
                    builder2.setPositiveButton("返回",null );
                    builder2.show();
                }else {
                    intent2.putExtra("SYNUM",synum);
                    intent2.putExtra("XMNUM",xmnum);
                    startActivity(intent2);
                }
            }
        });
    }


}
