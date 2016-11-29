package com.goo.iacaculator.presenter;

import android.content.Context;

import com.goo.iacaculator.base.BasePresenter;
import com.goo.iacaculator.utils.RSAAirthmetic;
import com.goo.iacaculator.utils.ToastUtil;
import com.goo.iacaculator.view.vinterface.RSAVInterface;

import java.math.BigInteger;

import static com.goo.iacaculator.utils.RSAAirthmetic.getResult;

/**
 * Created by Goo on 2016-11-29.
 */

public class RSAPresenter extends BasePresenter<RSAVInterface> {
    public RSAPresenter(RSAVInterface viewInterface) {
        super(viewInterface);
    }

    public void runRSA(Context context, String prime1, String prime2, String pubKey, String clearText) {
        if (prime1.isEmpty() || prime2.isEmpty() || pubKey.isEmpty() || clearText.isEmpty()) {
            ToastUtil.showToast(context, "请完整填写数据");
        } else {
            view.showResult(RSAAirthmetic.getResult(Integer.parseInt(prime1), Integer.parseInt(prime2), Integer.parseInt(pubKey), BigInteger.valueOf(Long.parseLong(clearText))));
        }

    }
}
