package com.yjx.sharelibrary.cache;

import android.graphics.Bitmap;

/**
 * Created by yjx on 15/5/22.
 * 缓存接口
 */
public interface Cache {

    Bitmap getBitmap(String key);

    Object getString(String key);

    byte[] getBytes(String key);

    Object getObject(String key);

    int getInt(String key, int defaultvalue);

    long getLong(String key, long defaultvalue);

    double getDouble(String key, double defaultvalue);

    float getFloat(String key, float defaultvalue);

    boolean getBoolean(String key, boolean defaultvalue);


    void put(String key, Object value);

    void remove(String key);

    void clear();
}
