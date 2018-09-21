
package com.glh.tjfx.utils;

import android.content.Context;
import android.os.Environment;


import com.glh.tjfx.R;
import com.glh.tjfx.app.BaseApplication;

import java.io.File;
import java.util.HashMap;
import java.util.Map;


/**
 * 创建当前用户所需目录
 * */
public class FileAccessor {

    public static final String BASE_FILE_PATH = getExternalStorePath();
    public static final String DCIM_PATH = "DCIM";// 用户拍照保存目录
    public static final String CROP_PATH = "crop";// 裁切图片目录
    public static final String VOICE_PATH = "voice"; // 语音目录
    public static final String VIDEO_PATH = "video"; // 视频目录
    public static final String DOWNLOAD_FILE_PATH = "download";  // 下载文件目录
    public static final String LOG_PATH = "log";//日志
    public static final String CONFIG_FILE = "config";// 配置文件
    public static final String COMMON_CACHE_PATH = "fileCache";  // 文件缓存目录
    public static final String FRESCO_CACHE_PATH = "FrescoCache";// 图片缓存目录

    private static final Map<String, String> USER_PATH = new HashMap<String, String>();

    /**初始化用户需要使用的本地目录*/
    public static void initUserFileAccess(String user, Context context) {
        String baseUserDir = BASE_FILE_PATH + File.separator + StringUtils.getAppProcessName(context)+
                File.separator + user + File.separator;
        USER_PATH.put(DCIM_PATH, baseUserDir + DCIM_PATH);
        USER_PATH.put(CROP_PATH, baseUserDir + CROP_PATH);
        USER_PATH.put(VOICE_PATH, baseUserDir + VOICE_PATH);
        USER_PATH.put(VIDEO_PATH, baseUserDir + VIDEO_PATH);
        USER_PATH.put(DOWNLOAD_FILE_PATH, baseUserDir + DOWNLOAD_FILE_PATH);
        USER_PATH.put(LOG_PATH, baseUserDir + LOG_PATH);
        USER_PATH.put(CONFIG_FILE, baseUserDir + CONFIG_FILE);
        USER_PATH.put(COMMON_CACHE_PATH, baseUserDir + COMMON_CACHE_PATH);
        USER_PATH.put(FRESCO_CACHE_PATH, baseUserDir + FRESCO_CACHE_PATH);
        createUserPath();
    }
    
    public static String getUserAccessPath(String key) {
        return USER_PATH.get(key);
    }

    /**
     * 获取存储卡的路径
     *
     * @return
     */
    public static String getExternalStorePath() {
        if (FileUtils.isExistSdcard()) {
            return System.getenv(Environment.MEDIA_MOUNTED);// 外部存储卡路径
        } else {
            return Environment.getExternalStorageDirectory().getAbsolutePath();// 内置存储卡
        }
    }

    /**
     * 创建当前登录用户所需目录
     * */
    public static void createUserPath() {
        FileUtils.createDir(USER_PATH.get(DCIM_PATH));
        FileUtils.createDir(USER_PATH.get(CROP_PATH));
        FileUtils.createDir(USER_PATH.get(VOICE_PATH));
        FileUtils.createDir(USER_PATH.get(VIDEO_PATH));
        FileUtils.createDir(USER_PATH.get(DOWNLOAD_FILE_PATH));
        FileUtils.createDir(USER_PATH.get(LOG_PATH));
        FileUtils.createDir(USER_PATH.get(CONFIG_FILE));
        FileUtils.createDir(USER_PATH.get(COMMON_CACHE_PATH));
        FileUtils.createDir(USER_PATH.get(FRESCO_CACHE_PATH));
    }

    /**
     * 删除当前登录用户所需目录
     * */
    public static void deleteUserPath() {
        FileUtils.deleteDir(USER_PATH.get(DCIM_PATH));
        FileUtils.deleteDir(USER_PATH.get(CROP_PATH));
        FileUtils.deleteDir(USER_PATH.get(VOICE_PATH));
        FileUtils.deleteDir(USER_PATH.get(VIDEO_PATH));
        FileUtils.deleteDir(USER_PATH.get(DOWNLOAD_FILE_PATH));
        FileUtils.deleteDir(USER_PATH.get(LOG_PATH));
        FileUtils.deleteDir(USER_PATH.get(CONFIG_FILE));
        FileUtils.deleteDir(USER_PATH.get(COMMON_CACHE_PATH));
        FileUtils.deleteDir(USER_PATH.get(FRESCO_CACHE_PATH));
        FileUtils.deleteDir(USER_PATH.get(BASE_FILE_PATH));
    }


}
