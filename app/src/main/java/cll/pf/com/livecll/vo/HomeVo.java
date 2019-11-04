package cll.pf.com.livecll.vo;

import android.support.annotation.NonNull;

import java.io.Serializable;
import java.util.List;

public class HomeVo implements Serializable, Comparable<HomeVo> {
    private String style;
    private String tile;
    private String image_url;
    private String click_url;
    private Integer sort;
    private Integer id;
    private List<HomeContentVo> home_contents;

    @Override
    public int compareTo(@NonNull HomeVo homeVo) {
        return this.sort.compareTo(homeVo.getSort());
    }

    public static class HomeContentVo implements Serializable, Comparable<HomeContentVo> {
        private String content;
        private String image_url;
        private String click_url;
        private Integer sort;
        private String description;
        private Integer id;
        private Integer title_id;

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
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

        public Integer getSort() {
            return sort;
        }

        public void setSort(Integer sort) {
            this.sort = sort;
        }

        public void setId(Integer id) {
            this.id = id;
        }

        public void setTitle_id(Integer title_id) {
            this.title_id = title_id;
        }

        public String getDescription() {
            return description;
        }

        public void setDescription(String description) {
            this.description = description;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        public int getTitle_id() {
            return title_id;
        }

        public void setTitle_id(int title_id) {
            this.title_id = title_id;
        }

        @Override
        public int compareTo(@NonNull HomeContentVo homeContentVo) {
            return this.sort.compareTo(homeContentVo.getSort());
        }
    }

    public String getStyle() {
        return style;
    }

    public void setStyle(String style) {
        this.style = style;
    }

    public String getTile() {
        return tile;
    }

    public void setTile(String tile) {
        this.tile = tile;
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

    public Integer getSort() {
        return sort;
    }

    public void setSort(Integer sort) {
        this.sort = sort;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<HomeContentVo> getHome_contents() {
        return home_contents;
    }

    public void setHome_contents(List<HomeContentVo> home_contents) {
        this.home_contents = home_contents;
    }
}
