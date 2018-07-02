package com.hhw.ipro.iprochinatown;

import com.hhw.ipro.iprochinatown.classes.CommunityAdapter;
import com.hhw.ipro.iprochinatown.classes.CommunityBasicActivity;

import java.util.ArrayList;

public class CommunityActivity extends CommunityBasicActivity {
    @Override
    public void initView(){
        super.initView();
        titleCommunity.setText(titleActivity);
    }

    @Override
    public void initData(){
        strings = new ArrayList<>();
        titleActivity = getIntent().getStringExtra("title_activity");
        String[] communityArr = getResources().getStringArray(R.array.community);
        String[] locationsArr = getResources().getStringArray(R.array.locations_array);

        if(titleActivity.equals(getString(R.string.community))) {
            strings = new ArrayList<>();
            for (String str : communityArr) {
                strings.add(str);
            }
            rvAdapter = new CommunityAdapter(this, strings, CommunityAdapter.nextIsList);
        }
        else{
            int indexCommunity = getIntent().getIntExtra("index_community", -1);
            switch(indexCommunity){
                case 0://community service
                    strings.add(locationsArr[puiTakCententer]);
                    rvAdapter = new CommunityAdapter(this, strings, CommunityAdapter.nextIsLocationDisplay);
                    break;
                case 1://hospital and tourism
                    strings.add(locationsArr[chineseAmericanMuseum]);
                    rvAdapter = new CommunityAdapter(this, strings, CommunityAdapter.nextIsLocationDisplay);
                    break;
                case 2:
                    strings.add("Lee's Family Association");
                    strings.add("Moy's Family Association");
                    rvAdapter = new CommunityAdapter(this, strings, CommunityAdapter.nextIsDetail);
                    break;
                case 3:
                    strings.add(communityArr[indexCommunity]);
                    rvAdapter = new CommunityAdapter(this, strings, CommunityAdapter.nextIsDetail);
                    break;
            }
        }
    }
}
