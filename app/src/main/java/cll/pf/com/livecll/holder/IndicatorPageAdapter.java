package cll.pf.com.livecll.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

public class IndicatorPageAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    List<String> mUrls;
    Context mContext;

    public IndicatorPageAdapter(Context context, List<String> urls) {
        mUrls = urls;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mUrls == null ? 0 : mUrls.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        return new View(mContext);
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new ImageView(mContext);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        Glide.with(convertView).load(mUrls.get(position)).into((ImageView) convertView);
        return convertView;
    }
}
