package cll.pf.com.livecll.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.RecyclerView.ItemDecoration;
import android.view.View;

public class CllItemDecoration extends ItemDecoration {
    private int height;
    private int margin;
    private int weight;
    private Paint mPaint;
    private Paint mOverPaint;

    /**
     *
     * @param height 分割线的高度
     * @param margin 分割线左右两边的边距
     */
    public CllItemDecoration(Context context, int height, int margin) {
        this.height = height;
        this.margin = margin;
        weight = context.getResources().getDisplayMetrics().widthPixels;
        mPaint=new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mPaint.setColor(Color.parseColor("#7f999999"));
        mOverPaint = new Paint(Paint.ANTI_ALIAS_FLAG|Paint.DITHER_FLAG);
        mOverPaint.setColor(Color.BLUE);
    }

    @Override
    public void onDraw(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDraw(c, parent, state);
        int count = parent.getChildCount();
        for (int i = 0; i < count; i++) {
            View view = parent.getChildAt(i);
            int top = view.getTop()-height;
            int bottom = top + height;
            c.drawRect(margin, top, weight-margin, bottom, mPaint);
        }
    }

    @Override
    public void onDrawOver(@NonNull Canvas c, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.onDrawOver(c, parent, state);
//        在item和decoration的上层绘制 使用比如悬浮窗
//        View view = parent.getChildAt(0);
//        if (view == null) {
//            return;
//        }
//        int bottom = view.getBottom();
//        if (bottom < height) {
//            c.drawRect(margin, 0, weight-margin, bottom, mOverPaint);
//        } else {
//            c.drawRect(margin, 0, weight-margin, height, mOverPaint);
//        }
    }

    @Override
    public void getItemOffsets(@NonNull Rect outRect, @NonNull View view, @NonNull RecyclerView parent, @NonNull RecyclerView.State state) {
        super.getItemOffsets(outRect, view, parent, state);
        outRect.top = height;
    }
}
