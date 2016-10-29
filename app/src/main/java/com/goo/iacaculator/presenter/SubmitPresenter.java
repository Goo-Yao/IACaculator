package com.goo.iacaculator.presenter;

import com.goo.iacaculator.base.BasePresenter;
import com.goo.iacaculator.view.activity.SubmitActivity;
import com.goo.iacaculator.view.vinterface.SubmitVInterface;

/**
 * Created by Goo on 2016-10-26.
 */

public class SubmitPresenter extends BasePresenter<SubmitVInterface> {
    public SubmitPresenter(SubmitActivity viewInterface) {
        super(viewInterface);
    }
}
