
##背景
想必大家一定用过 sharedpreferences 吧！就我个人而言，特别讨厌每次 put 完数据还要 commit。对 我就是这么懒！哈哈。另外，sharedpreferences 不能存类，集合和bitmap等数据！这点也让人非常不爽啊！所以，我就在这个美好的星期天撸了名为 SHARE 的工具类用来替代 sharedpreferences。

---

##项目介绍
### 整体架构
先来看一下，整体架构图（画的不好请大家见谅）:

![图片](http://ww4.sinaimg.cn/large/a174c633gw1esfdojri3oj20f009gabi.jpg)

从图中，我们可以了解到,当我们 put 数据的时候，我们同时存入到 内存和和sd卡中。读取的时候，优先从内存中获取，如果内存中没有，则从sd中获取。如果两者都没有，则使用用户自己设置的默认值！

### 代码介绍

下来看一下代码目录结构：
![图片](http://ww1.sinaimg.cn/large/a174c633gw1esffncc9wdj20hj049jrl.jpg)

- DiskLruCache:硬盘缓存的解决方案(非Google官方编写，但获得官方认证。[地址](android.googlesource.com/platform/libcore/+/jb-mr2-release/luni/src/main/java/libcore/io/DiskLruCache.java))
- Cache:接口。抽象了对数据的操作
- MemoryCache:内存缓存，实现了Cache接口
- DiskCache:硬盘缓存，同样实现了Cache接口
- Share:这个类就是我们使用的！他主要是提供了 put 和 get两种方法！其实就是对 MemoryCache 和 DiskCache 两个类的操作！

### 使用

在 Application中初始化:

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

### 近期要完成的新功能

1.增加异步 get 和 put.

2.对泛型的支持.

---
##项目地址

- [GitHub](https://github.com/yjxandroid/SHARE)

希望这个项目对大家有用。也希望多 star .同时也能多多提出修改意见！不管是对项目本身还是代码！！！！
        
