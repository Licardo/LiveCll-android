package cll.pf.com.livecll.holder;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

public class IndicatorPageAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    List<ImageView> mImageViews;
    List<String> mUrls;
    Context mContext;

    public IndicatorPageAdapter(Context context, List<ImageView> imageViews, List<String> urls) {
        mImageViews = imageViews;
        mUrls = urls;
        mContext = context;
    }

    @Override
    public int getCount() {
        return mImageViews == null ? 0 : mImageViews.size();
    }

    @Override
    public View getViewForTab(int position, View convertView, ViewGroup container) {
        return new View(mContext);
    }

    @Override
    public View getViewForPage(int position, View convertView, ViewGroup container) {
        ImageView imageView = mImageViews.get(position);
        Glide.with(imageView).load(mUrls.get(position)).into(imageView);
        return imageView;
    }
}
