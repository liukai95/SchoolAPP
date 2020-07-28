package com.flower.youth;

import android.app.Application;
import android.content.Context;
import android.provider.SyncStateContract;

import com.flower.youth.util.Constant;
import com.nostra13.universalimageloader.cache.disc.impl.UnlimitedDiskCache;
import com.nostra13.universalimageloader.cache.disc.naming.Md5FileNameGenerator;
import com.nostra13.universalimageloader.core.DisplayImageOptions;
import com.nostra13.universalimageloader.core.ImageLoader;
import com.nostra13.universalimageloader.core.ImageLoaderConfiguration;
import com.nostra13.universalimageloader.core.display.RoundedBitmapDisplayer;

import java.io.File;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by CJX on 2017/7/31.
 */

public class MyApplication extends Application {

    private static Context context = null;

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    private static DisplayImageOptions options = null;

    @Override
    public void onCreate() {
        super.onCreate();

        context = getApplicationContext();

        //ImageLoader配置参数
        initImageLoader();
    }

    private void initImageLoader() {
        File file = new File(Constant.LOCAL_CACHE_PATH + "/picture_cache");
        if (!file.exists()){
            file.mkdir();
        }

        ImageLoaderConfiguration configuration = new ImageLoaderConfiguration.Builder(this)
                .diskCacheFileNameGenerator(new Md5FileNameGenerator())
                .diskCacheFileCount(100)
                .diskCache(new UnlimitedDiskCache(file))
                .threadPoolSize(10)
                .build();
        ImageLoader.getInstance().init(configuration);

        options = new DisplayImageOptions.Builder()
                .cacheInMemory(true)                               //启用内存缓存
                .cacheOnDisk(true)                                 //启用外存缓存
                .considerExifParams(true)                          //启用EXIF和JPEG图像格式
                .build();
    }


    public static Context getContext(){
        return context;
    }

    public static DisplayImageOptions getOptions(){
        return options;
    }

    public static ExecutorService getThreadPool(){
        return threadPool;
    }
}
