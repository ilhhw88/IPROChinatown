package com.hhw.ipro.iprochinatown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;

import java.util.Locale;
import java.util.Timer;
import java.util.TimerTask;

public class StartActivity extends AppCompatActivity {
    SharedPreferences sp;
    String langage;
    Locale locale;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sp = getSharedPreferences("language", Context.MODE_PRIVATE);
        langage = sp.getString("languange_set", "CN");
        switch (langage){
            case "CN":
                locale = Locale.SIMPLIFIED_CHINESE;
                break;
            case "EN":
                locale = Locale.ENGLISH;
                break;
        }

        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, metrics);

        setContentView(R.layout.activity_start);

        final Intent intent = new Intent(this, MainActivity.class);
        Timer timer = new Timer();
        TimerTask task=new TimerTask() {
            @Override
            public void run(){
                startActivity(intent);
                finish();
            }
        };
        timer.schedule(task,1500);//此处的Delay可以是3*1000，代表三秒
    }
}
