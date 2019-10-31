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
import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;
import com.umeng.analytics.MobclickAgent;

import java.util.Collections;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.listener.OnTransitionViewListener;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.vo.tab_child_info;
import cll.pf.com.livecll.vo.tab_info;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

@CllRouter(value = ConstantPath.TAB_INDEX)
public class TabActivity extends BaseActivity {
    private IndicatorViewPager indicatorViewPager;
    private FixedIndicatorView indicator;
    private SViewPager sViewPager;
    private List<tab_info> mTabInfos;

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
        BmobQuery<tab_info> tabInfoBmobQuery = new BmobQuery<>();
        tabInfoBmobQuery.findObjects(new FindListener<tab_info>() {
            @Override
            public void done(List<tab_info> list, BmobException e) {
                if (list == null) {
                    return;
                }
                mTabInfos = list;
                Collections.sort(mTabInfos);
                getAllChildTabInfos();
            }
        });
    }

    private void getAllChildTabInfos() {
        BmobQuery<tab_child_info> query = new BmobQuery<>();
        query.findObjects(new FindListener<tab_child_info>() {
            @Override
            public void done(List<tab_child_info> list, BmobException e) {
                if (list == null) {
                    return;
                }
                handleData(list);
                initViewPage();
            }
        });
    }

    private void handleData(List<tab_child_info> tabChildInfos) {
        int size = mTabInfos.size();
        for (int i = 0; i < size; i++) {
            tab_info info = mTabInfos.get(i);
            int len = tabChildInfos.size();
            for (int j = 0; j < len; j++) {
                tab_child_info childInfo = tabChildInfos.get(j);
                if (info.getObjectId().equals(childInfo.getRelation().getObjectId())) {
                    info.getTabChildInfos().add(childInfo);
                }
            }
            Collections.sort(info.getTabChildInfos());
        }
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
//            imageView.setImageResource(tabUnIcons[position]);
            Glide.with(imageView).load(mTabInfos.get(position).getIcons_unselected()).into(imageView);
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment;
            fragment = new CommonFragment();
            Bundle bundle = new Bundle();
//            bundle.putString(MainFragment.INTENT_INT_INDEX, Constants.TAB_NAMES[position]);
            bundle.putParcelableArrayList(MainFragment.INTENT_INT_INDEX,
                    mTabInfos.get(position).getTabChildInfos());
            fragment.setArguments(bundle);
            return fragment;
        }
    }
}
