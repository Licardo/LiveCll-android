package cll.pf.com.livecll.router;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import java.util.HashMap;
import java.util.Map;

public class RouterPath {

    private static RouterPath sRouterPath;
    private Map<String, Class> mPaths;

    private RouterPath() {
        mPaths = new HashMap<>();
    }

    public static RouterPath getInstance() {
        if (sRouterPath == null) {
            synchronized (RouterPath.class) {
                if (sRouterPath == null) {
                    sRouterPath = new RouterPath();
                }
            }
        }
        return sRouterPath;
    }

    /**
     * 跳转方法
     * @param key
     */
    public void navigation(Context context, String key) {
        navigation(context, key, null);
    }

    /**
     * 跳转方法
     * @param key
     */
    public void navigation(Context context, String key, Bundle bundle) {
        Class path = getPath(key);
        if (path == null) {
            return;
        }
        Intent intent = new Intent();
        if (bundle != null) {
            intent.putExtras(bundle);
        }
        intent.setClass(context, path);
        context.startActivity(intent);
    }

    @Deprecated
    public void initPath(ConstantPath constantPath) {
        int len = constantPath.keys.length;
        for (int i = 0; i < len; i++) {
            register(constantPath.keys[i], constantPath.paths[i]);
        }
    }

    void register(String key, Class path) {
        if (!mPaths.containsKey(key)) {
            mPaths.put(key, path);
        }
    }

    private Class getPath(String key){
        if (mPaths.containsKey(key)) {
            return mPaths.get(key);
        }
        return null;
    }
}
