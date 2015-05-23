package com.yjx.sharelibrary.cache;/**
 * Created by yjx on 15/5/22.
 */

import android.graphics.Bitmap;

import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;

/**
 * User: YJX
 * Date: 2015-05-22
 * Time: 15:09
 * 内存缓存
 */
public class MemoryCache implements Cache {
    private ConcurrentMap<String, Object> hashMap;
    public MemoryCache() {
        hashMap = new ConcurrentHashMap<String, Object>();
    }



    @Override
    public Bitmap getBitmap(String key) {
        return (Bitmap) this.hashMap.get(key);
    }

    @Override
    public String getString(String key) {

        return String.valueOf(this.hashMap.get(key));
    }

    @Override
    public byte[] getBytes(String key) {
        return (byte[]) this.hashMap.get(key);
    }

    @Override
    public Object getObject(String key) {
        return this.hashMap.get(key);
    }

    @Override
    public int getInt(String key,int val) {
        Integer integer = (Integer) this.hashMap.get(key);
        if (integer==null)
            return integer;
        return integer.intValue();
    }

    @Override
    public long getLong(String key, long defaultvalue) {
        Long integer = (Long) this.hashMap.get(key);
        if (integer==null)
            return integer;
        return integer.longValue();
    }

    @Override
    public double getDouble(String key, double defaultvalue) {
        Double integer = (Double) this.hashMap.get(key);
        if (integer==null)
            return integer;
        return integer.doubleValue();
    }

    @Override
    public float getFloat(String key, float defaultvalue) {
        Float integer = (Float) this.hashMap.get(key);
        if (integer==null)
            return integer;
        return integer.floatValue();
    }

    @Override
    public boolean getBoolean(String key, boolean defaultvalue) {
        Boolean integer = (Boolean) this.hashMap.get(key);
        if (integer==null)
            return integer;
        return integer.booleanValue();
    }


    @Override
    public void put(String key, Object value) {
        if (value instanceof Bitmap|| value instanceof String)
        this.hashMap.put(key, value);
        else if(value instanceof Integer){
            this.hashMap.put(key, new Integer((int) value));
        } else if(value instanceof Long){
            this.hashMap.put(key, new Long((long) value));
        } else if(value instanceof Double){
            this.hashMap.put(key, new Double((double) value));
        } else if(value instanceof Float){
            this.hashMap.put(key, new Float((float) value));
        } else if(value instanceof Boolean){
            this.hashMap.put(key,new Boolean((boolean)value));
        }else
            this.hashMap.put(key, value);
    }

    @Override
    public void remove(String key) {
        this.hashMap.remove(key);
    }
}
