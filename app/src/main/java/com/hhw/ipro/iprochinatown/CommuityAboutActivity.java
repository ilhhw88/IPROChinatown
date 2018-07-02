package com.hhw.ipro.iprochinatown;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
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
import com.hhw.ipro.iprochinatown.classes.LocationItemDecoration;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class CommuityAboutActivity extends AppCompatActivity implements View.OnClickListener{

    SharedPreferences sp;
    SharedPreferences.Editor editor;

    ImageView btnBack;
    ImageView btnLanguage;
    RecyclerView recyclerView;
    LocationDisplayAdapter faqAdapter;
    TextView titleFAQ;

    List<String> strings;
    String title;

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

        titleFAQ = findViewById(R.id.title_faq);
        titleFAQ.setText(title);
    }

    void initData(){
        title = getIntent().getStringExtra("title_activity");

        strings = new ArrayList<>();
        switch (title){
            case "Lee's Family Association":
                initDataLeeFamily();
                break;
            case "Moy's Family Association":
                initDataLMoyFamily();
                break;

            default:
            case "Chinatown Culture and Traditions":
                initDataCulture();
                break;
        }
    }

    void initDataCulture(){

        strings.add("Small Groceries: Fresh Food");
        strings.add("Obtaining fresh groceries are of special importance to the Chinese community, especially to those who live in Chinatown. This practice is able to be seen with the busy and bustling streets of Chinatown every morning and early afternoon, filled with the hurried shuffles of neighborhood locals. The Chinese community values the freshness of groceries in their cuisine, and thus is demonstrated with the daily purchases of various vegetables, meat, and/or poultry. ");
        strings.add("Soy Sauce Buckets");
        strings.add("Soy sauce buckets may seem like strange objects to be discussed but can actually be utilized for many unexpected reasons in the Chinatown community. Throughout the winding streets of the neighborhood, buckets filled with gardening soil and different types of seafood are located among majority of households. The buckets are great for growing tomatoes, ginger, and other common ingredients. In traditional Asian culture, being resourceful is common through reusing products and not being wasteful of any consumables. Unconventionally, with the unwritten rules of parking dibs in Chicago, it is common to see soy sauce buckets on the street as a placeholder.");
        strings.add("Fish Delivery");
        strings.add("Every morning in Chinatown, the familiar scent of consistent semi-trucks filled with livestock and fish make their route to restock restaurants and small grocery stores. Any tourist walking through Chinatown will see aquariums of fish in shops. There is always an abundance of different varieties of fish. ");
        strings.add("BaGua Mirror");
        strings.add("Feng shui bagua mirror is an octagonal shaped mirror, you can see it around walking in Chinatown or in souvenir shops. The mirror has a purpose beyond being a souvenir piece from Chinatown. It’s an ancient feng shui method to balance the energy in an area and it’s used to diffuse strong energy directed to certain places. The mirror is usually hung outside homes or businesses, in rare cases it’s hung inside. The most common places where you catch the sight of Bagua mirrors are over the front doors of a business or a house. Some houses or businesses located at the end of a T-intersections use Bagua mirrors, also they are used if a sharp object or a corner point at the front door and if residents are experiencing unpleasant energy around a particular area. \nThere is another explanation to the powers of Bagua mirrors. Some people say that Bagua mirrors drive away evil and unwanted spirits as it’s believed that evil spirits are repulsed to the sight of their own reflection. Moreover, the preferred size of the mirror is 8 inches and it’s the mostly common used size because eight is a lucky number.");
        strings.add("Joss Paper");
        strings.add("Joss paper are sheets of paper burned in traditional chinese deity or ancestor worship ceremonies during special holidays and chinese funerals. Joss paper is typically made of coarse bamboo paper and commercial joss paper are made of rice paper. The paper is cut into squares or rectangulares. Each is either with a thin foil piece in the middle or with red ink seal. The color is white representing mourning. Joss paper is used for venerating departed ancestors because it enables the deceased family members to have all what they need in the afterlife. Ancestor worship is based on  the belief that dead spirits continue to dwell into the natural world affecting the fate and fortune of the living and the goal of the ancestor worship is ensuring the well being of the ancestors to guarantee the well being of the living. While burning the joss paper, it should be placed respectfully in an earthenware pot. ");
        strings.add("Household shrines");
        strings.add("In Buddhism, shrines are very common in houses and shops in Chinatown. Shrines are usually small structures which has small statue or picture and dedicated to a deity that is part of Buddhism. Around the shrine structure, there are always a lamp and small offerings such as fruit .");

    }

    void initDataLeeFamily(){
        strings.add("Lee's Family Association");
        strings.add("Lee association is a family association with over 100 years’ history. It’s one of the largest family association in Chinatown. Members of Lee association are mainly for Taishan and Canton. There’s more than 1000 Lees live in or live around Chicago. At least 200 of them are members of Lee association. In 1964, Lee association moved to current location, 2225 S wentworth second floor. This place was bought with donation from Lee’s members.\n  Before, Lee association was founded to provide Lees a family far away from home. Members help each other to get a better life in America. Nowadays, Lee association hosts parties for some Chinese traditional holidays, host social parties for elder and help members host weddings and funeral. Its current creed is ‘Zun Lao Ai You(尊老爱幼)’ which means respecting and take care the elder, cherish the young. Lee association don’t only host events by their own, they also host celebration with other association as well. Every Spring, half number of Chicago Chinese associations will celebrate Chinese new year together, and Lee is one of them.\n   Lee association support their organization by membership fees and donations. They do election every year to elect 4 to 5 manager including president and vice president. The challenge Lee association facing is actually the age of managers. Because the difficulty of finding young people with skills to run the organization. People who is taking charge of the association are usually too old to host some large events. In past 50 years, Chicago Lee association was not able to host Lee Family national convention of U.S.A(A meeting that host for all Lees in America). Lee association realized this problem. For the past 5 years, Lee association is focusing on find and raise younger manager with skill to develop the organization. They are trying to host next national convention.");
    }

    void initDataLMoyFamily(){
        strings.add("Moy's Family Association");
        strings.add("       During the late 19th century, children of the Moy families started to emigrate overseas. Their footprints reached Southeast Asia and the Americas. A significant number of the Moy families in the United States gathered in the City of Chicago. Soon thereafter, they organized the first Moy Family Association. As time passed by, more Moy families joined friends and relatives in Chicago. As a result, Moy was the most commonly seen surname among the Chinese population in Chicago.\nToday, the Moy Family General Association has been in Chicago for over a century. The arrival of the new millennium marked the beginning of the third century for the Moy families living in Chicago, the family name with the longest history in the city. The original location of the Moy Family Association was at the near-south-side of Chicago at Clark Street. It later relocated to the present Moy Family General Association Building, located at 2238 South Wentworth Avenue, which was built in 1927 thanks to the donations from the Moy brothers and sisters and other loans.\nThe mission of the Moy Family General Association is to unify the Moy descendants in the United States and to promote mutual assistance for all the members and the local communities. In addition to the Moy Family General Association in Chicago, other Moy Family Associations have started in other cities such as New York, Boston, San Francisco and all throughout the United States. However, there is only one \"General\" association for the Moy families, here in Chicago.\nEvery three years, a Convention of all the Moy Family Associations in the U. S. is held in a selected city in the United States. During this Convention, the president and other officials are elected to serve the following three-year term. The Moy Family General Association in Chicago holds its own Spring Banquet every year to promote friendship and harmony, and to enhance connections among the Moy families. An election is held annually in early December to choose the officials that will serve the association for a one-year term.\nEmphasizing scholarly pursuits, the Moy Family General Association in Chicago also encourages younger generations to excel in school. For this purpose, scholarships are issued at the annual Spring Banquet to those students who perform exceptionally well in school. At the same event, senior members of the Moy families are specially recognized, honored, and encourage younger generations to cherish the roots and traditions of the Moy families so that the virtues and traditions of their ancestors can live on.\nTime and environments are ever changing, however, the mission of the Moy Family General Association remains unchanged: to build a harmonious community and to live peacefully and cooperatively with each other. We treat all members of the Moy families as brothers and sisters, wherever they live. We understand that we must continuously enhance our relationships and support each other to be successful. Blood is thicker than water, and we are all one family originating from-the same root.");
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
        View contentView = LayoutInflater.from(CommuityAboutActivity.this).inflate(R.layout.popwindow_language, null);
        PopupWindow mPopWindow = new PopupWindow(contentView,ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        //设置各个控件的点击响应
        TextView btnCN = contentView.findViewById(R.id.pop_chinese);
        TextView btnEN = contentView.findViewById(R.id.pop_english);
        btnCN.setOnClickListener(this);
        btnEN.setOnClickListener(this);
        //显示PopupWindow
        View rootview = LayoutInflater.from(CommuityAboutActivity.this).inflate(R.layout.activity_faq, null);
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
