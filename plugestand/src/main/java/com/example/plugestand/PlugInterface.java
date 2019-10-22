package com.example.plugestand;

import android.app.Activity;
import android.os.Bundle;
import android.view.MotionEvent;

/**
 * @author Jams
 * @date 2019/10/16 10:54
 * @des 插件项目没有上下文、activity没有生命周期方法，这个接口是管理插件的生命周期和上下文注入，
 * 插件的Activity都需要实现这个接口
 * @
 * @upAuthor
 * @upDate 2019/10/16 10:54
 * @upDes todo
 */
public interface PlugInterface {
    //注入上下文
    void attach(Activity proxyActivity);

    void onCreate(Bundle saveInstanceState);

    void onStart();

    void onResume();

    void onStop();

    void onPause();

    void onDestroy();

    void onSaveInstanceState(Bundle outState);

    void onBackPressed();

    boolean onTouchEvent(MotionEvent event);
}
