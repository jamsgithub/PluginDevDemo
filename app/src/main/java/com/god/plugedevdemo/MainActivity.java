package com.god.plugedevdemo;

import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Toast;

import java.io.File;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        findViewById(R.id.open_three_party_app).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openThreePartyApp();
            }
        });

    }

    private void openThreePartyApp(){
        PluginManager.getInstance().setContext(this);

//        File apkFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() , "plugproject.apk");
        File apkFile = new File(Environment.getExternalStorageDirectory().getAbsolutePath() , "plugin.apk");
        String absolutePath = apkFile.getAbsolutePath();
        boolean exists = apkFile.exists();
        if (!apkFile.exists()){
            Toast.makeText(getApplicationContext(), "安装包不存在!", Toast.LENGTH_SHORT).show();
            return;
        }

        String apkPath = apkFile.getAbsolutePath();
        PluginManager.getInstance().loadApk(apkFile.getAbsolutePath());
        PackageInfo packageInfo = PluginManager.getInstance().getPackageInfo();
        Intent intent = new Intent(this , ProxyActivity.class);
        ActivityInfo[] activities = packageInfo.activities;
        //拿到插件里的launcher对应的activity
        String activityPath = packageInfo.activities[0].name;
        intent.putExtra("className" , packageInfo.activities[0].name);
        startActivity(intent);
    }
}
