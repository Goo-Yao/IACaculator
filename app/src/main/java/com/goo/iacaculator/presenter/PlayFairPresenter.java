package com.goo.iacaculator.presenter;

import android.content.Context;

import com.goo.iacaculator.base.BasePresenter;
import com.goo.iacaculator.utils.PlayFairAirthmetic;
import com.goo.iacaculator.utils.ToastUtil;
import com.goo.iacaculator.view.vinterface.PlayFairVInterface;

/**
 * Created by Goo on 2016-10-26.
 */

public class PlayFairPresenter extends BasePresenter<PlayFairVInterface> {

    String formatedClearText = null;
    char[][] keyMatrix;
    String encryptResult;
    String outPut = "";

    public PlayFairPresenter(PlayFairVInterface viewInterface) {
        super(viewInterface);
    }

    /**
     * 运行PlayFair算法
     *
     * @param clearText
     * @param key
     * @param sgin
     */
    public void runPlayFair(Context context, String clearText, String key, String sgin) {
        outPut = "";
        if (clearText.isEmpty() || key.isEmpty() || sgin.isEmpty()) {
            ToastUtil.showToast(context, "请完整填写数据");
        } else {
            formatedClearText = PlayFairAirthmetic.formatClearText(clearText, sgin);
            keyMatrix = PlayFairAirthmetic.initKeyMatrix(key);
            encryptResult = PlayFairAirthmetic.encrypt(keyMatrix, formatedClearText);
            outPut += "明文格式化:" + formatedClearText + "\n" + "密钥矩阵:\n" + printKeyMatrix(keyMatrix) + "加密后:" + encryptResult + "\n解密后:" + PlayFairAirthmetic.decrypt(keyMatrix, encryptResult, sgin) + "\n";
            view.showResult(outPut);
        }
    }

    /**
     * 打印密钥矩阵
     */
    private String printKeyMatrix(char[][] keyMatrix) {
        String result = "";
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                result += keyMatrix[i][j] + "   ";
                if (j == 4)
                    result += "\n";
            }
        return result;
    }
}
