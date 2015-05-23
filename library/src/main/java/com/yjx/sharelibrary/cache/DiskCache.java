package com.yjx.sharelibrary.cache;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

import com.yjx.sharelibrary.DiskLruCache;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;

/**
 * User: YJX
 * Date: 2015-05-22
 * Time: 15:30
 * 硬盘缓存
 */
public class DiskCache implements Cache {
    private DiskLruCache diskLruCache;
    private int cacheSize;//缓存大小
    private String path;//缓存路径
    private int version;//版本号

    public DiskCache(int cacheSize, String path, int version) {
        this.cacheSize = cacheSize;
        this.path = path;
        this.version = version;
        try {
            this.diskLruCache = DiskLruCache.open(new File(path), version, 1, cacheSize);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Override
    public Bitmap getBitmap(String key) {
        byte[] bytes = getBytes(key);
        if (bytes != null) {
            return BitmapFactory.decodeByteArray(bytes, 0, bytes.length);
        }
        return null;
    }

    @Override
    public String getString(String key) {
        byte[] bytes = getBytes(key);
        if (bytes != null) {
            return new String(bytes);
        }
        return null;
    }

    @Override
    public byte[] getBytes(String key) {
        try {
            return write(this.diskLruCache.get(key).getInputStream(0));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getObject(String key) {
        try {
            ObjectInputStream objectInputStream = new ObjectInputStream(this.diskLruCache.get(key).getInputStream(0));
            return objectInputStream.readObject();
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public int getInt(String key, int val) {
        Object obj = getObject(key);
        if (obj == null)
            return val;
        else
            return ((Integer) obj).intValue();
    }

    @Override
    public long getLong(String key, long defaultvalue) {
        Object obj = getObject(key);
        if (obj == null)
            return defaultvalue;
        else
            return ((Long) obj).longValue();
    }

    @Override
    public double getDouble(String key, double defaultvalue) {
        Object obj = getObject(key);
        if (obj == null)
            return defaultvalue;
        else
            return ((Double) obj).doubleValue();
    }

    @Override
    public float getFloat(String key, float defaultvalue) {
        Object obj = getObject(key);
        if (obj == null)
            return defaultvalue;
        else
            return ((Float) obj).floatValue();
    }

    @Override
    public boolean getBoolean(String key, boolean defaultvalue) {
        Object obj = getObject(key);
        if (obj == null)
            return defaultvalue;
        else
            return ((Boolean) obj).booleanValue();
    }

    @Override
    public void put(String key, Object value) {
        try {
            DiskLruCache.Editor editor = this.diskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(0);

            if (value instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) value;
                bitmap.compress(Bitmap.CompressFormat.PNG, 100, outputStream);
            } else if (value instanceof String) {
                outputStream.write(((String) value).getBytes());
                outputStream.close();
            } else {
                ObjectOutputStream objectOutputStream = new ObjectOutputStream(outputStream);
                objectOutputStream.writeObject(value);
                outputStream.close();
                objectOutputStream.close();

            }
            editor.commit();
            this.diskLruCache.flush();

        } catch (Exception e) {
            e.printStackTrace();
        }


    }

    @Override
    public void remove(String key) {
        try {
            this.diskLruCache.remove(key);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void clear() {
        File file=new File(path);
        if (file.exists())
            file.delete();
    }

    //写入数据
    private void write(OutputStream out, InputStream in) {
        int len = 0;
        byte[] bytes = new byte[1024];
        try {
            while ((len = in.read(bytes, 0, bytes.length)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private byte[] write(InputStream in) {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        int len = 0;
        byte[] bytes = new byte[1024];
        try {
            while ((len = in.read(bytes, 0, bytes.length)) != -1) {
                out.write(bytes, 0, len);
            }
            in.close();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return out.toByteArray();
    }


}
