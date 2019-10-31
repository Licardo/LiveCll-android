package cll.pf.com.livecll.holder;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cll.pf.com.livecll.R;

public class ArticleHolder extends RecyclerView.ViewHolder{
    public ImageView ivImage;
    public TextView tvTitle, tvSource, tvPlatform;
    public View root;
    public View line;

    public ArticleHolder(@NonNull View itemView) {
        super(itemView);
        root = itemView;
        ivImage = itemView.findViewById(R.id.iv_image);
        tvTitle = itemView.findViewById(R.id.tv_title);
        tvSource = itemView.findViewById(R.id.tv_source);
        tvPlatform = itemView.findViewById(R.id.tv_platform);
        line = itemView.findViewById(R.id.line);
    }
}
