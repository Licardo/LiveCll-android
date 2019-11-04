package cll.pf.com.livecll.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import cn.bmob.v3.BmobObject;
@Deprecated
public class home_content extends BmobObject implements Parcelable,Comparable<home_content> {
    private Short sort;
    private String content;
    private String description;
    private home_title title;
    private String image_url;
    private String click_url;

    public String getClick_url() {
        return click_url;
    }

    public void setClick_url(String click_url) {
        this.click_url = click_url;
    }

    public String getImage_url() {
        return image_url;
    }

    public void setImage_url(String image_url) {
        this.image_url = image_url;
    }

    public Short getSort() {
        return sort;
    }

    public void setSort(Short sort) {
        this.sort = sort;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public home_title getTitle() {
        return title;
    }

    public void setTitle(home_title title) {
        this.title = title;
    }

    protected home_content(Parcel in) {
        int tmpSort = in.readInt();
        sort = tmpSort != Integer.MAX_VALUE ? (short) tmpSort : null;
        content = in.readString();
        description = in.readString();
        image_url = in.readString();
        click_url = in.readString();
    }

    public static final Creator<home_content> CREATOR = new Creator<home_content>() {
        @Override
        public home_content createFromParcel(Parcel in) {
            return new home_content(in);
        }

        @Override
        public home_content[] newArray(int size) {
            return new home_content[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(content);
        parcel.writeString(description);
        parcel.writeInt(sort);
        parcel.writeString(image_url);
        parcel.writeString(click_url);
    }

    @Override
    public int compareTo(@NonNull home_content home_content) {
        return this.sort.compareTo(home_content.getSort());
    }
}
