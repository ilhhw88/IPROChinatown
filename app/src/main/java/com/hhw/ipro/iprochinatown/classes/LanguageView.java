package com.hhw.ipro.iprochinatown.classes;

import android.support.annotation.ArrayRes;
import android.support.annotation.StringRes;

public interface LanguageView {
    void setTextById (@StringRes int id);//手动设置textId
    void setTextWithString (String text);//手动去掉textId,不然重新加载语言的时候会被重置掉
    void setTextByArrayAndIndex (@ArrayRes int arrId, @StringRes int arrIndex);//手动通过TextArray设置语言

    void reLoadLanguage();//修改语言时主要调用的方法
}
