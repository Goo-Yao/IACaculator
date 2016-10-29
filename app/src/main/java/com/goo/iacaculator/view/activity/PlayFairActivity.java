package com.goo.iacaculator.view.activity;

import android.view.View;
import android.widget.MaterialEditText;
import android.widget.TextView;

import com.goo.iacaculator.R;
import com.goo.iacaculator.base.BaseSwipeBackActivity;
import com.goo.iacaculator.presenter.PlayFairPresenter;
import com.goo.iacaculator.view.vinterface.PlayFairVInterface;

public class PlayFairActivity extends BaseSwipeBackActivity<PlayFairVInterface, PlayFairPresenter> implements PlayFairVInterface, View.OnClickListener {

    private MaterialEditText mEtClearText, mEtKey, mEtSgin;
    private TextView mTvRun, mTvClean, mTvResult;


    @Override
    protected PlayFairPresenter createPresenter() {
        return new PlayFairPresenter(this);
    }

    @Override
    protected int setContentViewById() {
        return R.layout.activity_play_fair;
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initView() {
        findAllViewById();
        showToolbarAndShowNavigation("PlayFair算法演示");
        setAllListener();
    }

    private void setAllListener() {
        mTvRun.setOnClickListener(this);
        mTvClean.setOnClickListener(this);
    }

    @Override
    protected void findAllViewById() {
        mEtClearText = $(R.id.et_clearText);
        mEtKey = $(R.id.et_key);
        mEtSgin = $(R.id.et_sgin);
        mTvClean = $(R.id.tv_clean);
        mTvRun = $(R.id.tv_run);
        mTvResult = $(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_run:
                mPresenter.runPlayFair(this, mEtClearText.getText().toString(), mEtKey.getText().toString(), mEtSgin.getText().toString());
                break;
            case R.id.tv_clean:
                cleanData();
                break;
        }

    }

    /**
     * 清空数据
     */
    private void cleanData() {
        mEtClearText.setText("");
        mEtSgin.setText("");
        mEtKey.setText("");
        mTvResult.setText("");
    }

    @Override
    public void showResult(String result) {
        mTvResult.setText(result);
    }
}
