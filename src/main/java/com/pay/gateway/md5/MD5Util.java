//package com.pay.gateway.md5;
//
///**
// * @ClassName MD5Util
// * @Description TODO
// * @Author boy
// * @Date 2019/8/30 8:50 PM
// */
//
//import java.security.MessageDigest;
//
//public class MD5Util {
//    /**
//     * 生成md5
//     *
//     * @param message
//     * @return
//     */
//    public static String getMD5(String message) {
//        String md5str = "";
//        try {
//            // 1 创建一个提供信息摘要算法的对象，初始化为md5算法对象
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            // 2 将消息变成byte数组
//            byte[] input = message.getBytes();
//            // 3 计算后获得字节数组,这就是那128位了
//            byte[] buff = md.digest(input);
//            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
//            md5str = Hex.encodeHexStr(buff);
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return md5str;
//    }
//    public static String VerifyMD5(String Result, String newInput) {
//        String md5str = "";
//        try {
//            // 1，初始化为md5算法对象
//            MessageDigest md = MessageDigest.getInstance("MD5");
//            // 2 将消息变成byte数组
//            byte[] input = newInput.getBytes();
//            // 3 计算后获得字节数组,这就是那128位了
//            byte[] buff = md.digest(input);
//            // 4 把数组每一字节（一个字节占八位）换成16进制连成md5字符串
//            md5str = Hex.encodeHexStr(buff);
//            if (md5str.equals(Result)) {
//                return "验证密码通过！";
//            } else {
//                return "验证密码失败！";
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return "验证失败！";
//    }
//
//    public static void main(String[] args)
//            throws Exception {
//        String str = "sunjun123";
//        String md5str = "781E5E245D69B566979B86E28D23F2C7";
//        System.out.println(getMD5(str));
//        String result = getMD5(str);
//        System.out.println(result);
//        // System.out.println(VerifyMD5(md5str,str));
//    }
//}