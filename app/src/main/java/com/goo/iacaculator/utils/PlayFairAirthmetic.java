package com.goo.iacaculator.utils;

import java.util.HashSet;
import java.util.Stack;

/**
 * PlayFair算法实现类
 * Created by Goo on 2016-10-26.
 */
public class PlayFairAirthmetic {

    private static final int INPUT_RIGHT = 1;
    private static final int INPUT_ERROR = 0;

    /**
     * 格式化明文
     *
     * @param clearText 明文
     * @param sign      标识字
     * @return
     */
    public static String formatClearText(String clearText, String sign) {
        String upperText = clearText.toUpperCase();
        String resultText = "", temp = "";
        String upperSign = sign.toUpperCase();
        char c;
        //使用栈来分隔明文
        Stack<Character> stack = new Stack<Character>();
        for (int i = 0; i < upperText.length(); i++) {
            c = upperText.charAt(i);
            if (checkChar(c) == INPUT_RIGHT) {
                //若重复字母则多插一个标识字
                if (stack.size() % 2 != 0 && stack.peek() == c) {
                    stack.push(upperSign.charAt(0));
                }
                stack.push(c);
            }
        }
        while (stack.size() > 0) {
            temp += stack.pop();
        }
        for (int i = temp.length() - 1; i > -1; i--)
            resultText += temp.charAt(i);
        if (resultText.length() % 2 != 0)
            resultText += upperSign.charAt(0);
        return resultText;
    }

    /**
     * 根据密钥生成密钥矩阵
     *
     * @param k
     * @return
     */
    public static char[][] initKeyMatrix(String k) {
        char[][] key = new char[5][5];
        String resultKey = formatKey(k);
        int counter = 0;
        for (int i = 0; i < 5; i++)
            for (int j = 0; j < 5; j++) {
                key[i][j] = resultKey.charAt(counter);
                counter++;
            }
        return key;
    }

    /**
     * 加密
     *
     * @param keyMatrix         密钥矩阵
     * @param formatedClearText 已格式化的明文
     * @return
     */
    public static String encrypt(char[][] keyMatrix, String formatedClearText) {

        String encryptResult = "";
        int i = 0;
        char first, second;
        while (i < formatedClearText.length() - 1) {
            first = formatedClearText.charAt(i);
            second = formatedClearText.charAt(i + 1);
            encryptResult += getEnData(first, second, keyMatrix);
            i += 2;
        }
        return encryptResult;
    }

    /**
     * 解密
     *
     * @param keyMatrix 密钥矩阵
     * @param data      密文
     * @param sign      标识字
     * @return
     */
    public static String decrypt(char[][] keyMatrix, String data, String sign) {
        String dedata = "";
        int i = 0;
        char first, second;
        while (i < data.length()-1) {
            first = data.charAt(i);
            second = data.charAt(i + 1);
            dedata = dedata + getDeData(first, second, keyMatrix);
            i += 2;
        }
        dedata = dedata.replaceAll(sign, "");
        return dedata;
    }


    /**
     * 检查输入字符(不支持字母Z)
     *
     * @param c
     * @return
     */
    private static int checkChar(char c) {
        if (c >= 'A' && c <= 'Y')
            return INPUT_RIGHT;
        else
            return INPUT_ERROR;
    }


    /**
     * 格式化密钥
     *
     * @param key 密钥
     * @return
     */
    private static String formatKey(String key) {
        StringBuilder sb = new StringBuilder();
        HashSet<Character> hs = new HashSet<>();
        char n;
        String k = key.toUpperCase();
        char[] detail = new char[25];
        for (int i = 0, j = 65; i < 25; i++, j++)
            detail[i] = (char) j;
        for (int i = 0; i < k.length(); i++) {
            n = k.charAt(i);
            if (!hs.contains(n) && checkChar(n) == 1) {
                sb.append(n);
                hs.add(n);
            }
        }
        int i = 0;
        while (sb.length() != 25) {
            char t = detail[i];
            if (!hs.contains(t)) {
                sb.append(t);
                hs.add(t);
            }
            i++;
        }
        return sb.toString();
    }


    /**
     * 单步加密
     *
     * @param first     第一字符
     * @param second    第二字符
     * @param keyMatrix 密钥矩阵
     * @return
     */
    private static String getEnData(char first, char second, char[][] keyMatrix) {
        String result = "";
        int f1 = 0, f2 = 0, s1 = 0, s2 = 0;//记录位置
        for (int i = 0; i < keyMatrix.length; i++)
            for (int j = 0; j < keyMatrix[i].length; j++) {
                if (first == keyMatrix[i][j]) {
                    f1 = i;
                    f2 = j;
                }
                if (second == keyMatrix[i][j]) {
                    s1 = i;
                    s2 = j;
                }
            }
        if (f1 != s1 && f2 != s2)
            //两个字符相同
            result = result + keyMatrix[f1][s2] + keyMatrix[s1][f2];
        else if (f1 == s1) {
            //同行
            if (f2 == 4)
                result = result + keyMatrix[f1][0] + keyMatrix[f1][s2 + 1];
            else if (s2 == 4)
                result = result + keyMatrix[f1][f2 + 1] + keyMatrix[f1][0];
            else
                result = result + keyMatrix[f1][f2 + 1] + keyMatrix[f1][s2 + 1];
        } else {
            //同列
            if (f1 == 4)
                result = result + keyMatrix[0][f2] + keyMatrix[s1 + 1][f2];
            else if (s1 == 4)
                result = result + keyMatrix[f1 + 1][f2] + keyMatrix[0][f2];
            else
                result = result + keyMatrix[f1 + 1][f2] + keyMatrix[s1 + 1][f2];
        }
        return result;
    }

    /**
     * 单步解密
     *
     * @param first
     * @param second
     * @param keyMatrix
     * @return
     */
    private static String getDeData(char first, char second, char[][] keyMatrix) {
        String result = "";
        int f1 = 0, f2 = 0, s1 = 0, s2 = 0;
        for (int i = 0; i < keyMatrix.length; i++)
            for (int j = 0; j < keyMatrix[i].length; j++) {
                if (first == keyMatrix[i][j]) {
                    f1 = i;
                    f2 = j;
                }
                if (second == keyMatrix[i][j]) {
                    s1 = i;
                    s2 = j;
                }
            }
        if (f1 != s1 && f2 != s2)
            //位置不同
            result = result + keyMatrix[f1][s2] + keyMatrix[s1][f2];
        else if (f1 == s1) {
            //同行
            if (f2 == 0)
                result = result + keyMatrix[f1][4] + keyMatrix[f1][s2 - 1];
            else if (s2 == 0)
                result = result + keyMatrix[f1][f2 - 1] + keyMatrix[f1][4];
            else
                result = result + keyMatrix[f1][f2 - 1] + keyMatrix[f1][s2 - 1];
        } else {
            //同列
            if (f1 == 0)
                result = result + keyMatrix[4][f2] + keyMatrix[s1 - 1][f2];
            else if (s1 == 0)
                result = result + keyMatrix[f1 - 1][f2] + keyMatrix[4][f2];
            else
                result = result + keyMatrix[f1 - 1][f2] + keyMatrix[s1 - 1][f2];
        }
        return result;
    }
}
