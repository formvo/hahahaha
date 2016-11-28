package com.example.com.project;

import android.graphics.drawable.Drawable;

/**
 * Created by com on 2016-11-16.
 */

public class CListViewItem {
    private Drawable iconDrawable ;
    private String titleStr ;
    private String descStr ;
    private String url;
    private String name;

    public void setUrl(String url) {
        this.url = url;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setIcon(Drawable icon) {
        iconDrawable = icon ;
    }
    public void setTitle(String title) {
        titleStr = title ;
    }
    public void setDesc(String desc) {
        descStr = desc ;
    }

    public Drawable getIcon() {
        return this.iconDrawable ;
    }
    public String getTitle() {
        return this.titleStr ;
    }
    public String getDesc() {
        return this.descStr ;
    }
    public String getUrl() {
        return url;
    }
    public String getName() {
        return name;
    }
}
