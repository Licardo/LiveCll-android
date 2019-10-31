package cll.pf.com.livecll.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.tencent.bugly.beta.Beta;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.adapter.HomeAdapter;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.constant.Constants;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.vo.cll_data;
import cll.pf.com.livecll.vo.home_content;
import cll.pf.com.livecll.vo.home_title;
import cn.bmob.v3.BmobQuery;
import cn.bmob.v3.exception.BmobException;
import cn.bmob.v3.listener.FindListener;

@CllRouter(value = ConstantPath.INTRODUCE_INDEX)
public class IntroduceActivity extends BaseActivity {

    private List<home_title> mTabInfos;
    private SwipeRefreshLayout mRefreshLayout;
    private HomeAdapter mAdapter;

    @Override
    public void initView() {
        setContentView(R.layout.activity_introduce);
        RecyclerView recyclerView = findViewById(R.id.rv_content);
        mRefreshLayout = findViewById(R.id.swipe_refresh);
        mTabInfos = new ArrayList<>();
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 4);
        gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
            @Override
            public int getSpanSize(int i) {
                if (Constants.STYLE_FUNCTION.equals(mTabInfos.get(i).getStyle())) {
                    return 1;
                }
                return 4;
            }
        });
        recyclerView.setLayoutManager(gridLayoutManager);
        mAdapter = new HomeAdapter(mTabInfos);
        recyclerView.setAdapter(mAdapter);

        mRefreshLayout.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                mTabInfos.clear();
                getAllHomeTitle();
            }
        });
    }

    @Override
    public void initData() {
        Beta.checkUpgrade();//检查版本号
        getAllHomeTitle();
    }

    private void getAllHomeTitle() {
        BmobQuery<home_title> query = new BmobQuery<>();
        query.findObjects(new FindListener<home_title>() {
            @Override
            public void done(List<home_title> list, BmobException e) {
                mRefreshLayout.setRefreshing(false);
                if (list == null) {
                    return;
                }
                mTabInfos.addAll(list);
                Collections.sort(mTabInfos);
                getAllHomeContent();
                loadCllDatas("", "");
            }
        });
    }

    private void getAllHomeContent() {
        BmobQuery<home_content> query = new BmobQuery<>();
        query.findObjects(new FindListener<home_content>() {
            @Override
            public void done(List<home_content> list, BmobException e) {
                if (list == null) {
                    return;
                }
                Collections.sort(list);
                handleData(list);
            }
        });
    }

    private void loadCllDatas(String value, String platform) {
        BmobQuery<cll_data> query = new BmobQuery<>();
//        query.addWhereEqualTo("source", "杨申淼");
//        if (!TextUtils.isEmpty(cllPlatform)) {
//            query.addWhereEqualTo("platform", "杨申淼");
//        }
        query.order("-send_time");
        query.setSkip(0).setLimit(10).findObjects(new FindListener<cll_data>() {
            @Override
            public void done(List<cll_data> list, BmobException e) {
                if (list == null) {
                    return;
                }
                for (cll_data data:list) {
                    home_title title = new home_title();
                    title.setImage_url(data.getImage());
                    title.setTitle(data.getTitle());
                    title.setClick_url(data.getUrl());
                    title.setStyle(Constants.STYLE_ARTICLE);
                    mTabInfos.add(title);
                }
                mAdapter.notifyDataSetChanged();
            }
        });
    }

    private void handleData(List<home_content> contents) {
        int len = mTabInfos.size();
        for (int i = 0; i < len; i++) {
            home_title title = mTabInfos.get(i);
            int size = contents.size();
            for (int j = 0; j < size; j++) {
                if (title.getObjectId().equals(contents.get(j).getTitle().getObjectId())) {
                    title.getHomeContents().add(contents.get(j));
                }
            }
        }
        mAdapter.notifyDataSetChanged();
    }
}
