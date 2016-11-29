package com.goo.iacaculator.view.activity;

import android.view.View;
import android.widget.MaterialEditText;
import android.widget.TextView;
import android.widget.Toast;

import com.goo.iacaculator.R;
import com.goo.iacaculator.base.BaseSwipeBackActivity;
import com.goo.iacaculator.presenter.RSAPresenter;
import com.goo.iacaculator.view.vinterface.RSAVInterface;

public class RSAActivity extends BaseSwipeBackActivity<RSAVInterface, RSAPresenter> implements RSAVInterface, View.OnClickListener {

    private MaterialEditText mEtClearText, mEtPublicText, mEtPrime1, mEtPrime2;
    private TextView mTvRun, mTvClean, mTvResult;

    @Override
    protected RSAPresenter createPresenter() {
        return new RSAPresenter(this);
    }

    @Override
    protected int setContentViewById() {
        return R.layout.activity_rsa;
    }

    @Override
    protected void initAttributes() {

    }

    @Override
    protected void initView() {
        findAllViewById();
        showToolbarAndShowNavigation("RSA加密算法演示");
        setAllListener();
        Toast.makeText(this, "暂时仅支持小数据演示", Toast.LENGTH_SHORT).show();
    }

    private void setAllListener() {
        mTvClean.setOnClickListener(this);
        mTvRun.setOnClickListener(this);
    }

    @Override
    protected void findAllViewById() {
        mEtClearText = $(R.id.et_clear_text);
        mEtPrime1 = $(R.id.et_prime_1);
        mEtPrime2 = $(R.id.et_prime2);
        mEtPublicText = $(R.id.et_public_key);
        mTvClean = $(R.id.tv_clean);
        mTvResult = $(R.id.tv_result);
        mTvRun = $(R.id.tv_run);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.tv_clean:
                cleanData();
                break;
            case R.id.tv_run:
                mPresenter.runRSA(this, mEtPrime1.getText().toString(), mEtPrime2.getText().toString(), mEtPublicText.getText().toString(), mEtClearText.getText().toString());
                break;
            default:
                break;
        }

    }

    /**
     * 清空数据
     */
    private void cleanData() {
        mEtPublicText.setText("");
        mEtPrime2.setText("");
        mEtPrime1.setText("");
        mEtClearText.setText("");
        mTvResult.setText("");
    }

    @Override
    public void showResult(String result) {
        mTvResult.setText(result);
    }
}
