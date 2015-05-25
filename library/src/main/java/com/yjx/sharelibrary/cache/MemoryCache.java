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
        if (hashMap.containsKey(key))
            return String.valueOf(this.hashMap.get(key));
        return null;
    }

    @Override
    public byte[] getBytes(String key) {
        if (hashMap.containsKey(key))
            return (byte[]) this.hashMap.get(key);
        return null;
    }

    @Override
    public Object getObject(String key) {
        if (hashMap.containsKey(key))
            return this.hashMap.get(key);
        return null;
    }

    @Override
    public Integer getInt(String key) {
        if (hashMap.containsKey(key)) {
            Integer value = (Integer) this.hashMap.get(key);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Long getLong(String key) {
        if (hashMap.containsKey(key)) {
            Long value = (Long) this.hashMap.get(key);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Double getDouble(String key) {
        if (hashMap.containsKey(key)) {
            Double value = (Double) this.hashMap.get(key);
            return value;
        } else {
            return null;
        }

    }

    @Override
    public Float getFloat(String key) {
        if (hashMap.containsKey(key)) {
            Float value = (Float) this.hashMap.get(key);
            return value;
        } else {
            return null;
        }
    }

    @Override
    public Boolean getBoolean(String key) {
        if (hashMap.containsKey(key)) {
            Boolean value = (Boolean) this.hashMap.get(key);
            return value;
        } else {
            return null;
        }
    }


    @Override
    public void put(String key, Object value) {
        if (value instanceof Bitmap || value instanceof String)
            this.hashMap.put(key, value);
        else if (value instanceof Integer) {
            this.hashMap.put(key, new Integer((int) value));
        } else if (value instanceof Long) {
            this.hashMap.put(key, new Long((long) value));
        } else if (value instanceof Double) {
            this.hashMap.put(key, new Double((double) value));
        } else if (value instanceof Float) {
            this.hashMap.put(key, new Float((float) value));
        } else if (value instanceof Boolean) {
            this.hashMap.put(key, new Boolean((boolean) value));
        } else
            this.hashMap.put(key, value);
    }

    @Override
    public void remove(String key) {
        if (hashMap.containsKey(key))
            this.hashMap.remove(key);
    }

    @Override
    public void clear() {
        hashMap.clear();
    }
}
