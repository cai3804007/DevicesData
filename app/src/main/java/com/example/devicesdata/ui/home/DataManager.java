package com.example.devicesdata.ui.home;

import android.app.Activity;
import android.webkit.WebView;

import androidx.fragment.app.Fragment;
import android.content.SharedPreferences;
import android.content.Context;
public class DataManager {
    public Fragment home;
    public Activity main;
    public WebView webview;
    private DataManager() {
        // 初始化单例对象
    }

    // 静态内部类持有单例实例
    private static class SingletonHolder {
        private static final DataManager INSTANCE = new DataManager();
    }

    // 获取单例实例的静态方法
    public static DataManager getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public String getURL(){

        // 获取 Shared Preferences 对象
        SharedPreferences sharedPreferences = main.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        // 读取字符串数据，默认值为 ""（如果找不到对应的键值）
        String retrievedString = sharedPreferences.getString("url", "");
        if (retrievedString == ""){
            retrievedString = "https://www.baidu.com/";
        }
        return retrievedString;
    }

    public void setURLSting(String url){
        if (url.isEmpty()){
            return;
        }
        // 获取 Shared Preferences 对象
        SharedPreferences sharedPreferences = main.getSharedPreferences("myPrefs", Context.MODE_PRIVATE);
        // 获取 Shared Preferences 的编辑器
        SharedPreferences.Editor editor = sharedPreferences.edit();
        // 存储字符串数据
        editor.putString("url", url);
        // 应用提交，使更改生效
        editor.apply();
    }

}
