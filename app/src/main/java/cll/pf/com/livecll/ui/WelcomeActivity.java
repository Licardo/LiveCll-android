package cll.pf.com.livecll.ui;

import android.graphics.Color;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.indicator.IndicatorViewPager;
import com.shizhefei.view.indicator.slidebar.ColorBar;
import com.shizhefei.view.indicator.transition.OnTransitionTextListener;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;

@CllRouter(value = ConstantPath.WELCOME_INDEX)
public class WelcomeActivity extends BaseActivity {

    private IndicatorViewPager mIndicatorViewPager;

    @Override
    public void initView() {
        setContentView(R.layout.activity_welcome);
        ViewPager viewPager = findViewById(R.id.vp_content);
        Indicator indicator = findViewById(R.id.iv_indicator);
        mIndicatorViewPager = new IndicatorViewPager(indicator, viewPager);
        mIndicatorViewPager.setAdapter(new ViewPageAdapter());
        // 设置它可以自定义实现在滑动过程中，tab项的字体变化，颜色变化等等过渡效果
        mIndicatorViewPager.setIndicatorOnTransitionListener(new OnTransitionTextListener().setColor(Color.BLUE, Color.YELLOW).setSize(15, 12));
        // 设置它可以自定义滑动块的样式
        mIndicatorViewPager.setIndicatorScrollBar(new ColorBar(this, ContextCompat.getColor(this, R.color.colorAccent), 4));
    }

    @Override
    public void initData() {

    }
    class ViewPageAdapter extends IndicatorViewPager.IndicatorViewPagerAdapter {
        int[] images = { R.drawable.ic_launcher_background, R.drawable.ic_launcher_background, R.drawable.ic_launcher_background,
                R.drawable.ic_launcher_background };

        /**
         * 获取tab
         */
        @Override
        public View getViewForTab(int position, View convertView,
                                  ViewGroup container) {
            if (convertView == null) {
                convertView = LayoutInflater.from(WelcomeActivity.this).inflate(R.layout.tab_indicator, container,
                        false);
            }
            return convertView;
        }

        /**
         * 获取每一个界面
         */
        @Override
        public View getViewForPage(int position, View convertView,
                                   ViewGroup container) {
            if (convertView == null) {
                convertView = new View(getApplicationContext());
                convertView.setLayoutParams(new ViewGroup.LayoutParams(
                        ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
            }
            convertView.setBackgroundResource(images[position]);
            return convertView;
        }

        /**
         * 获取界面数量
         */
        @Override
        public int getCount() {
            return images.length;
        }
    }
}
