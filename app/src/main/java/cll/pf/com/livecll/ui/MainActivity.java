package cll.pf.com.livecll.ui;

import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.util.ArrayList;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.adapter.MainAdapter;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.view.CllItemDecoration;
import cll.pf.com.livecll.vo.cll_data;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

@CllRouter(value = ConstantPath.MAIN_INDEX)
public class MainActivity extends BaseActivity {

    private PullLoadMoreRecyclerView mRvContent;
    private MainAdapter mAdapter;
    private List<cll_data> mCllDatas;

    @Override
    public void initView() {
        setContentView(R.layout.activity_main);
        mRvContent = findViewById(R.id.rv_content);
    }

    @Override
    public void initData() {
        mCllDatas = new ArrayList<>();
        mAdapter = new MainAdapter(mCllDatas);
        mRvContent.setAdapter(mAdapter);
        mRvContent.setLinearLayout();
        mRvContent.setPullRefreshEnable(false);
        mRvContent.addItemDecoration(new CllItemDecoration(this, 30, 0));
        mRvContent.setOnPullLoadMoreListener(new PullLoadMoreRecyclerView.PullLoadMoreListener() {
            @Override
            public void onRefresh() {
            }

            @Override
            public void onLoadMore() {
                loadCllDatas();
                mRvContent.setPullLoadMoreCompleted();
            }
        });
        loadCllDatas();
    }

    private void loadCllDatas() {
        BmobQuery<cll_data> query = new BmobQuery<>("cll_data");
        int skipCount = mCllDatas.size();
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
