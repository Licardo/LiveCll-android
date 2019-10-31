package cll.pf.com.livecll.holder;

import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.shizhefei.view.indicator.BannerComponent;
import com.shizhefei.view.indicator.FixedIndicatorView;

import cll.pf.com.livecll.R;

public class HomeBannerHolder extends RecyclerView.ViewHolder {
    public ViewPager mViewPager;
    public FixedIndicatorView mIndicatorView;
    public BannerComponent mComponent;
    public HomeBannerHolder(@NonNull View itemView) {
        super(itemView);
        mViewPager = itemView.findViewById(R.id.banner_content);
        mIndicatorView = itemView.findViewById(R.id.indicator_dot);
        mComponent = new BannerComponent(mIndicatorView, mViewPager, false);
    }
}
