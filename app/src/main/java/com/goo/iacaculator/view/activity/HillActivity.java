package com.goo.iacaculator.view.activity;

import android.view.View;
import android.widget.MaterialEditText;
import android.widget.TextView;

import com.goo.iacaculator.R;
import com.goo.iacaculator.base.BaseSwipeBackActivity;
import com.goo.iacaculator.presenter.HillPresenter;
import com.goo.iacaculator.utils.ToastUtil;
import com.goo.iacaculator.view.vinterface.HillVInterface;

public class HillActivity extends BaseSwipeBackActivity<HillVInterface, HillPresenter> implements HillVInterface, View.OnClickListener {

    private MaterialEditText mEtClearText;
    private TextView mTvRun, mTvClean, mTvResult;

    @Override
    protected HillPresenter createPresenter() {
        return new HillPresenter(this);
    }

    @Override
    protected int setContentViewById() {
        return R.layout.activity_hill;
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initView() {
        findAllViewById();
        showToolbarAndShowNavigation("Hill算法演示");
        mTvRun.setOnClickListener(this);
        mTvClean.setOnClickListener(this);
    }

    @Override
    protected void findAllViewById() {
        mEtClearText = $(R.id.et_clearText);
        mTvClean = $(R.id.tv_clean);
        mTvRun = $(R.id.tv_run);
        mTvResult = $(R.id.tv_result);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clean:
                mEtClearText.setText("");
                mTvResult.setText("");
                break;
            case R.id.tv_run:
                if (mEtClearText.getText().toString().isEmpty()) {
                    ToastUtil.showToast(this, "请完整填写数据");
                } else {
                    mPresenter.runHill(mEtClearText.getText().toString());
                }
                break;
        }
    }

    @Override
    public void showResult(String result) {
        mTvResult.setText(result);
    }
}
