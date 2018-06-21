package com.glandeoid.livemusiclivelife.Utils;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Environment;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;

import static com.glandeoid.livemusiclivelife.Utils.StaticUtils.CACHE_SAVE;
import static com.glandeoid.livemusiclivelife.Utils.StaticUtils.CACHE_SAVEFILE_PATH;

/**
 * 类名: CacheUtils
 * 创建人: 杜强海
 * QQ:342954420
 * 创建时间:2018/6/21 0021 21:04
 * 类作用功能:文件缓存类-缓存软件参数数据
 */

public class CacheUtils {
    /**
     * 得到缓存值
     *
     * @param context 上下文
     * @param key
     * @return
     */
    public static boolean getBoolean(Context context, String key) {
        SharedPreferences sp = context.getSharedPreferences(CACHE_SAVE, Context.MODE_PRIVATE);
        return sp.getBoolean(key, false);
    }

    /**
     * 保存软件参数
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putBoolean(Context context, String key, boolean value) {
        SharedPreferences sp = context.getSharedPreferences(CACHE_SAVE, Context.MODE_PRIVATE);
        sp.edit().putBoolean(key, value).commit();
    }
    /**
     * 缓存文本数据
     *
     * @param context
     * @param key
     * @param value
     */
    public static void putString(Context context, String key, String value) {
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {

            try {
                String fileName = MD5Encoder.encode(key);

                File file = new File(Environment.getExternalStorageDirectory() + CACHE_SAVEFILE_PATH, fileName);

                File parentFile = file.getParentFile();
                if (!parentFile.exists()) {
                    //创建目录
                    parentFile.mkdirs();
                }


                if (!file.exists()) {
                    file.createNewFile();
                }
                //保存文本数据
                FileOutputStream fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(value.getBytes());
                fileOutputStream.close();

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("文本数据缓存失败");
            }
        } else {
            SharedPreferences sp = context.getSharedPreferences(CACHE_SAVE, Context.MODE_PRIVATE);
            sp.edit().putString(key, value).commit();
        }

    }

    /**
     * 获取缓存的文本信息
     *
     * @param context
     * @param key
     * @return
     */
    public static String getString(Context context, String key) {
        String result = "";
        if (Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED)) {
            try {
                String fileName = MD5Encoder.encode(key);

                File file = new File(Environment.getExternalStorageDirectory() + CACHE_SAVEFILE_PATH, fileName);

                if (file.exists()) {

                    FileInputStream is = new FileInputStream(file);
                    ByteArrayOutputStream stream = new ByteArrayOutputStream();

                    byte[] buffer = new byte[1024];
                    int length;
                    while ((length = is.read(buffer)) != -1) {
                        stream.write(buffer, 0, length);
                    }

                    is.close();
                    stream.close();

                    result = stream.toString();


                }

            } catch (Exception e) {
                e.printStackTrace();
                LogUtil.e("图片获取失败");
            }
        } else {
            SharedPreferences sp = context.getSharedPreferences(CACHE_SAVE, Context.MODE_PRIVATE);
            result = sp.getString(key, "");
        }
        return result;
    }
}
