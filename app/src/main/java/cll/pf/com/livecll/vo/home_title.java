package cll.pf.com.livecll.vo;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

@Deprecated
public class home_title extends BmobObject implements Comparable<home_title>{
    private String title;
    private String image_url;
    private String click_url;
    private String style;
    private Short sort;
    private ArrayList<home_content> homeContents;

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public ArrayList<home_content> getHomeContents() {
        if (homeContents == null) {
            homeContents = new ArrayList<>();
        }
        return homeContents;
    }

    public void setHomeContents(ArrayList<home_content> homeContents) {
        this.homeContents = homeContents;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(@NonNull home_title home_title) {
        return this.sort.compareTo(home_title.getSort());
    }
}
