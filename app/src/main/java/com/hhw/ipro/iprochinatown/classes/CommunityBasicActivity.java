package com.hhw.ipro.iprochinatown.classes;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.hhw.ipro.iprochinatown.MainActivity;
import com.hhw.ipro.iprochinatown.R;
import com.hhw.ipro.iprochinatown.classes.LocationAdapter;
import com.hhw.ipro.iprochinatown.classes.LocationItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommunityBasicActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    protected final int chineseAmericanMuseum = 0;
    protected final int pingTomPark = 1;
    protected final int chinatownLibrary = 2;
    protected final int stThereseChurch = 3;
    protected final int puiTakCententer = 4;
    protected final int chinatownGate = 5;
    protected final int nineDragonWall = 6;
    protected final int chineseChristianCatholicChurch = 7;
    protected final int chinatownSquare = 8;
    protected final int kentCenter = 9;
    protected final int kamLLiuBuilding = 10;

    public ImageView btnBack;
    public TextView titleCommunity;
    public ImageView btnLanguage;
    public RecyclerView recyclerView;
    public CommunityAdapter rvAdapter;

    public List<String> strings;
    public String titleActivity;

    public View.OnClickListener onClickListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community);

        sp = getSharedPreferences("language", Context.MODE_PRIVATE);
        editor = sp.edit();

        initData();
        initView();
    }

    public void initView(){
        btnBack = findViewById(R.id.locations_back);
        btnBack.setOnClickListener(this);
        titleCommunity = findViewById(R.id.community_title);
        btnLanguage = findViewById(R.id.locations_language);
        btnLanguage.setOnClickListener(this);

        recyclerView = findViewById(R.id.rv_list);
        //rvAdapter = new CommunityAdapter(this, strings);

        recyclerView.setAdapter(rvAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LocationItemDecoration());
    }

    public void initData(){}


    @Override
    public void onClick(View v){
        switch (v.getId()){
            case R.id.locations_back:
                onBackPressed();
                break;
            case R.id.locations_language:
                showPopupWindow();
                break;
            case R.id.pop_chinese:
                editor.putString("languange_set", "CN");
                changeAppLanguage(Locale.SIMPLIFIED_CHINESE);
                break;
            case R.id.pop_english:
                editor.putString("languange_set", "EN");
                changeAppLanguage(Locale.ENGLISH);
                break;

        }
    }

    public void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(this).inflate(R.layout.popwindow_language, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        TextView btnCN = contentView.findViewById(R.id.pop_chinese);
        TextView btnEN = contentView.findViewById(R.id.pop_english);
        btnCN.setOnClickListener(this);
        btnEN.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(this).inflate(R.layout.activity_community, null);
        mPopWindow.showAsDropDown(btnLanguage, -10, 10);
    }

    public void changeAppLanguage(Locale locale) {
        DisplayMetrics metrics = getResources().getDisplayMetrics();
        Configuration configuration = getResources().getConfiguration();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN_MR1) {
            configuration.setLocale(locale);
        } else {
            configuration.locale = locale;
        }
        getResources().updateConfiguration(configuration, metrics);
        //重新启动Activity
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
        startActivity(intent);
    }
}
