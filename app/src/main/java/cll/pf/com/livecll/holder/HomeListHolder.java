package cll.pf.com.livecll.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import cll.pf.com.livecll.R;

public class HomeListHolder extends RecyclerView.ViewHolder {
    public TextView mTextView;
    public LinearLayout mLinearLayout;

    public HomeListHolder(@NonNull View itemView) {
        super(itemView);
        mTextView = itemView.findViewById(R.id.tv_title);
        mLinearLayout = itemView.findViewById(R.id.ll_content);
    }
}
