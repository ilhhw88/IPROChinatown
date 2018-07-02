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
import android.widget.Spinner;
import android.widget.TextView;

import com.hhw.ipro.iprochinatown.classes.LocationAdapter;
import com.hhw.ipro.iprochinatown.classes.LocationDisplayAdapter;
import com.hhw.ipro.iprochinatown.classes.LocationItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class FAQActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    ImageView btnBack;
    ImageView btnLanguage;
    RecyclerView recyclerView;
    LocationDisplayAdapter faqAdapter;

    List<String> strings;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_faq);

        sp = getSharedPreferences("language", Context.MODE_PRIVATE);
        editor = sp.edit();

        initData();
        initView();
    }

    void initView(){
        btnBack = findViewById(R.id.locations_back);
        btnBack.setOnClickListener(this);
        btnLanguage = findViewById(R.id.locations_language);
        btnLanguage.setOnClickListener(this);

        recyclerView = findViewById(R.id.rv_faq);
        faqAdapter = new LocationDisplayAdapter(this, strings);

        recyclerView.setAdapter(faqAdapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.addItemDecoration(new LocationItemDecoration());
    }

    void initData(){
        strings = new ArrayList<>();

        strings.add("Who we are:");
        strings.add("We are a group of students from the Illinois Institute of Technology who are working on a project to better understand the Chinatown community and learn more about the stories this community has to share");
        strings.add("What we are doing:");
        strings.add("We are conducting a series of interviews to learn what stories different organizations/groups are interested in sharing about their community on a public forum to understand what chinatown wants to share with outside groups. Knowing that information, we then want to formulate a plan to gather stories and immortalize them in different forms of media, showcasing them through the use of digital technology so that anyone can learn about what makes Chinatown unique.");
        strings.add("Why we are doing this:");
        strings.add("We have learned that there are many “gaps” that exist in the Chinatown community, such as inter-generational, age and language gaps. These have caused a multitude of minor problems that ultimately cause community tension while leaving the community vulnerable to gentrification. We want to work with the community to help preserve its culture, tradition, and existence. ");
        strings.add("When we are doing this:");
        strings.add("The project duration for this particular goal runs from 5/21/2018 to 6/30/2018, however this duration is subject only to the personal involvement of all team members. Should there be enough interest, this project will be continued in the fall, running from 8/27/2018 to early december.");
        strings.add("Where we are doing this:");
        strings.add("We want to work directly with the Chinatown community, focusing our personal team meetings at the Illinois Institute of Technology, but the actual work that will be implemented will be within the boundaries of the Chinatown community.");
        strings.add("How we are doing this:");
        strings.add("We are partnering with different community groups and organizations within Chinatown to better understand how to help us reach our goal of relieving the stress of some of the issues that the community is facing while also providing a service that enhances the community as a whole itself.");
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
        View contentView = LayoutInflater.from(FAQActivity.this).inflate(R.layout.popwindow_language, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        TextView btnCN = contentView.findViewById(R.id.pop_chinese);
        TextView btnEN = contentView.findViewById(R.id.pop_english);
        btnCN.setOnClickListener(this);
        btnEN.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(FAQActivity.this).inflate(R.layout.activity_faq, null);
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
