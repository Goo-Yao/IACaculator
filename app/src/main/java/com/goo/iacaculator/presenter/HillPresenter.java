package com.goo.iacaculator.presenter;

import com.goo.iacaculator.base.BasePresenter;
import com.goo.iacaculator.utils.HillAirthmetic;
import com.goo.iacaculator.view.vinterface.HillVInterface;

/**
 * Created by Goo on 2016-10-29.
 */

public class HillPresenter extends BasePresenter<HillVInterface> {
    public HillPresenter(HillVInterface viewInterface) {
        super(viewInterface);
    }

    public void runHill(String clearText) {
        String result = "默认密钥矩阵:\n" + "17, 17, 5\n" + "21, 18, 21\n" + "2, 2, 19\n";
        result += "加密结果:" + HillAirthmetic.encryptAll(clearText);
        view.showResult(result);
    }
}
