package com.yjx.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjx.sharelibrary.Share;

import java.io.File;


public class MainActivity extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "sample");
        if (!file.exists()) {
            file.mkdirs();
        }
        Share.init("CACHE", 10 * 1024, file.toString(), new Handler());
        TextView textView1 = (TextView) findViewById(R.id.tv);
        TextView textView2 = (TextView) findViewById(R.id.tv1);
        TextView textView3 = (TextView) findViewById(R.id.tv2);
        TextView textView4 = (TextView) findViewById(R.id.tv3);
        TextView textView5 = (TextView) findViewById(R.id.tv4);
        TextView textView6 = (TextView) findViewById(R.id.tv5);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        People people=new People(20,"yjx");
        Share.putString("str", "你好啊");
        Share.putInt("int", 1);
        Share.putBoolean("boolean", true);
        Share.putDouble("double", 2.1d);
        Share.putLong("long", 20000);
        Share.putFloat("float", 2.2f);
        Share.putObject("obj", people);
        Bitmap bitmap= BitmapFactory.decodeResource(getResources(),R.drawable.dd);
        Share.putBitmap("bitmap", bitmap);
        textView1.setText(Share.getString("str"));
        textView2.setText(Share.getInt("int", 0)+"");
        textView3.setText(Share.getDouble("double", 0.0d)+"");
        textView4.setText(Share.getFloat("float", 0.0f)+"");
        textView5.setText(Share.getLong("long",0)+"");
        People pp= (People) Share.getObject("obj");
        textView6.setText(pp.getAge()+"  "+pp.getName());
        iv.setImageBitmap(Share.getBitmap("bitmap"));

        Share.clearData();
        textView2.setText(Share.getInt("int", 0) + "");


    }


}
