package com.pay.gateway.aes;

import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;

/**
 * @ClassName AESUtils
 * @Description TODO
 * @Author boy
 * @Date 2019/8/21 8:52 PM
 */
public class AESUtils {
    public static String AES = "AES";//指定算法类型
    public static int KEY_LEN = 128;//指定密钥长度
    public static String UTF_8 = "UTF-8";//编码格式

    /*
     * @Author boy
     * @Description 生成AES密钥
     * @Date 2019/8/22 10:49 AM
     * @Param []
     * @return java.lang.String
     */
    public static String genAESKey() throws Exception{
        //构造密钥生成器，指定为AES算法
        KeyGenerator keyGenerator = KeyGenerator.getInstance(AES);
        //生成一个指定位数的随机源，KEY_LEN=128就是128位
        keyGenerator.init(KEY_LEN);
        //生成对称密钥
        SecretKey sKey = keyGenerator.generateKey();
        return Base64.getEncoder().encodeToString(sKey.getEncoded());
    }

    /*
     * @Author boy
     * @Description 使用AES密钥解密数据
     * @Date 2019/8/22 10:51 AM
     * @Param [key, data]
     * @return java.lang.String
     */
    public static String encrypt(String key,String data) throws Exception{
        //获取key
        SecretKey secretKey = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes()),AES);
        //根据指定算法自成密码器
        Cipher cipher=Cipher.getInstance(AES);
        //初始化密码器，第一个参数为加密或者解密解密操作，第二个参数为使用的KEY
        cipher.init(Cipher.ENCRYPT_MODE, secretKey);
        //将加密内容转化为字节数组
        byte [] byte_data=data.getBytes(UTF_8);
        //将字节数组加密
        byte [] AES_data=cipher.doFinal(byte_data);
        return new String(new BASE64Encoder().encode(AES_data));
    }
    public static String decrypt(String key,String data) throws Exception{
        //获取key
        SecretKeySpec secretKey = new SecretKeySpec(Base64.getDecoder().decode(key.getBytes()),AES);
        //根据指定算法自成密码器
        Cipher cipher=Cipher.getInstance(AES);
        //初始化密码器，第一个参数为加密或者解密解密操作，第二个参数为使用的KEY
        cipher.init(Cipher.DECRYPT_MODE, secretKey);
        //将加密内容转化为字节数组，因为数据是用Base64转换过的，所以需要使用base64解密
        byte [] dataByte = Base64.getDecoder().decode(data.getBytes(UTF_8));
        //解密字节数组
        byte [] decryptData=cipher.doFinal(dataByte);
        return new String(decryptData);
    }

    /*
     * @Author boy
     * @Description 使用AES密钥加密数据
     * @Date 2019/8/22 10:51 AM
     * @Param [args]
     * @return void
     */
    public static void main(String[] args) throws Exception{
        String data = "你好！AES";
        String key = genAESKey();
        System.out.println("密钥："+key);
        System.out.println("加密前数据："+data);
        String AESData = encrypt(key,data);
        System.out.println("加密后数据：" + AESData);
        System.out.println("解密后数据：" + decrypt(key,AESData));
    }
}
