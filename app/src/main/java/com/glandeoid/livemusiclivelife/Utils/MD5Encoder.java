package com.glandeoid.livemusiclivelife.Utils;

import java.security.MessageDigest;
/**
 * 类名: MD5Encoder
 * 创建人: 杜强海
 * QQ:342954420
 * 创建时间:2018/6/21 0021 21:06
 * 类作用功能:MD5加密类
 */

public class MD5Encoder{
        public static String encode(String string) throws Exception {
            byte[] hash = MessageDigest.getInstance("MD5").digest(string.getBytes("UTF-8"));
            StringBuilder hex = new StringBuilder(hash.length * 2);
            for (byte b : hash) {
                if ((b & 0xFF) < 0x10) {
                    hex.append("0");
                }
                hex.append(Integer.toHexString(b & 0xFF));
            }
            return hex.toString();
        }
}
