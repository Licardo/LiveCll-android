package cll.pf.com.livecll.listener;

import android.content.Context;
import android.content.res.Resources;
import android.support.constraint.ConstraintLayout;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.shizhefei.view.indicator.Indicator;
import com.shizhefei.view.utils.ColorGradient;

import cll.pf.com.livecll.R;

public class OnTransitionViewListener implements Indicator.OnTransitionListener {
    private float selectSize = -1;
    private float unSelectSize = -1;
    private ColorGradient gradient;
    private float dFontFize = -1;
    private String[] images = {};
    private String[] unImages = {} ;

    private boolean isPxSize = false;

    public OnTransitionViewListener() {
        super();
    }

    public OnTransitionViewListener(float selectSize, float unSelectSize, int selectColor, int unSelectColor, String[] images, String[] unImages) {
        super();
        setColor(selectColor, unSelectColor);
        setSize(selectSize, unSelectSize);
        this.images = images;
        this.unImages = unImages;
    }

    public final OnTransitionViewListener setSize(float selectSize, float unSelectSize) {
        isPxSize = false;
        this.selectSize = selectSize;
        this.unSelectSize = unSelectSize;
        this.dFontFize = selectSize - unSelectSize;
        return this;
    }

    public final OnTransitionViewListener setValueFromRes(Context context, int selectColorId, int unSelectColorId, int selectSizeId,
                                                          int unSelectSizeId) {
        setColorId(context, selectColorId, unSelectColorId);
        setSizeId(context, selectSizeId, unSelectSizeId);
        return this;
    }

    public final OnTransitionViewListener setColorId(Context context, int selectColorId, int unSelectColorId) {
        Resources res = context.getResources();
        setColor(res.getColor(selectColorId), res.getColor(unSelectColorId));
        return this;
    }

    public final OnTransitionViewListener setSizeId(Context context, int selectSizeId, int unSelectSizeId) {
        Resources res = context.getResources();
        setSize(res.getDimensionPixelSize(selectSizeId), res.getDimensionPixelSize(unSelectSizeId));
        isPxSize = true;
        return this;
    }

    public final OnTransitionViewListener setColor(int selectColor, int unSelectColor) {
        gradient = new ColorGradient(unSelectColor, selectColor, 100);
        return this;
    }

    /**
     * 如果tabItemView 不是目标的TextView，那么你可以重写该方法返回实际要变化的TextView
     *
     * @param tabItemView
     *            Indicator的每一项的view
     * @return
     */
    private ConstraintLayout getConstraintLayout(View tabItemView) {
        if (tabItemView instanceof ConstraintLayout) {
            return (ConstraintLayout) tabItemView;
        }
        return null;
    }

    @Override
    public void onTransition(View view, int position, float selectPercent) {
        ConstraintLayout selectTextView = getConstraintLayout(view);
        if (selectTextView == null) {
            return;
        }
//        try {
//            MobclickAgent.onEvent(selectTextView.getContext(), Constants.TAB_NAMES[position]);
//        } catch (NullPointerException e){
//            e.printStackTrace();
//        }
        TextView textView = selectTextView.findViewById(R.id.tv_title);
        ImageView imageView = selectTextView.findViewById(R.id.iv_image);
        if (gradient != null) {
            textView.setTextColor(gradient.getColor((int) (selectPercent * 100)));
        }
        if (imageView != null) {
            if (selectPercent != 0) {
//                imageView.setImageResource(images.length > position ? images[position] : -1);
                Glide.with(imageView).load(images.length > position ? images[position] : "").into(imageView);
            } else {
//                imageView.setImageResource(unImages.length > position ? unImages[position] : -1);
                Glide.with(imageView).load(unImages.length > position ? unImages[position] : "").into(imageView);
            }
        }
        if (unSelectSize > 0 && selectSize > 0) {
            if (isPxSize) {
                textView.setTextSize(TypedValue.COMPLEX_UNIT_PX, unSelectSize + dFontFize * selectPercent);
            } else {
                textView.setTextSize(unSelectSize + dFontFize * selectPercent);
            }
        }
    }
}
