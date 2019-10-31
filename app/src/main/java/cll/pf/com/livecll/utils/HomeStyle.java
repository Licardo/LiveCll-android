package cll.pf.com.livecll.utils;

import java.util.HashMap;

import cll.pf.com.livecll.constant.Constants;

public class HomeStyle {
    public static HashMap<String, Short> mStyle = new HashMap<>();
    static {
        mStyle.put(Constants.STYLE_BANNER, Constants.STYLE_BANNER_TYPE);
        mStyle.put(Constants.STYLE_FUNCTION, Constants.STYLE_FUNCTION_TYPE);
        mStyle.put(Constants.STYLE_LIST, Constants.STYLE_LIST_TYPE);
        mStyle.put(Constants.STYLE_ARTICLE, Constants.STYLE_ARTICLE_TYPE);
    }
}
