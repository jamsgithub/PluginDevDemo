package com.example.plugproject;

import android.app.Activity;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.res.Resources;
import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.example.plugestand.PlugInterface;

/**
 * @author Jams
 * @date 2019/10/16 11:17
 * @des todo
 * @
 * @upAuthor
 * @upDate 2019/10/16 11:17
 * @upDes todo
 */
public class BaseActivity extends AppCompatActivity implements PlugInterface {
    //接收宿主的Activity，进行生命周期、函数使用。
    public Activity originalActivity;

    @Override
    public void attach(Activity proxyActivity) {
        originalActivity = proxyActivity;
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        if (originalActivity == null) {
            super.setContentView(layoutResID);
            return;
        }
        originalActivity.setContentView(layoutResID);
    }

    @Override
    public Resources getResources() {
        if (originalActivity == null) {
            return super.getResources();
        }
        return originalActivity.getResources();
    }

    @Override
    public WindowManager getWindowManager() {
        if (originalActivity == null) {
            return super.getWindowManager();
        }
        return originalActivity.getWindowManager();
    }

    @Override
    public <T extends View> T findViewById(int id) {
        if (originalActivity == null) {
            return super.findViewById(id);
        }
        return originalActivity.findViewById(id);
    }


    @Override
    public Intent getIntent() {
        if (originalActivity == null) {
            return super.getIntent();
        }
        return originalActivity.getIntent();
    }

    @Override
    public ClassLoader getClassLoader() {
        if (originalActivity == null) {
            return super.getClassLoader();
        }
        return originalActivity.getClassLoader();
    }

    @NonNull
    @Override
    public LayoutInflater getLayoutInflater() {
        if (originalActivity == null) {
            return super.getLayoutInflater();
        }
        return originalActivity.getLayoutInflater();
    }

    @Override
    public ApplicationInfo getApplicationInfo() {
        if (originalActivity == null) {
            return super.getApplicationInfo();
        }
        return originalActivity.getApplicationInfo();
    }

    @Override
    public Window getWindow() {
        if (originalActivity == null) {
            return super.getWindow();
        }
        return originalActivity.getWindow();
    }

    @Override
    public void finish() {
        if (originalActivity == null) {
            super.finish();
            return;
        }
        originalActivity.finish();
    }

    @Override
    public void startActivity(Intent intent) {
        String className = intent.getComponent().getClassName();
        Intent intent1 = new Intent();
        intent1.putExtra("className" , className);
        //调用宿主的ProxyActivity的startActivity打开页面
        originalActivity.startActivity(intent1);
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onStop() {
        super.onStop();
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }

    @Override
    public void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
    }
}
