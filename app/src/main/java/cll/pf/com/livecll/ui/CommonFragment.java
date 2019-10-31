package cll.pf.com.livecll.ui;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.shizhefei.view.indicator.FixedIndicatorView;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.viewpager.SViewPager;

import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseFragment;
import cll.pf.com.livecll.constant.Constants;
import cll.pf.com.livecll.listener.OnTransitionViewListener;
import cll.pf.com.livecll.model.TabsModel;
import cll.pf.com.livecll.vo.tab_child_info;

public class CommonFragment extends BaseFragment {
    private IndicatorViewPager indicatorViewPager;
    private FixedIndicatorView indicator;
//    private String mTabName;
    private List<tab_child_info> mTabChildInfos;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_common;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        indicator = view.findViewById(R.id.iv_indicator);
        SViewPager sViewPager = view.findViewById(R.id.vp_fragment);
        OnTransitionViewListener listener = new OnTransitionViewListener();
        listener.setSize(15, 15);
        listener.setColor(Color.parseColor("#FFDC35"), Color.BLACK);
        indicator.setOnTransitionListener(listener);
        indicatorViewPager = new IndicatorViewPager(indicator, sViewPager);
        sViewPager.setCanScroll(true);
        sViewPager.setOffscreenPageLimit(4);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
//            mTabName = bundle.getString(MainFragment.INTENT_INT_INDEX);
            mTabChildInfos = bundle.getParcelableArrayList(MainFragment.INTENT_INT_INDEX);
        }
        if (mTabChildInfos == null) {
            return;
        }
        TabsModel[] tabsModels;
//        if (Constants.TAB_NAMES[2].equals(mTabName)) {
//            String[] tabNames = {"专家说", "淋巴瘤快报", "免费新药"};
//            String[] sources = {"淋巴瘤之家", "淋巴瘤之家", "淋巴瘤之家"};
//            String[] platforms = {"专家说", "淋巴瘤快报", "免费新药"};
//            tabsModels = handleData(tabNames, sources, platforms);
//            indicatorViewPager.setAdapter(new ViewPagerAdapter(this.getChildFragmentManager(), tabsModels));
//        } else if (Constants.TAB_NAMES[0].equals(mTabName)) {
        int len = mTabChildInfos.size();
        String[] tabNames = new String[len];
        String[] sources = new String[len];
        String[] platforms = new String[len];
        String[] type = new String[len];
        for (int i = 0; i < len; i++) {
            if (mTabChildInfos.get(i).getShow() == Constants.HIDDEN_FRAGMENT) {
                continue;
            }
            tabNames[i] = mTabChildInfos.get(i).getTab_name();
            sources[i] = mTabChildInfos.get(i).getSource();
            platforms[i] = mTabChildInfos.get(i).getPlatform();
            type[i] = mTabChildInfos.get(i).getShow_type();
        }
        tabsModels = handleData(tabNames, sources, platforms, type);
        indicatorViewPager.setAdapter(new ViewPagerAdapter(this.getChildFragmentManager(), tabsModels));
    }

    class ViewPagerAdapter extends IndicatorViewPager.IndicatorFragmentPagerAdapter{
        ViewPagerAdapter(FragmentManager fragmentManager, TabsModel[] tabNames) {
            this(fragmentManager);
            this.tabNames = tabNames;
        }

        private TabsModel[] tabNames;
        private LayoutInflater inflater;

        ViewPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
            inflater = LayoutInflater.from(CommonFragment.this.getContext());
        }

        @Override
        public int getCount() {
            return tabNames.length;
        }

        @Override
        public View getViewForTab(int position, View convertView, ViewGroup container) {
            if (convertView == null) {
                convertView = inflater.inflate(R.layout.tab_indicator_text, container, false);
            }
            TextView textView = convertView.findViewById(R.id.tv_title);
            textView.setText(tabNames[position].getTabName());
            return convertView;
        }

        @Override
        public Fragment getFragmentForPage(int position) {
            Fragment fragment;
            if (Constants.SHOW_TYPE_BIG.equals(tabNames[position].getShowType())) {
                fragment = new MainFragment();
            } else {
                fragment = new ListFragment();
            }
            Bundle bundle = new Bundle();
            bundle.putString(MainFragment.INTENT_INT_SOURCE, tabNames[position].getTabParams());
            bundle.putString(MainFragment.INTENT_INT_PLATFORM, tabNames[position].getTabPlatform());
            fragment.setArguments(bundle);
            return fragment;
        }
    }

    public TabsModel[] handleData(String[]... param) {
        int size = param[0].length;
        TabsModel[] tabsModels = new TabsModel[size];
        for (int i = 0; i < size; i++) {
            TabsModel model3;
//            if (param.length == 3) {
//                model3 = new TabsModel(param[0][i], param[1][i], param[2][i], param[3][i]);
//            } else {
//                model3 = new TabsModel(param[0][i], param[1][i]);
//            }
            model3 = new TabsModel(param[0][i], param[1][i], param[2][i], param[3][i]);
            tabsModels[i] = model3;
        }
        return tabsModels;
    }
}
