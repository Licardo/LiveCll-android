package cll.pf.com.livecll.constant;

public class Constants {
//    public static final String[] TAB_NAMES = {"杨申淼", "肿瘤资讯", "淋巴瘤之家", "亿迎新生"};
    /**
     * 文章的展示类型，分为大图和小图
     */
    public static final String SHOW_TYPE_SMALL = "small";
    public static final String SHOW_TYPE_BIG = "big";
    /**
     * 是否隐藏子Fragment，还没有实现
     */
    public static final Short HIDDEN_FRAGMENT = 0;

    /**
     *  首页的展示类型
     */
    public static final String STYLE_BANNER = "style_banner";
    public static final String STYLE_FUNCTION = "style_function";
    public static final String STYLE_LIST = "style_list";
    public static final String STYLE_ARTICLE = "style_article";

    public static final short STYLE_BANNER_TYPE = 1;
    public static final short STYLE_FUNCTION_TYPE = 2;
    public static final short STYLE_LIST_TYPE = 3;
    public static final short STYLE_ARTICLE_TYPE = 4;

    /**
     * 所有Activity的路径，路由会根据该路径获取所有注解。
     */
    public static final String ACTIVITY_FILE = "cll.pf.com.livecll.ui";
}
