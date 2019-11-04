package cll.pf.com.livecll.vo;

import android.os.Parcel;
import android.os.Parcelable;
import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.ArrayList;

public class TabVo implements Serializable, Comparable<TabVo> {
    private Integer id;
    private Integer sort;
    private String name;
    private String icons_selected;
    private String icons_unselected;
    private ArrayList<TabChildVo> tab_child_infos;

    @Override
    public int compareTo(@NonNull TabVo tabVo) {
        return this.sort.compareTo(tabVo.getSort());
    }

    public static class TabChildVo implements Serializable, Comparable<TabChildVo>, Parcelable {
        private Integer tab_id;
        private String tab_name;
        private String source;
        private String platform;
        private Integer sort;
        private String show_type;
        private Integer show;


        @Override
        public int describeContents() {
            return 0;
        }

        @Override
        public void writeToParcel(Parcel parcel, int i) {
            parcel.writeString(tab_name);
            parcel.writeString(source);
            parcel.writeString(platform);
            parcel.writeString(show_type);
            parcel.writeInt(tab_id);
            parcel.writeInt(sort);
            parcel.writeInt(show);
        }

        protected TabChildVo(Parcel in) {
            if (in.readByte() == 0) {
                tab_id = null;
            } else {
                tab_id = in.readInt();
            }
            tab_name = in.readString();
            source = in.readString();
            platform = in.readString();
            if (in.readByte() == 0) {
                sort = null;
            } else {
                sort = in.readInt();
            }
            show_type = in.readString();
            if (in.readByte() == 0) {
                show = null;
            } else {
                show = in.readInt();
            }
        }

        public static final Creator<TabChildVo> CREATOR = new Creator<TabChildVo>() {
            @Override
            public TabChildVo createFromParcel(Parcel in) {
                return new TabChildVo(in);
            }

            @Override
            public TabChildVo[] newArray(int size) {
                return new TabChildVo[size];
            }
        };

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

        public void setTab_id(Integer tab_id) {
            this.tab_id = tab_id;
        }

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public void setShow(Integer show) {
            this.show = show;
        }

        public String getShow_type() {
            return show_type;
        }

        public void setShow_type(String show_type) {
            this.show_type = show_type;
        }

        @Override
        public int compareTo(@NonNull TabChildVo tabChildVo) {
            return this.sort.compareTo(tabChildVo.getSort());
        }

        public Integer getTab_id() {
            return tab_id;
        }

        public Integer getShow() {
            return show;
        }
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
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

    public ArrayList<TabChildVo> getTab_child_infos() {
        return tab_child_infos;
    }

    public void setTab_child_infos(ArrayList<TabChildVo> tab_child_infos) {
        this.tab_child_infos = tab_child_infos;
    }
}
