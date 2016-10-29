package com.goo.iacaculator.view.activity;

import android.webkit.WebView;

import com.goo.iacaculator.R;
import com.goo.iacaculator.base.BaseSwipeBackActivity;
import com.goo.iacaculator.presenter.SubmitPresenter;
import com.goo.iacaculator.view.vinterface.SubmitVInterface;

public class SubmitActivity extends BaseSwipeBackActivity<SubmitVInterface, SubmitPresenter> implements SubmitVInterface {

    private WebView mWvInfo;

    @Override
    protected SubmitPresenter createPresenter() {
        return new SubmitPresenter(this);
    }

    @Override
    protected int setContentViewById() {
        return R.layout.activity_submit;
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initView() {
        mWvInfo = $(R.id.wv_info);
        showToolbarAndShowNavigation("反馈");
        mWvInfo.loadUrl("file:///android_asset/info.html");
    }

    @Override
    protected void findAllViewById() {

    }
}
