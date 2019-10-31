package cll.pf.com.livecll.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public abstract class BaseFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = LayoutInflater.from(this.getContext()).inflate(getLayoutId(), container, false);
        initView(view);
        initData();
        return view;
    }

    public abstract int getLayoutId();

    /**
     * 初始化控件
     */
    public void initView(View view){

    }

    /**
     * 初始化数据相关
     */
    public void initData(){

    }
}