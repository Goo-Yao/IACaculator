package com.goo.iacaculator.view.activity;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.DecelerateInterpolator;

import com.goo.iacaculator.R;
import com.goo.iacaculator.base.BaseActivity;
import com.goo.iacaculator.listener.HidingScrollListener;
import com.goo.iacaculator.presenter.MainPresenter;
import com.goo.iacaculator.utils.DimenUtils;
import com.goo.iacaculator.utils.ToastUtil;
import com.goo.iacaculator.view.adapter.RVMainAdapter;
import com.goo.iacaculator.view.vinterface.MainVInterface;

public class MainActivity extends BaseActivity<MainVInterface, MainPresenter> implements View.OnClickListener, MainVInterface {

    private RecyclerView mRvMain;
    private FloatingActionButton mFABSetting;

    @Override
    protected MainPresenter createPresenter() {
        return new MainPresenter(this);
    }

    @Override
    protected int setContentViewById() {
        return R.layout.activity_main;
    }

    @Override
    protected void initAttributes() {
        isDoubleBackDestory = true;
    }

    @Override
    protected void initView() {
        findAllViewById();
        initRv();
        mFABSetting.setOnClickListener(this);
    }

    /**
     * 加载RecyclerView
     */
    private void initRv() {
        RVMainAdapter rvAdapter = mPresenter.getRVAdapter(this);
        mRvMain.setLayoutManager(new LinearLayoutManager(this));
        mRvMain.setAdapter(rvAdapter);
        mRvMain.addOnScrollListener(new HidingScrollListener() {
            @Override
            public void onHide() {
                hideFAB();
            }

            @Override
            public void onShow() {
                showFAB();
            }
        });
        ItemTouchHelper itemHelper = mPresenter.getItemTouchHelper(rvAdapter);
        itemHelper.attachToRecyclerView(mRvMain);
    }

    /**
     * 显示悬浮按钮
     */
    private void showFAB() {
        mFABSetting.animate().translationY(0).setInterpolator(new DecelerateInterpolator(1)).start();
    }

    /**
     * 隐藏悬浮按钮
     */
    private void hideFAB() {
        ConstraintLayout.LayoutParams lp = (ConstraintLayout.LayoutParams) mFABSetting.getLayoutParams();
        int fabBottomMargin = lp.bottomMargin;
        mFABSetting.animate().translationY(mFABSetting.getHeight() + fabBottomMargin + DimenUtils.getNavBarHeight(this) + DimenUtils.getStatusBarHeight(this)).
                setInterpolator(new AccelerateInterpolator(2)).start();
    }


    @Override
    protected void findAllViewById() {
        mRvMain = $(R.id.rv_main);
        mFABSetting = $(R.id.fab_submit);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.fab_submit:
                Intent intent = new Intent(MainActivity.this, SubmitActivity.class);
                startActivityWithAnim(intent);
                break;
        }
    }

    @Override
    public void startActivityWithAnim(String arithmeticName) {
        if (arithmeticName.equals("PlayFair")) {
            startActivityWithAnim(new Intent(MainActivity.this, PlayFairActivity.class));
        } else if (arithmeticName.equals("Hill")) {
            startActivityWithAnim(new Intent(MainActivity.this, HillActivity.class));
        } else {
            ToastUtil.showToast(this, "尚未实现,敬请期待");
        }
    }
}