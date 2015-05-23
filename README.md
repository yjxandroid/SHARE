# SHARE
一个更简单，更快速的存储数据方式！用来代替 Android SharedPreferences 的工具类！同时支持Bitmap,Object的等任意类型的存储！
#使用
只要在 Application中初始化:

    @Override
    public void onCreate() {
        super.onCreate();
        File file = new File(Environment.getExternalStorageDirectory().toString() + File.separator + "sample");
        if (!file.exists()) {
            file.mkdirs();
        }
        Share.init("CACHE", 10 * 1024, file.toString());
    }
之后，你就可以任意的使用它了！

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
---
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
        .....
        
使用就是如此简单！        
        
        
