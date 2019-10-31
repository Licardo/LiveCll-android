package cll.pf.com.livecll.model;

public class TabsModel {
    private String tabName;
    private String tabParams;
    private String tabPlatform;
    private String showType;

    public TabsModel(String tabName, String tabParams) {
        this.tabName = tabName;
        this.tabParams = tabParams;
    }

    public TabsModel(String tabName, String tabParams, String tabPlatform) {
        this.tabName = tabName;
        this.tabParams = tabParams;
        this.tabPlatform = tabPlatform;
    }

    public TabsModel(String tabName, String tabParams, String tabPlatform, String showType) {
        this.tabName = tabName;
        this.tabParams = tabParams;
        this.tabPlatform = tabPlatform;
        this.showType = showType;
    }

    public String getShowType() {
        return showType;
    }

    public void setShowType(String showType) {
        this.showType = showType;
    }

    public String getTabName() {
        return tabName;
    }

    public void setTabName(String tabName) {
        this.tabName = tabName;
    }

    public String getTabParams() {
        return tabParams;
    }

    public void setTabParams(String tabParams) {
        this.tabParams = tabParams;
    }

    public String getTabPlatform() {
        return tabPlatform;
    }

    public void setTabPlatform(String tabPlatform) {
        this.tabPlatform = tabPlatform;
    }
}
