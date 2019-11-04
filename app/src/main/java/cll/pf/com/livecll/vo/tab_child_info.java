package cll.pf.com.livecll.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import cn.bmob.v3.BmobObject;

@Deprecated
public class tab_child_info extends BmobObject implements Comparable<tab_child_info>, Parcelable {
    private String tab_name;
    private String source;
    private String platform;
    private Short sort;
    // 是否显示  1：显示，0：隐藏
    private short show;
    // 显示类型 small：小图模式 big：大图模式
    private String show_type;

    private tab_info relation;

    public tab_info getRelation() {
        return relation;
    }

    public void setRelation(tab_info relation) {
        this.relation = relation;
    }

    public String getTab_name() {
        return tab_name;
    }

    public void setTab_name(String tab_name) {
        this.tab_name = tab_name;
    }

    public String getSource() {
        return source;
    }

    public void setSource(String source) {
        this.source = source;
    }

    public String getPlatform() {
        return platform;
    }

    public void setPlatform(String platform) {
        this.platform = platform;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public short getShow() {
        return show;
    }

    public void setShow(short show) {
        this.show = show;
    }

    public String getShow_type() {
        return show_type;
    }

    public void setShow_type(String show_type) {
        this.show_type = show_type;
    }

    @Override
    public int compareTo(@NonNull tab_child_info tab_child_info) {
        return this.sort.compareTo(tab_child_info.getSort());
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(tab_name);
        parcel.writeString(source);
        parcel.writeString(platform);
        parcel.writeInt(sort);
        parcel.writeInt(show);
        parcel.writeString(show_type);
    }

    protected tab_child_info(Parcel in) {
        tab_name = in.readString();
        source = in.readString();
        platform = in.readString();
        int tmpSort = in.readInt();
        sort = tmpSort != Integer.MAX_VALUE ? (short) tmpSort : null;
        show = (short) in.readInt();
        show_type = in.readString();
    }

    public static final Creator<tab_child_info> CREATOR = new Creator<tab_child_info>() {
        @Override
        public tab_child_info createFromParcel(Parcel in) {
            return new tab_child_info(in);
        }

        @Override
        public tab_child_info[] newArray(int size) {
            return new tab_child_info[size];
        }
    };
}
