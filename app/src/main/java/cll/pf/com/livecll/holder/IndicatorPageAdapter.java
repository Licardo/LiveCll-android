package cll.pf.com.livecll.holder;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.shizhefei.view.indicator.IndicatorViewPager;

import java.util.List;

import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.router.RouterPath;

public class IndicatorPageAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {

    List<String> mUrls, mPageUrls;
    Context mContext;

    public IndicatorPageAdapter(Context context, List<String> urls, List<String> pages) {
        mUrls = urls;
        mContext = context;
        mPageUrls = pages;
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
    public View getViewForPage(final int position, View convertView, ViewGroup container) {
        if (convertView == null) {
            convertView = new ImageView(mContext);
            convertView.setLayoutParams(new ViewGroup.LayoutParams(
                    ViewGroup.LayoutParams.MATCH_PARENT,
                    ViewGroup.LayoutParams.MATCH_PARENT));
        }
        convertView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = mPageUrls.get(position);
                if (TextUtils.isEmpty(path)) {
                    return;
                }
                if (path.startsWith("livecll-http")) {
                    String url = path.replace("livecll-http", "http");
                    Bundle bundle = new Bundle();
                    bundle.putString("key", url);
                    bundle.putString("title", "最热资讯");
                    bundle.putString("des", "慢淋资讯");
                    RouterPath.getInstance().navigation(mContext,
                            ConstantPath.WEBVIEW_INDEX, bundle);
                } else {
                    RouterPath.getInstance().navigation(mContext, path);
                }
            }
        });
        Glide.with(convertView).load(mUrls.get(position)).into((ImageView) convertView);
        return convertView;
    }
}
