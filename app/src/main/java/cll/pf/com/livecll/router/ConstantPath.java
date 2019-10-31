package cll.pf.com.livecll.router;

import cll.pf.com.livecll.ui.SplashActivity;

/**
 * 新增路径必须满足
 * 1. keys和paths一一对应
 * 2. keys不能重复
 * 3. paths可以重复
 */
public class ConstantPath {

    public static final String INTRODUCE_INDEX = "livecll.com/introduce";
    public static final String LOGIN_INDEX = "livecll.com/login";
    public static final String MAIN_INDEX = "livecll.com/main";
    public static final String SPLASH_INDEX = "livecll.com/splash";
    public static final String TAB_INDEX = "livecll.com/tab";
    public static final String TINKER_INDEX = "livecll.com/tinker";
    public static final String WEBVIEW_INDEX = "livecll.com/webview";
    public static final String WELCOME_INDEX = "livecll.com/welcome";

    @Deprecated
    public String[] keys = {
//            "livecll.com/introduce",
//            "livecll.com/login",
//            "livecll.com/main",
            "livecll.com/splash",
//            "livecll.com/tab",
//            "livecll.com/tinker",
//            "livecll.com/webview",
//            "livecll.com/welcome"
    };

    @Deprecated
    public Class[] paths = {
//            IntroduceActivity.class,
//            LoginActivity.class,
//            MainActivity.class,
            SplashActivity.class,
//            TabActivity.class,
//            TinkerActivity.class,
//            WebViewActivity.class,
//            WelcomeActivity.class
    };
}
