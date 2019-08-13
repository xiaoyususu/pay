package com.pay.gateway;

import java.security.*;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;

/**
 * @ClassName RSAUtils
 * @Description 生成密钥对
 * @Author boy
 * @Date 2019/8/13 5:08 PM
 */
public class KeyPairGenUtil {
    public static int KEY_LENGTH = 1024;//密钥大小
    public static String ALGORITHM_TYPE = "RSA";//算法类型
    /*
     * @Author boy
     * @Description 生成密钥对
     * @Date 2019/8/13 8:01 PM
     * @Param []
     * @return void
     */
    public static Map genKeyPair() throws NoSuchAlgorithmException {
        Map<String, String> keyMap = new HashMap<>();//存储公钥和私钥
        //为RSA算法创建KeyPairGenerator对象
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(ALGORITHM_TYPE);
        //创建RSA算法可信任的随机数源
        SecureRandom secureRandom = new SecureRandom();
        //使用随机数源初始化keyPairGenerator对象
        keyPairGenerator.initialize(KEY_LENGTH, secureRandom);
        //生成密钥对
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        //获取私钥
        PrivateKey privateKey = keyPair.getPrivate();
        //获取公钥
        PublicKey publicKey = keyPair.getPublic();
        //使用base64将私钥和公钥转化为字符串
        String privateKeyStr = Base64.getEncoder().encodeToString(privateKey.getEncoded());
        String publicKeyStr = Base64.getEncoder().encodeToString(publicKey.getEncoded());
        keyMap.put("privateKey", privateKeyStr);
        keyMap.put("publicKey", publicKeyStr);
        return keyMap;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> keyMap = genKeyPair();
            System.out.println("私钥为：" + keyMap.get("privateKey"));
            System.out.println("公钥为：" + keyMap.get("publicKey"));
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
    }
}
