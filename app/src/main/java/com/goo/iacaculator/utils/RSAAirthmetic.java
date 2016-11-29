package com.goo.iacaculator.utils;


import java.math.BigInteger;

public class RSAAirthmetic {

    /**
     * 判断是否为素数
     *
     * @param num
     * @return
     */
    public static boolean isPrime(int num) {
        if (num < 2) {
            return false;
        }
        for (int i = 2; i <= Math.sqrt(num); i++) {//程序默认2是素数，当j=2时，循环不执行
            if (num % i == 0) {
                return false;
            }
        }
        return true;
    }

    /**
     * 主逻辑
     *
     * @param prime1
     * @param prime2
     * @param pubKey
     * @param clearText
     * @return
     */
    public static String getResult(int prime1, int prime2, int pubKey, BigInteger clearText) {
        String result = "";
        int priKey = -1;//私钥
        if (!isPrime(prime1) || !isPrime(prime2)) {
            return result + "请保证素数A、B为素数（例：5，17）";
        }
        int baseNum = prime1 * prime2;
        int phi = (prime1 - 1) * (prime2 - 1);
        result += "BaseNum = " + prime1 + "*" + prime2 + "=" + baseNum + "\n";
        result += "Phi = (" + prime1 + "-1)*(" + prime2 + "-1)=" + phi + "\n";
        //检查公钥是否与phi互质
        if (isCoprime(pubKey, phi)) {
            result += "公钥为(" + pubKey + "," + baseNum + ")\n";
        } else {
            return result + "公钥与Phi不互质，请输入范围为(0,Phi)，与Phi互质的公钥";
        }
        result += "由(pubKey*priKey) mod phi = 1\n";
        priKey = getPriKey(pubKey, phi);
        if (priKey == -1) {
            return result + "无法找到对于私钥，算法终止\n";
        } else {
            result += "私钥为(" + priKey + "," + baseNum + ")\n";
        }
        BigInteger encodedText = rsa(baseNum, pubKey, clearText);
        result += "明文加密结果为:" + encodedText.toString() + "\n";
        result += "密文解密结果为:" + rsa(baseNum, priKey, encodedText).toString() + "\n";
        return result;
    }

    /**
     * 加密、解密算法
     *
     * @param key     公钥或密钥
     * @param message 数据
     * @return
     */
    public static BigInteger rsa(int baseNum, int key, BigInteger message) {
        if (baseNum < 1 || key < 1) {
            return BigInteger.valueOf(0L);
        }
        //加密核心算法
        return (message.pow(key)).mod(BigInteger.valueOf(baseNum));
    }

    /**
     * 判断两个数是否互质
     * 核心思想：排除特例后，求最大公因数（互质需要最大公因数为1）
     *
     * @param num1
     * @param num2
     * @return
     */
    public static boolean isCoprime(int num1, int num2) {
        if (num1 <= 0 || num2 <= 0 || num1 == num2) {
            return false;
        } else if (num1 == 1 || num2 == 1) {
            return true;
        } else {
            return maxCommonFactor(num1, num2) == 1;
        }
    }

    /**
     * 这里采用蛮力法实现（欧几里德算法有一定难度）
     *
     * @param pubKey
     * @param phi
     * @return
     */
    public static int getPriKey(int pubKey, int phi) {
        int priKey = -1;
        while (!((priKey * pubKey) % phi == 1)) {
            priKey++;
            if (priKey > 1000000) {
                //防止死循环导致ANR
                return -1;
            }
        }
        return priKey;
    }

    /**
     * 辗转相除法求最大公因数
     *
     * @param num1
     * @param num2
     * @return
     */
    private static int maxCommonFactor(int num1, int num2) {
        while (true) {
            int temp = num1 % num2;
            if (temp == 0) {
                break;
            } else {
                num1 = num2;
                num2 = temp;
            }
        }
        return num2;
    }


}  