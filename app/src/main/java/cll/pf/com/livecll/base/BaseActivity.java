package cll.pf.com.livecll.base;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.support.v4.content.ContextCompat;

import com.githang.statusbar.StatusBarCompat;
import com.umeng.analytics.MobclickAgent;
import com.umeng.message.PushAgent;

import cll.pf.com.livecll.R;

public abstract class BaseActivity extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initView();
        PushAgent.getInstance(this).onAppStart();
        StatusBarCompat.setStatusBarColor(this, ContextCompat.getColor(this, R.color.colorWhite), true);
        initData();
    }

    /**
     * 初始化控件
     */
    public abstract void initView();

    /**
     * 初始化数据相关
     */
    public abstract void initData();

    @Override
    protected void onResume() {
        super.onResume();
        MobclickAgent.onResume(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        MobclickAgent.onPause(this);
    }

    /**
     * 写入缓存
     * @param key
     * @param value
     */
    public void writePreferences(String key, String value) {
        SharedPreferences sp = getSharedPreferences("LiveCll", MODE_PRIVATE);
        sp.edit().putString(key, value)
                .apply();
    }

    public String readPreferences(String key) {
        SharedPreferences sp = getSharedPreferences("LiveCll", MODE_PRIVATE);
        return sp.getString(key, "");
    }
}
