package com.hhw.ipro.iprochinatown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hhw.ipro.iprochinatown.classes.LocationDisplayAdapter;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class LocationDisplayActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    private final int chineseAmericanMuseum = 0;
    private final int pingTomPark = 1;
    private final int chinatownLibrary = 2;
    private final int stThereseChurch = 3;
    private final int puiTakCententer = 4;
    private final int chinatownGate = 5;
    private final int nineDragonWall = 6;
    private final int chineseChristianCatholicChurch = 7;
    private final int chinatownSquare = 8;
    private final int kentCenter = 9;
    private final int kamLLiuBuilding = 10;

    ImageView btnBack;
    TextView strName;
    ImageView btnLanguage;

    ImageView imgLocation;
    //TextView descriptionLocation;
    RecyclerView rvLocationInfo;
    LocationDisplayAdapter locationInfoAdapter;

    int index;
    String name;
    int idImg;
    int idInfoArr;
    List<String> dataInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location_display);

        sp = getSharedPreferences("language", Context.MODE_PRIVATE);
        editor = sp.edit();

        initData();
        initView();
    }

    void initView(){
        btnBack = findViewById(R.id.locations_back);
        btnBack.setOnClickListener(this);
        strName = findViewById(R.id.location_name);
        strName.setText(name);
        btnLanguage = findViewById(R.id.locations_language);
        btnLanguage.setOnClickListener(this);

        imgLocation = findViewById(R.id.location_img);
        imgLocation.setImageResource(idImg);

        rvLocationInfo = findViewById(R.id.location_infomation);
        locationInfoAdapter = new LocationDisplayAdapter(this, dataInfo);

        rvLocationInfo.setAdapter(locationInfoAdapter);
        rvLocationInfo.setLayoutManager(new LinearLayoutManager(this));
    }

    void initData(){
        index = getIntent().getIntExtra("nameIndex",0);
        String[] locationsArr = getResources().getStringArray(R.array.locations_array);
        int[] imgIdArr = {R.drawable.chinese_american_museum,
                        R.drawable.ping_tom,
                        R.drawable.chinatownlibrary,
                        R.drawable.chinatown_church_saint_therese,
                        R.drawable.pui_tak_center,
                        R.drawable.chinatowngate,
                        R.drawable.nine_dragon_wall,
                        R.drawable.chinese_christian_cathilic_church,
                        R.drawable.chinatown_square,
                        R.drawable.kent_center,
                        R.drawable.kam_l_liu_building};

        int[] idInfoidArr = {R.array.chinese_american_museum,
                            R.array.ping_tom_park,
                            R.array.chinatown_library,
                            R.array.st_therese_church,
                            R.array.pui_tak_center,
                            R.array.chinatowngate,
                            R.array.nine_dragon_wall,
                            R.array.chinese_christian_union_church,
                            R.array.chinatown_square,
                            R.array.kent_center,
                            R.array.kam_l_liu_building};


        name = locationsArr[index];
        idImg = imgIdArr[index];
        idInfoArr = idInfoidArr[index];

        initDataInfo();
    }

    void initDataInfo(){
        dataInfo = new ArrayList<>();

        String[] infoArr = getResources().getStringArray(idInfoArr);

        dataInfo.add(getString(R.string.hours));
        dataInfo.add(infoArr[0]);
        dataInfo.add(getString(R.string.address));
        dataInfo.add(infoArr[1]);
        dataInfo.add(getString(R.string.contact));
        dataInfo.add(infoArr[2]);
        dataInfo.add(getString(R.string.about));
        dataInfo.add(infoArr[3]);
    }

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

    private void showPopupWindow() {
        //设置contentView
        View contentView = LayoutInflater.from(LocationDisplayActivity.this).inflate(R.layout.popwindow_language, null);
        PopupWindow mPopWindow = new PopupWindow(contentView, ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        TextView btnCN = contentView.findViewById(R.id.pop_chinese);
        TextView btnEN = contentView.findViewById(R.id.pop_english);
        btnCN.setOnClickListener(this);
        btnEN.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(LocationDisplayActivity.this).inflate(R.layout.activity_location_display, null);
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
