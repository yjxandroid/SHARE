package com.yjx.share;/**
 * Created by yjx on 15/5/24.
 */

import android.app.Application;
import android.os.Environment;

import com.yjx.sharelibrary.Share;

import java.io.File;

/**
 * User: YJX
 * Date: 2015-05-24
 * Time: 01:09
 */
public class YjxApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "sample");
        if (!file.exists()) {
            file.mkdirs();
        }
        Share.init("CACHE", 10 * 1024, file.toString());
    }
}
