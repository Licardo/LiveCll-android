package cll.pf.com.livecll.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.router.RouterPath;
import cll.pf.com.livecll.vo.CllVo;

public class MainAdapter extends RecyclerView.Adapter<MainAdapter.MainViewHolder> {
    private List<CllVo> mCllDatas;
    private Context mContext;

    public MainAdapter(List<CllVo> cllDatas) {
        mCllDatas = cllDatas;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_cll, viewGroup, false);
        return new MainViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final MainViewHolder mainViewHolder, int i) {
        final CllVo cllData = mCllDatas.get(i);
        mainViewHolder.tvTitle.setText(cllData.getTitle());
        mainViewHolder.tvTag.setText(new StringBuilder().append(cllData.getPlatform()));
        Glide.with(mContext).load(cllData.getImage()).into(mainViewHolder.ivImage);
        mainViewHolder.mCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(cllData.getUrl());
                String host = uri.getHost();
                if (host == null) {
                    Snackbar.make(mainViewHolder.mCardView, "资讯链接不合法", Snackbar.LENGTH_LONG).show();
                    return;
                }
                Bundle bundle = new Bundle();
                bundle.putString("key", cllData.getUrl());
                RouterPath.getInstance().navigation(mContext, ConstantPath.WEBVIEW_INDEX, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCllDatas == null ? 0 : mCllDatas.size();
    }

    static class MainViewHolder extends RecyclerView.ViewHolder{

        TextView tvTitle, tvTag;
        ImageView ivImage;
        CardView mCardView;

        MainViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvTag = itemView.findViewById(R.id.tv_tag);
            ivImage = itemView.findViewById(R.id.iv_image);
            mCardView = itemView.findViewById(R.id.cv_card);
        }
    }
}
