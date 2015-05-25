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
            DiskLruCache.Snapshot snapshot = this.diskLruCache.get(key);
            if (snapshot != null && snapshot.getInputStream(0) != null)
                return write(this.diskLruCache.get(key).getInputStream(0));
            else
                return null;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Object getObject(String key) {
        try {
            DiskLruCache.Snapshot snapshot = this.diskLruCache.get(key);
            if (snapshot != null && snapshot.getInputStream(0) != null) {
                ObjectInputStream objectInputStream = new ObjectInputStream(this.diskLruCache.get(key).getInputStream(0));
                return objectInputStream.readObject();
            } else {
                return null;
            }
        } catch (Exception e) {
            return null;
        }
    }

    @Override
    public Integer getInt(String key) {
        Object obj = getObject(key);
        if (obj != null)
            return ((Integer) obj);
        return null;
    }

    @Override
    public Long getLong(String key) {

        Object obj = getObject(key);
        if (obj != null)
            return ((Long) obj);
        return null;
    }

    @Override
    public Double getDouble(String key) {
        Object obj = getObject(key);
        if (obj != null)
            return ((Double) obj);
        return null;
    }

    @Override
    public Float getFloat(String key) {
        Object obj = getObject(key);
        if (obj != null)
            return ((Float) obj);
        return null;
    }

    @Override
    public Boolean getBoolean(String key) {
        Object obj = getObject(key);
        if (obj != null)
            return ((Boolean) obj);
        return null;
    }

    @Override
    public void put(String key, Object value) {
        try {
            DiskLruCache.Editor editor = this.diskLruCache.edit(key);
            OutputStream outputStream = editor.newOutputStream(0);
            if (value instanceof Bitmap) {
                Bitmap bitmap = (Bitmap) value;
                outputStream.write(Bitmap2Bytes(bitmap));
                outputStream.close();
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
        File file = new File(path);
        if (file.exists()) {
            File[] files = file.listFiles();
            for (File f : files) {
                f.delete();
            }
        }
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
    private  byte[] Bitmap2Bytes(Bitmap bm) {
        ByteArrayOutputStream baos =new ByteArrayOutputStream();
        bm.compress(Bitmap.CompressFormat.PNG, 100, baos);
        try {
            baos.flush();
            baos.close();
            if (!bm.isRecycled()){
                bm.recycle();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return baos.toByteArray();
    }


}
