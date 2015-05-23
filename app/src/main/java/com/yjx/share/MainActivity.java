package com.yjx.share;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.widget.ImageView;
import android.widget.TextView;

import com.yjx.sharelibrary.Share;

import java.util.ArrayList;
import java.util.List;


public class MainActivity extends ActionBarActivity {
    List<String> items = new ArrayList<String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        items.add("item1");
        items.add("item2");
        items.add("item3");
        items.add("item4");
        items.add("item5");
        TextView textView1 = (TextView) findViewById(R.id.tv);
        TextView textView2 = (TextView) findViewById(R.id.tv1);
        TextView textView3 = (TextView) findViewById(R.id.tv2);
        TextView textView4 = (TextView) findViewById(R.id.tv3);
        TextView textView5 = (TextView) findViewById(R.id.tv4);
        TextView textView6 = (TextView) findViewById(R.id.tv5);
        ImageView iv = (ImageView) findViewById(R.id.iv);
        People people = new People(20, "yjx");
        //设置字符串
        Share.putString("str", "你好啊");
        //设置int
        Share.putInt("int", 1);
        //设置boolean
        Share.putBoolean("boolean", true);
        //设置double
        Share.putDouble("double", 2.1d);
        //设置long
        Share.putLong("long", 20000);
        //设置flot
        Share.putFloat("float", 2.2f);
        //设置类
        Share.putObject("obj", people);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.dd);
        //设置bitmap
        Share.putBitmap("bitmap", bitmap);
        //设置集合
        Share.putObject("list", items);

        //得到字符串
        String str=Share.getString("str");
        //得到double
        double dd=Share.getDouble("double", 0.0d);
        //得到int
        int value=Share.getInt("int", 0);
        //得到float
        float ff=Share.getFloat("float", 0.0f);
        //得到bitmap
        Bitmap map=Share.getBitmap("bitmap");
        //得到集合
        List<String> copy= (List<String>) Share.getObject("list");

        textView1.setText(Share.getString("str"));
        textView2.setText(Share.getInt("int", 0) + "");
        textView3.setText(Share.getDouble("double", 0.0d) + "");
        textView4.setText(Share.getFloat("float", 0.0f) + "");
        textView5.setText(Share.getLong("long", 0) + "");
        People pp = (People) Share.getObject("obj");
        textView6.setText(pp.getAge() + "  " + pp.getName());
        iv.setImageBitmap(Share.getBitmap("bitmap"));

//        Share.clearData();

//        textView2.setText(Share.getInt("int", 0) + "");

        Share.putObject("list", items);


        for (int i=0;i<copy.size();i++){
            System.out.print(copy.get(i));
        }


    }


}
