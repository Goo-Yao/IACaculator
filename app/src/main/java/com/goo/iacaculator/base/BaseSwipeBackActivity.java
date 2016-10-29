
package com.goo.iacaculator.base;

import android.os.Bundle;
import android.view.View;

import com.goo.iacaculator.swipeback.SwipeBackActivityBase;
import com.goo.iacaculator.swipeback.SwipeBackActivityHelper;
import com.goo.iacaculator.swipeback.SwipeBackLayout;
import com.goo.iacaculator.swipeback.Utils;


public abstract class BaseSwipeBackActivity<V, P extends BasePresenter<V>> extends BaseActivity<V, P> implements SwipeBackActivityBase {
    private SwipeBackActivityHelper mHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mHelper = new SwipeBackActivityHelper(this);
        mHelper.onActivityCreate();
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);
        mHelper.onPostCreate();
    }

    @Override
    public View findViewById(int id) {
        View v = super.findViewById(id);
        if (v == null && mHelper != null)
            return mHelper.findViewById(id);
        return v;
    }

    @Override
    public SwipeBackLayout getSwipeBackLayout() {
        return mHelper.getSwipeBackLayout();
    }

    @Override
    public void setSwipeBackEnable(boolean enable) {
        getSwipeBackLayout().setEnableGesture(enable);
    }

    @Override
    public void scrollToFinishActivity() {
        Utils.convertActivityToTranslucent(this);
        getSwipeBackLayout().scrollToFinishActivity();
    }
}
