package com.hhw.ipro.iprochinatown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.drawable.BitmapDrawable;
import android.location.Location;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.method.ScrollingMovementMethod;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.TextView;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    ImageView imgMain;
    TextView btnAboutChinatown;
    TextView btnStory;
    Button btnLocation;
    Button btnMap;
    Button btnCommunity;
    Button btnAboutUs;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        initData();
    }

    @Override
    public void onStop(){
        super.onStop();
    }

    void initView(){
        imgMain = findViewById(R.id.img_main);
        btnAboutChinatown = findViewById(R.id.btn_about_chinatown);
        btnStory = findViewById(R.id.btn_story);
        btnLocation = findViewById(R.id.btn_location);
        btnMap = findViewById(R.id.btn_map);
        btnCommunity = findViewById(R.id.btn_community);
        btnAboutUs = findViewById(R.id.btn_about_us);

        imgMain.setOnClickListener(this);
        btnAboutChinatown.setOnClickListener(this);
        btnStory.setOnClickListener(this);
        btnLocation.setOnClickListener(this);
        btnMap.setOnClickListener(this);
        btnCommunity.setOnClickListener(this);
        btnAboutUs.setOnClickListener(this);
    }

    void initData(){
        //isPlayed = false;
    }

    @Override
    public void onClick(View v){
        Intent intent;
        switch (v.getId()){
            case R.id.img_main:
            case R.id.btn_story:
                intent = new Intent(MainActivity.this, StoryActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_about_chinatown:
                intent = new Intent(MainActivity.this, AboutChinatownActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_location:
                intent = new Intent(MainActivity.this, LocationActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_map:
                intent = new Intent(MainActivity.this, MapsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_about_us:
                intent = new Intent(MainActivity.this, AboutUsActivity.class);
                startActivity(intent);
                break;
            case R.id.btn_community:
                intent = new Intent(MainActivity.this, CommunityActivity.class);
                intent.putExtra("title_activity", getString(R.string.community));
                startActivity(intent);
                break;
        }
    }
}
