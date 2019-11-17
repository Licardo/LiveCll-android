package cll.pf.com.livecll.adapter;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.constant.Constants;
import cll.pf.com.livecll.holder.ArticleHolder;
import cll.pf.com.livecll.holder.HomeBannerHolder;
import cll.pf.com.livecll.holder.HomeFunctionHolder;
import cll.pf.com.livecll.holder.HomeListHolder;
import cll.pf.com.livecll.holder.IndicatorPageAdapter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.router.RouterPath;
import cll.pf.com.livecll.utils.HomeStyle;
import cll.pf.com.livecll.vo.HomeVo;

public class HomeAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    private List<HomeVo> mTitles;

    public HomeAdapter(List<HomeVo> titles) {
        mTitles = titles;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        RecyclerView.ViewHolder viewHolder;
        LayoutInflater inflater = LayoutInflater.from(viewGroup.getContext());
        if (Constants.STYLE_BANNER_TYPE == i) {
            View view = inflater.inflate(R.layout.home_item_banner, viewGroup, false);
            viewHolder = new HomeBannerHolder(view);
        } else if (Constants.STYLE_FUNCTION_TYPE == i) {
            View view = inflater.inflate(R.layout.home_item_function, viewGroup, false);
            viewHolder = new HomeFunctionHolder(view);
        } else if (Constants.STYLE_LIST_TYPE == i) {
            View view = inflater.inflate(R.layout.home_item_card, viewGroup, false);
            viewHolder = new HomeListHolder(view);
        } else if (Constants.STYLE_ARTICLE_TYPE == i){
            View view = inflater.inflate(R.layout.item_list_cll, viewGroup, false);
            viewHolder = new ArticleHolder(view);
        } else {
            viewHolder = new RecyclerView.ViewHolder(new View(viewGroup.getContext())) {};
        }
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull final RecyclerView.ViewHolder viewHolder, int i) {
        if (viewHolder instanceof HomeBannerHolder) {

            HomeBannerHolder holder = (HomeBannerHolder) viewHolder;
            int size = mTitles.get(i).getHome_contents().size();
//            List<ImageView> imageViews = new ArrayList<>();
            List<String> urls = new ArrayList<>();
            for (int j = 0; j < size; j++) {
                ImageView view = new ImageView(holder.mViewPager.getContext());
//                imageViews.add(view);
                urls.add(mTitles.get(i).getHome_contents().get(j).getImage_url());
            }
            IndicatorPageAdapter mPageAdapter = new IndicatorPageAdapter(holder.mViewPager.getContext(), urls);
            holder.mComponent.setAdapter(mPageAdapter);
            holder.mComponent.startAutoPlay();
//                holder.mComponent.setAutoPlayTime(3000);
        } else if (viewHolder instanceof HomeFunctionHolder) {
            final HomeFunctionHolder holder = (HomeFunctionHolder) viewHolder;
            holder.mTextView.setText(mTitles.get(i).getTile());
            Glide.with(holder.mImageView).load(mTitles.get(i).getImage_url()).into(holder.mImageView);
            final String path = mTitles.get(i).getClick_url();
            holder.setListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    // 跳转到内页
                    RouterPath.getInstance().navigation(holder.mImageView.getContext(), path);
                }
            });
        } else if (viewHolder instanceof HomeListHolder) {
            final HomeListHolder holder = (HomeListHolder) viewHolder;
            holder.mTextView.setText(mTitles.get(i).getTile());
            holder.mLinearLayout.removeAllViews();
            int size = mTitles.get(i).getHome_contents().size();
            for (int j = 0; j < size; j++) {
                View view = LayoutInflater.from(holder.mLinearLayout.getContext()).
                        inflate(R.layout.home_item_function, null);
                TextView textView = view.findViewById(R.id.tv_name);
                ImageView imageView = view.findViewById(R.id.iv_image);
                textView.setText(mTitles.get(i).getHome_contents().get(j).getContent());
                Glide.with(imageView).load(mTitles.get(i).getHome_contents().get(j).getImage_url()).into(imageView);
                final String path = mTitles.get(i).getHome_contents().get(j).getClick_url();
                final String content = mTitles.get(i).getHome_contents().get(j).getContent();
                imageView.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        if (TextUtils.isEmpty(path)) {
                            return;
                        }
                        if (path.startsWith("livecll-http")) {
                            String url = path.replace("livecll-http", "http");
                            Bundle bundle = new Bundle();
                            bundle.putString("key", url);
                            bundle.putString("title", content);
                            bundle.putString("des", "慢淋资讯");
                            RouterPath.getInstance().navigation(holder.mTextView.getContext(),
                                    ConstantPath.WEBVIEW_INDEX, bundle);
                        } else {
                            RouterPath.getInstance().navigation(holder.mTextView.getContext(), path);
                        }
                    }
                });
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                        ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                params.weight = 1;
                view.setLayoutParams(params);
                holder.mLinearLayout.addView(view);
            }
        } else if (viewHolder instanceof ArticleHolder) {
            final ArticleHolder holder = (ArticleHolder) viewHolder;
            final HomeVo title = mTitles.get(i);
            holder.tvTitle.setText(title.getTile());
//            holder.tvSource.setText(cllData.getSource());
//            holder.tvPlatform.setText(cllData.getPlatform());
            Glide.with(holder.ivImage).load(title.getImage_url()).into(holder.ivImage);
            holder.line.setVisibility(View.VISIBLE);
            holder.root.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Bundle bundle = new Bundle();
                    bundle.putString("key", title.getClick_url());
//                    bundle.putString("title", cllData.getSource());
                    bundle.putString("des", title.getTile());
                    RouterPath.getInstance().navigation(holder.ivImage.getContext(),
                            ConstantPath.WEBVIEW_INDEX, bundle);
                }
            });
        }
    }

    @Override
    public int getItemCount() {
        return mTitles!=null?mTitles.size():0;
    }

    @Override
    public int getItemViewType(int position) {
        Short type = HomeStyle.mStyle.get(mTitles.get(position).getStyle());
        return type == null ? -1 : type;
    }

}
