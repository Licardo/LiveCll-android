package cll.pf.com.livecll.ui;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.adapter.SmallImageAdapter;
import cll.pf.com.livecll.base.BaseFragment;
import cll.pf.com.livecll.view.CllItemDecoration;
import cll.pf.com.livecll.vo.cll_data;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

public class ListFragment extends BaseFragment {

    private PullLoadMoreRecyclerView mRvContent;
    private SmallImageAdapter mAdapter;
    private List<cll_data> mCllDatas;
    private String cllSource, cllPlatform;

    @Override
    public int getLayoutId() {
        return R.layout.fragment_list;
    }

    @Override
    public void initView(View view) {
        super.initView(view);
        mRvContent = view.findViewById(R.id.rv_content);
    }

    @Override
    public void initData() {
        super.initData();
        Bundle bundle = getArguments();
        if (bundle != null) {
            cllSource = bundle.getString(MainFragment.INTENT_INT_SOURCE);
            cllPlatform = bundle.getString(MainFragment.INTENT_INT_PLATFORM);
        }

        mCllDatas = new ArrayList<>();
        mAdapter = new SmallImageAdapter(mCllDatas);
        mRvContent.setAdapter(mAdapter);
        mRvContent.setLinearLayout();
        mRvContent.setPullRefreshEnable(false);
        mRvContent.addItemDecoration(new CllItemDecoration(this.getContext(), 1, 0));
        mRvContent.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                loadCllDatas(cllSource, cllPlatform);
                mRvContent.setPullLoadMoreCompleted();
            }
        });
        loadCllDatas(cllSource, cllPlatform);
    }

    private void loadCllDatas(String value, String platform) {
        BmobQuery<cll_data> query = new BmobQuery<>("cll_data");
        int skipCount = mCllDatas.size();
        query.addWhereEqualTo("source", value);
        if (!TextUtils.isEmpty(cllPlatform)) {
            query.addWhereEqualTo("platform", platform);
        }
        query.order("-send_time");
        query.setSkip(skipCount).setLimit(10).findObjects(new FindListener<cll_data>() {
            @Override
            public void done(List<cll_data> list, BmobException e) {
                if (list == null) {
                    return;
                }
                mCllDatas.addAll(list);
                mAdapter.notifyDataSetChanged();
            }
        });
    }
}
