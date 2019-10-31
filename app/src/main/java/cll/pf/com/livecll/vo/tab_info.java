package cll.pf.com.livecll.vo;

import android.support.annotation.NonNull;

import java.util.ArrayList;

import cn.bmob.v3.BmobObject;

public class tab_info extends BmobObject implements Comparable<tab_info>{
    private String name;
    private String icons_selected;
    private String icons_unselected;
    private Integer sort;
    private ArrayList<tab_child_info> tabChildInfos;

    public ArrayList<tab_child_info> getTabChildInfos() {
        if (tabChildInfos == null) {
            tabChildInfos = new ArrayList<>();
        }
        return tabChildInfos;
    }

    public void setTabChildInfos(ArrayList<tab_child_info> tabChildInfos) {
        this.tabChildInfos = tabChildInfos;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIcons_selected() {
        return icons_selected;
    }

    public void setIcons_selected(String icons_selected) {
        this.icons_selected = icons_selected;
    }

    public String getIcons_unselected() {
        return icons_unselected;
    }

    public void setIcons_unselected(String icons_unselected) {
        this.icons_unselected = icons_unselected;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    @Override
    public int compareTo(@NonNull tab_info tab_info) {
        return this.sort.compareTo(tab_info.getSort());
    }
}
