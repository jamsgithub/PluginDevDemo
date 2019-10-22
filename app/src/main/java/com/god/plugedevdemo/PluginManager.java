package com.god.plugedevdemo;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.AssetManager;
import android.content.res.Resources;

import java.io.File;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dalvik.system.DexClassLoader;

/**
 * @author Jams
 * @date 2019/10/16 11:33
 * @des todo
 * @
 * @upAuthor
 * @upDate 2019/10/16 11:33
 * @upDes todo
 */
public class PluginManager {
    private static PluginManager instance;
    private DexClassLoader mClassLoader;
    private Resources mResources;
    private PackageInfo mPackageInfo;

    private Context mContext;

    private PluginManager() {
    }

    public static PluginManager getInstance() {
        if (instance == null) {
            synchronized (PluginManager.class) {
                if (instance == null) {
                    instance = new PluginManager();
                }
            }
        }
        return instance;
    }

    public void loadApk(String dexPath){
        AssetManager assetManager = null;
        try {
            File file = mContext.getDir("dex" , Context.MODE_PRIVATE);
            mClassLoader = new DexClassLoader(dexPath , file.getAbsolutePath() , null , mContext.getClassLoader());
            PackageManager manager = mContext.getPackageManager();
            File dexFile = new File(dexPath);
            boolean exists = dexFile.exists();
            //得到插件里的所有activity
            // TODO: 2019/10/21 获取packageInfo为null
            mPackageInfo  = manager.getPackageArchiveInfo(dexPath , PackageManager.GET_ACTIVITIES);
            assetManager = AssetManager.class.newInstance();
            Method addAssetPath = AssetManager.class.getMethod("addAssetPath" , String.class);
            addAssetPath.invoke(assetManager , dexPath);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        mResources = new Resources(assetManager , mContext.getResources().getDisplayMetrics() ,
                mContext.getResources().getConfiguration());


    }

    public void setContext(Context context){
        mContext = context.getApplicationContext();
    }

    public Resources getResources(){
        return  mResources;
    }

    public DexClassLoader getClassLoader(){
        return mClassLoader;
    }

    public PackageInfo getPackageInfo(){
        return mPackageInfo;
    }


}
