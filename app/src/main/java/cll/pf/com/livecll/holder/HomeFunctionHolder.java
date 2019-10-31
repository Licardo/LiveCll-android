package cll.pf.com.livecll.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cll.pf.com.livecll.R;

public class HomeFunctionHolder extends RecyclerView.ViewHolder {
    public ImageView mImageView;
    public TextView mTextView;
    public View root;
    public View.OnClickListener listener;

    public HomeFunctionHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        mImageView = itemView.findViewById(R.id.iv_image);
        mTextView = itemView.findViewById(R.id.tv_name);
    }
    public void setListener(View.OnClickListener listener) {
        root.setOnClickListener(listener);
    }
}
