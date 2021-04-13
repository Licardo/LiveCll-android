package cll.pf.com.livecll.net;

import android.net.Uri;
import android.text.TextUtils;

import java.util.List;
import java.util.Map;
import java.util.Set;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;

public class HttpUtils {
    private static OkHttpClient mClient;
    private static final String HOST = "http://www.livecll.com";
//    private static final String HOST = "http://172.16.20.105:8000/";

    public static void init(List<Interceptor> interceptors) {
        if (mClient == null) {
            synchronized (HttpUtils.class) {
                if (mClient == null) {
                    OkHttpClient.Builder builder = new OkHttpClient.Builder();
                    if (interceptors != null) {
                        builder.interceptors().addAll(interceptors);
                    }
                    mClient = builder.build();
                }
            }
        }
    }

    public static OkHttpClient getClient() {
        return mClient;
    }

    public static String addParams(String url, Map<String, String> params) {
        if (TextUtils.isEmpty(url)) {
            return "";
        }
        Uri uri = Uri.parse(HOST+url);
        Uri.Builder builder = uri.buildUpon();
        if (params == null) {
            return builder.build().toString();
        }
        Set<String> keys = params.keySet();
        for (String key:keys) {
            if (TextUtils.isEmpty(params.get(key))) {
                continue;
            }
            builder.appendQueryParameter(key, params.get(key));
        }
        return builder.build().toString();
    }
}
