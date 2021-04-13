package cll.pf.com.livecll.ui;

import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.tencent.bugly.beta.Beta;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cll.pf.com.livecll.R;
import cll.pf.com.livecll.adapter.HomeAdapter;
import cll.pf.com.livecll.base.BaseActivity;
import cll.pf.com.livecll.constant.Constants;
import cll.pf.com.livecll.net.HttpUtils;
import cll.pf.com.livecll.router.CllRouter;
import cll.pf.com.livecll.router.ConstantPath;
import cll.pf.com.livecll.vo.CllVo;
import cll.pf.com.livecll.vo.HomeVo;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Request;
import okhttp3.Response;

@CllRouter(value = ConstantPath.INTRODUCE_INDEX)
public class IntroduceActivity extends BaseActivity {

//    private List<home_title> mTabInfos;
    private List<HomeVo> mTabInfos;
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
        String url = HttpUtils.addParams("/cll/home/info", null);
        Request request = new Request.Builder().get().url(url).build();
        HttpUtils.getClient().newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(Call call, IOException e) {
                mRefreshLayout.setRefreshing(false);
            }

            @Override
            public void onResponse(Call call, Response response) throws IOException {
                mRefreshLayout.setRefreshing(false);
                if (!response.isSuccessful()) {
                    return;
                }
                Gson gson = new Gson();
                String data = response.body()==null?"":response.body().string();
                Log.e("ssss", ">>>"+data);
                List<HomeVo> homeVos = gson.fromJson(data, new TypeToken<List<HomeVo>>(){}.getType());
                mTabInfos.addAll(homeVos);
                Collections.sort(mTabInfos);
                mRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                        loadCllDatas();
                    }
                });
            }
        });
    }

    private void loadCllDatas() {
        Map<String, String> params = new HashMap<>();
        params.put("page", "1");
        String url = HttpUtils.addParams("/cll/info", params);
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
                for (CllVo vo:cllVos) {
                    HomeVo title = new HomeVo();
                    title.setImage_url(vo.getImage());
                    title.setTitle(vo.getTitle());
                    title.setClick_url(vo.getUrl());
                    title.setStyle(Constants.STYLE_ARTICLE);
                    mTabInfos.add(title);
                }
                mRefreshLayout.post(new Runnable() {
                    @Override
                    public void run() {
                        mAdapter.notifyDataSetChanged();
                    }
                });

            }
        });
    }
}
