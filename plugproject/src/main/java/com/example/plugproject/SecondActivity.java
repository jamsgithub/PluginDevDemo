package com.example.plugproject;

import android.os.Bundle;

/**
 * 因为插件apk没有安装，所以activity不用在清单文件中注册
 */
public class SecondActivity extends BaseActivity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
    }
}
