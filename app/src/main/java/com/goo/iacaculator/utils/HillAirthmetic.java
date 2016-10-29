package com.goo.iacaculator.utils;

/**
 * Hill加密算法
 * Created by Goo on 2016-10-29.
 */
public class HillAirthmetic {
    public static int[][] key = {{17, 17, 5}, {21, 18, 21}, {2, 2, 19}};

    public static String encryptAll(String clearText) {
        String result = "";
        int groups = clearText.length() / 3;//组数
        int tail = clearText.length() % 3;//尾巴

        for (int i = 0; i < groups; i++) {
            result += encrypt3(clearText.substring(i*3,  (i+1)*3).toUpperCase());
        }
        if (tail != 0) {
            switch (tail) {
                case 1:
                    result += encrypt3(clearText.substring(clearText.length() - 1, clearText.length()).toUpperCase() + "XX");
                    break;
                case 2:
                    result += encrypt3(clearText.substring(clearText.length() - 2, clearText.length()).toUpperCase() + "X");
                    break;
            }
        }
        return result;
    }

    /**
     * 加密
     *
     * @param clearText
     * @return
     */
    public static String encrypt3(String clearText) {
        int temp1 = 0, temp2, temp3;
        StringBuilder cipherText = new StringBuilder();
        temp1 = key[0][0] * (clearText.charAt(0) - 'A')
                + key[0][1] * (clearText.charAt(1) - 'A')
                + key[0][2] * (clearText.charAt(2) - 'A');
        temp2 = key[1][0] * (clearText.charAt(0) - 'A')
                + key[1][1] * (clearText.charAt(1) - 'A')
                + key[1][2] * (clearText.charAt(2) - 'A');
        temp3 = key[2][0] * (clearText.charAt(0) - 'A')
                + key[2][1] * (clearText.charAt(1) - 'A')
                + key[2][2] * (clearText.charAt(2) - 'A');
        cipherText.append((char) ('A' + temp1 % 26));
        cipherText.append((char) ('A' + temp2 % 26));
        cipherText.append((char) ('A' + temp3 % 26));
        return cipherText.toString();
    }

}
