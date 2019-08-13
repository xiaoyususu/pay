package com.pay.gateway;

import javax.crypto.Cipher;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.Base64;
import java.util.Map;

/**
 * @ClassName RSAUtils
 * @Description RSA加解密工具类
 * @Author boy
 * @Date 2019/8/13 8:00 PM
 */
public class RSAUtils {
    /*
     * @Author boy
     * @Description 加密数据
     * @Date 2019/8/13 8:25 PM
     * @Param [data, publicKey]
     * @return java.lang.String
     */
    public static String encrypt(String data, String publicKey) throws Exception {
        //base64编码的公钥解析为二进制
        byte[] publicKeyByte = Base64.getDecoder().decode(publicKey);
        //得到公钥
        PublicKey pubKey = KeyFactory.getInstance("RSA").generatePublic(new X509EncodedKeySpec(publicKeyByte));
        //加密数据
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.ENCRYPT_MODE, pubKey);
        //得到加密后的数据
        String encryptData = Base64.getEncoder().encodeToString(cipher.doFinal(data.getBytes()));
        return encryptData;
    }

    /*
     * @Author boy
     * @Description 解密数据
     * @Date 2019/8/13 8:53 PM
     * @Param [data, privateKey]
     * @return java.lang.String
     */
    public static String decrypt(String data, String privateKey) throws Exception {
        //base64编码的私钥解析为二进制
        byte[] privateKeyByte = Base64.getDecoder().decode(privateKey);
        //base64解析后的加密数据
        byte[] dataByte = Base64.getDecoder().decode(data.getBytes());
        //获取私钥
        PrivateKey priKey = KeyFactory.getInstance("RSA").generatePrivate(new PKCS8EncodedKeySpec(privateKeyByte));
        //RSA解密
        Cipher cipher = Cipher.getInstance("RSA");
        cipher.init(Cipher.DECRYPT_MODE, priKey);
        //得到解密后的数据
        String decryptData = new String(cipher.doFinal(dataByte));
        return decryptData;
    }

    public static void main(String[] args) {
        try {
            Map<String, String> keyMap = KeyPairGenUtil.genKeyPair();
            String data = "你好！RSA！";
            System.out.println("加密前数据：" + data);
            String encryptData = encrypt(data, keyMap.get("publicKey"));
            System.out.println("加密后数据：" + encryptData);
            String decryptData = decrypt(encryptData, keyMap.get("privateKey"));
            System.out.println("解密后数据：" + decryptData);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
