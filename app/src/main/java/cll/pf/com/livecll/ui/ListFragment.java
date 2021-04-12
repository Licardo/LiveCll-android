package cll.pf.com.livecll.ui;

import android.os.Bundle;
import android.view.View;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.wuxiaolong.pullloadmorerecyclerview.PullLoadMoreRecyclerView;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.adapter.SmallImageAdapter;
import cll.pf.com.livecll.base.BaseFragment;
import cll.pf.com.livecll.net.HttpUtils;
import cll.pf.com.livecll.view.CllItemDecoration;
import cll.pf.com.livecll.vo.CllVo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

public class ListFragment extends BaseFragment {

    private PullLoadMoreRecyclerView mRvContent;
    private SmallImageAdapter mAdapter;
    private List<CllVo> mCllDatas;
    private String cllSource, cllPlatform;
    private int page = 1;

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
                page++;
                loadCllDatas(cllSource, cllPlatform, page);
                mRvContent.setPullLoadMoreCompleted();
            }
        });
        loadCllDatas(cllSource, cllPlatform, page);
    }

    private void loadCllDatas(String source, String platform, int page) {
        Map<String, String> params = new HashMap<>();
        params.put("page", String.valueOf(page));
        params.put("source", source);
        params.put("platform", platform);
        String url = HttpUtils.addParams("http://www.livecll.com/cll/info", params);
        Request request = new Request.Builder().get().url(url).build();
        HttpUtils.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {

            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                if (!response.isSuccessful()) {
                    return;
                }
                Gson gson = new Gson();
                String data = response.body()==null?"":response.body().string();
                List<CllVo> cllVos = gson.fromJson(data, new TypeToken<List<CllVo>>(){}.getType());

                if (cllVos == null) {
                    return;
                }
                mCllDatas.addAll(cllVos);
                mRvContent.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
