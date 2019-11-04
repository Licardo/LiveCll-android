package cll.pf.com.livecll.adapter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
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

public class SmallImageAdapter extends RecyclerView.Adapter<SmallImageAdapter.ViewHolder> {

    private List<CllVo> mCllDatas;
    private Context mContext;

    public SmallImageAdapter(List<CllVo> cllDatas) {
        mCllDatas = cllDatas;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        mContext = viewGroup.getContext();
        View root = LayoutInflater.from(mContext).inflate(R.layout.item_list_cll, viewGroup, false);
        return new SmallImageAdapter.ViewHolder(root);
    }

    @Override
    public void onBindViewHolder(@NonNull final ViewHolder viewHolder, int i) {
        final CllVo cllData = mCllDatas.get(i);
        viewHolder.tvTitle.setText(cllData.getTitle());
        viewHolder.tvSource.setText(cllData.getSource());
        viewHolder.tvPlatform.setText(cllData.getPlatform());
        Glide.with(mContext).load(cllData.getImage()).into(viewHolder.ivImage);
        viewHolder.root.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Uri uri = Uri.parse(cllData.getUrl());
                String host = uri.getHost();
                if (host == null) {
                    Snackbar.make(viewHolder.root, "资讯链接不合法", Snackbar.LENGTH_LONG).show();
                    return;
                }
//                Intent intent = new Intent(mContext, WebViewActivity.class);
                Bundle bundle = new Bundle();
                bundle.putString("key", cllData.getUrl());
                bundle.putString("title", cllData.getSource());
                bundle.putString("des", cllData.getTitle());
//                intent.putExtras(bundle);
//                mContext.startActivity(intent);
                RouterPath.getInstance().navigation(mContext, ConstantPath.WEBVIEW_INDEX, bundle);
            }
        });
    }

    @Override
    public int getItemCount() {
        return mCllDatas==null ? 0 : mCllDatas.size();
    }

    static class ViewHolder extends RecyclerView.ViewHolder {
        ImageView ivImage;
        TextView tvTitle, tvSource, tvPlatform;
        View root;

        ViewHolder(@NonNull View itemView) {
            super(itemView);
            root = itemView;
            ivImage = itemView.findViewById(R.id.iv_image);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvSource = itemView.findViewById(R.id.tv_source);
            tvPlatform = itemView.findViewById(R.id.tv_platform);
        }
    }
}
