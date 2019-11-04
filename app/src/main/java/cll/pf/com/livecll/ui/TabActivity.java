package cll.pf.com.livecll.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;
import com.umeng.analytics.MobclickAgent;

import java.io.IOException;
import java.util.Collections;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.listener.OnTransitionViewListener;
import cll.pf.com.livecll.net.HttpUtils;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.vo.TabVo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

@CllRouter(value = ConstantPath.TAB_INDEX)
public class TabActivity extends BaseActivity {
    private IndicatorViewPager indicatorViewPager;
    private FixedIndicatorView indicator;
    private SViewPager sViewPager;
    private List<TabVo> mTabInfos;

    @Override
    public void initView() {
        setContentView(R.layout.activity_tab);
        sViewPager = findViewById(R.id.vp_fragment);
        indicator = findViewById(R.id.iv_indicator);
        sViewPager.setCanScroll(true);
        sViewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initData() {
        getAllTabInfos();
    }

    private void initViewPage() {
        int len = mTabInfos.size();
        String[] tabIcons= new String[len];
        String[] tabUnIcons = new String[len];
        for (int i = 0; i < len; i++) {
            tabIcons[i] = mTabInfos.get(i).getIcons_selected();
            tabUnIcons[i] = mTabInfos.get(i).getIcons_unselected();
        }

        indicator.setOnTransitionListener(new OnTransitionViewListener(13, 13,
                Color.parseColor("#FFDC35"), Color.BLACK, tabIcons, tabUnIcons));
        indicatorViewPager = new IndicatorViewPager(indicator, sViewPager);
        indicatorViewPager.setAdapter(new ViewPagerAdapter(getSupportFragmentManager()));
    }

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

    private void getAllTabInfos() {

        Request request = new Request.Builder()
                .get()
                .url("http://49.232.163.72:8000/cll/tab/info")
                .build();
        HttpUtils.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) { }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    return;
                }
                Gson gson = new Gson();
                String data = response.body()==null?"":response.body().string();
                List<TabVo> tabVos = gson.fromJson(data, new TypeToken<List<TabVo>>(){}.getType());
                mTabInfos = tabVos;
                Collections.sort(mTabInfos);
                sViewPager.post(new Runnable() {
                    @Override
                    public void run() {
                        initViewPage();
                    }
                });
            }
        });
    }

    class ViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{

        private LayoutInflater inflater;

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(getApplicationContext());
        }

        @Override
        public int getCount() {
            return mTabInfos.size();
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_indicator_view, container, false);
            }
            TextView textView = convertView.findViewById(R.id.tv_title);
            ImageView imageView = convertView.findViewById(R.id.iv_image);
            textView.setText(mTabInfos.get(position).getName());
            Glide.with(imageView).load(mTabInfos.get(position).getIcons_unselected()).into(imageView);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment;
            fragment = new CommonFragment();
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList(MainFragment.INTENT_INT_INDEX,
                    mTabInfos.get(position).getTab_child_infos());
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}
