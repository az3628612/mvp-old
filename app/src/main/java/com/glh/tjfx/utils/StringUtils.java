package com.glh.tjfx.utils;

import android.app.ActivityManager;
import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.text.Html;
import android.text.Spanned;
import android.text.TextUtils;
import android.util.Base64;
import android.widget.TextView;


import com.glh.tjfx.app.BaseApplication;

import org.json.JSONObject;

import java.security.MessageDigest;
import java.util.Collection;
import java.util.List;
import java.util.Locale;
import java.util.UUID;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符串工具
 */
public class StringUtils {

    /**
     * 获取UUID
     */
    public static String getUUID() {
        return UUID.randomUUID().toString();
    }

    /**
     * 获取版本名
     */
    public static String getAppVersion(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionName;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return "";
    }

    /**
     * 获取版本号
     */
    public static int getVersionCode(Context context) {
        try {
            PackageInfo pi = context.getPackageManager().getPackageInfo(context.getPackageName(), 0);
            return pi.versionCode;
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        return 0;
    }

    /**
     * 获取当前应用程序的包名
     * @param context 上下文对象
     * @return 返回包名
     */
    public static String getAppProcessName(Context context) {
        //当前应用pid
        int pid = android.os.Process.myPid();
        //任务管理类
        ActivityManager manager = (ActivityManager) context.getSystemService(Context.ACTIVITY_SERVICE);
        //遍历所有应用
        List<ActivityManager.RunningAppProcessInfo> infos = manager.getRunningAppProcesses();
        for (ActivityManager.RunningAppProcessInfo info : infos) {
            if (info.pid == pid)//得到当前应用
                return info.processName;//返回包名
        }
        return "";
    }


    /**
     * 判断字符串是否是json
     * */
    public static boolean isJsonFormat(String json) {
        try {
            new JSONObject(json);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 将字符串进行Base64编码
     * */
    public static String encodeString(String str) {
        return new String(Base64.encode(str.getBytes(), Base64.DEFAULT));
    }

    /**
     * 将字符串进行Base64解码
     * */
    public static String decodeString(String str) {
        byte[] bsByte = Base64.decode(str, Base64.DEFAULT);
        return new String(bsByte);
    }

    /**
     * 将列表转换成字符串
     * */
    public static String listToString(Collection<String> stringList) {
        if (stringList == null) {
            return null;
        }
        StringBuilder result = new StringBuilder();
        boolean flag = false;
        for (String string : stringList) {
            if (flag) {
                result.append(",");
            } else {
                flag = true;
            }
            result.append(string);
        }
        return result.toString();
    }

    /**
     * 判断是否电子邮箱
     */
    public static boolean checkEmail(String email) {

        Pattern pattern = Pattern
                .compile("^\\w+([-.]\\w+)*@\\w+([-]\\w+)*\\.(\\w+([-]\\w+)*\\.)*[a-z]{2,3}$");
        Matcher matcher = pattern.matcher(email);
        if (matcher.matches()) {
            return true;
        }
        return false;
    }

    /**
     * 判断是否是手机号码
     */
    public static boolean checkMobile(String mobileNo) {
        return mobileNo.matches("^1[3456789]\\d{9}$");
    }

    /**
     * 判断是否是固定电话号码
     * */
    public static boolean checkTele(String teleNo) {
        return teleNo.matches("^\\d{7}|\\d{8}|\\d{11}|\\d{12}$");
    }

    /**
     * 过滤emoji 或者 其他非文字类型的字符
     */
    public static String filterEmoji(String source) {
        // 到这里铁定包含
        StringBuilder buf = null;
        int len = source.length();
        for (int i = 0; i < len; i++) {
            char codePoint = source.charAt(i);
            if (!isEmojiCharacter(codePoint)) {
                if (buf == null) {
                    buf = new StringBuilder(source.length());
                }
                buf.append(codePoint);
            } else {
            }
        }
        if (buf == null) {
            return "";
        } else {
            if (buf.length() == len) {// 这里的意义在于尽可能少的toString，因为会重新生成字符串
                buf = null;
                return source;
            } else {
                return buf.toString();
            }
        }
    }

    public static boolean isEmojiCharacter(char codePoint) {
        return !((codePoint == 0x0) || (codePoint == 0x9) || (codePoint == 0xA) || (codePoint == 0xD)
                || ((codePoint >= 0x20) && (codePoint <= 0xD7FF)) || ((codePoint >= 0xE000) && (codePoint <= 0xFFFD))
                || ((codePoint >= 0x10000) && (codePoint <= 0x10FFFF)));
    }

    /**
     * 搜索文字高亮显示
     */
    public static void showHightLightText(TextView textView, String name, String searchString) {
        if (!TextUtils.isEmpty(searchString)) {
            int index = name.indexOf(searchString);
            if (index != -1) {
                int len = searchString.length();
                Spanned temp = Html.fromHtml(name.substring(0, index)
                        + "<font color=#5987e4>"
                        + name.substring(index, index + len) + "</font>"
                        + name.substring(index + len, name.length()));
                textView.setText(temp);
            } else {
                textView.setText(name);
            }
        }
    }

    /**
     * 检查是否只有字母、数字和汉字
     * */
    public static boolean isSpecialName(char str) {
        // 只允许字母、数字和汉字
        return String.valueOf(str).matches("[^a-zA-Z0-9\u4E00-\u9FA5]");
    }

    /**
     * 检查是否只有字母、数字、汉字、简单汉字标点符号
     * */
    public static boolean isSpecialCharIntroduce(char str) {
        // 只允许字母、数字和汉字
        return String.valueOf(str).matches("[^a-zA-Z0-9\u4E00-\u9FA5，、。！？；：”]");
    }

    /**
     * 是否是表情符号
     * */
    public static boolean isEmoji(String str) {
        Pattern pattern = Pattern.compile("#img#Face_[0-9]+[.]gif#/img#");
        Matcher matcher = pattern.matcher(str);
        while (matcher.find()) {
            return true;
        }
        return false;
    }

    /**
     *
     * */
    public static String enterToSpace(String str) {
        if (!TextUtils.isEmpty(str)) {
            String newString = str;
            Pattern CRLF = Pattern.compile("(\r\n|\r|\n|\n\r)");
            Matcher m = CRLF.matcher(str);
            if (m.find()) {
                newString = m.replaceAll(" ");
            }
            return newString;
        }
        return "";
    }

    /**
     * 以指定字符str连接arr中的字符串
     */
    public static String contactArrWithSemicolon(String[] arr, String str) {
        String result = "";
        if (arr != null && arr.length > 0) {
            StringBuffer sb = new StringBuffer();
            for (int i = 0; i < arr.length; i++) {
                sb.append(arr[i]).append(str);
            }

            if (sb.indexOf(";") != -1) {
                result = sb.substring(0, sb.length() - 1);
            }
        }
        return result;
    }

    /**
     * 对指定字符串进行md5加密
     *
     * @param s
     * @return 加密后的数据
     */
    public static String EncryptMD5(String s) {
        char hexDigits[] = {'0', '1', '2', '3', '4', '5', '6', '7', '8', '9',
                'a', 'b', 'c', 'd', 'e', 'f'};
        try {
            byte[] btInput = s.getBytes();
            // 获得MD5摘要算法的 MessageDigest 对象
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            // 使用指定的字节更新摘要
            mdInst.update(btInput);
            // 获得密文
            byte[] md = mdInst.digest();
            // 把密文转换成十六进制的字符串形式
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++) {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }


    /**
     * 根据系统语言判断是否为中国
     *
     * @return
     */
    public static boolean isZh() {
        Locale locale = BaseApplication.getInstance().getResources().getConfiguration().locale;
        String language = locale.getLanguage();
        if (language.startsWith("zh")) {
            return true;
        } else {
            return false;
        }
    }
}
