package com.god.plugedevdemo;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.example.plugestand.PlugInterface;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

/**
 * @author Jams
 * @date 2019/10/16 11:32
 * @des 代理类.这个类的打开模式必须是standard
 * @
 * @upAuthor
 * @upDate 2019/10/16 11:32
 * @upDes todo
 */
public class ProxyActivity extends AppCompatActivity {

    private String className;
    private PlugInterface mPlugInterface;
    private final static String CLASS_NAME = "className";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        className = getIntent().getStringExtra(CLASS_NAME);
        try {
            Class<?> activityClass = getClassLoader().loadClass(className);
            Constructor constructor = activityClass.getConstructor(new Class[]{});
            Object instance = constructor.newInstance(new Object[]{});
            //插件标准
            mPlugInterface = (PlugInterface) instance;
            //注入宿主的上下文
            mPlugInterface.attach(this);
            //打开插件工程，传递参数
            Bundle bundle = new Bundle();
            mPlugInterface.onCreate(bundle);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    @Override
    public Resources getResources() {
        return PluginManager.getInstance().getResources();
    }

    @Override
    public ClassLoader getClassLoader() {
        return PluginManager.getInstance().getClassLoader();
    }

    /**
     * 在插件中使用startActivity打开插件中的Activity时，反调用这个方法打开对应的插件中的Activity
     * @param intent
     */
    @Override
    public void startActivity(Intent intent) {
        //接受从插件传过来的Activity的全类名
        String className1 = intent.getStringExtra(CLASS_NAME);
        Intent intent1 = new Intent(this , ProxyActivity.class);
        intent1.putExtra(CLASS_NAME , className1);
        super.startActivity(intent1);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mPlugInterface.onStart();
    }

    @Override
    protected void onResume() {
        super.onResume();
        mPlugInterface.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mPlugInterface.onPause();
    }

    @Override
    protected void onStop() {
        super.onStop();
        mPlugInterface.onStop();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mPlugInterface.onDestroy();
    }
}
