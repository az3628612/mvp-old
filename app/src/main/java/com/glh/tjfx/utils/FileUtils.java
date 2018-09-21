package com.glh.tjfx.utils;

import android.graphics.Bitmap;
import android.os.Environment;
import android.text.TextUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.Date;

/**
 * 文件操作工具类
 */
public class FileUtils {

    public static void checkFilePath(String path) {
        File file = new File(path);
        if (!file.exists() && !file.isDirectory()) {
            file.mkdirs();
        }
    }

    /**
     * 检测SD卡是否存在
     *
     * @return true or false
     */
    public static boolean isExistSdcard() {
        boolean sdCardExist = Environment.getExternalStorageState().equals(Environment.MEDIA_MOUNTED); // 判断sd卡是否存在
        if (sdCardExist) {
            return true;
        }
        return false;
    }

    /**
     * 创建文件
     *
     * @param folderPath
     * @param fileName
     * @return
     */
    public static File createFile(String folderPath, String fileName) {
        File destDir = new File(folderPath);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return new File(folderPath, fileName);
    }

    /**
     * 创建文件
     * @param fileName 文件全路径
     * @return
     */
    public static File createFile(String fileName) {
        File file = null;
        try {
            file = new File(fileName);
            if (!file.exists()) {
                file.createNewFile();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return file;
    }

    /**
     * 创建目录
     *
     * @param path
     * @return
     */
    public static File createDir(String path) {
        File destDir = new File(path);
        if (!destDir.exists()) {
            destDir.mkdirs();
        }
        return destDir;
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    public static boolean deleteDir(String dir) {
        File file = new File(dir);
        if (!file.exists()) {
            return true;
        }
        return deleteDir(file);
    }

    /**
     * 递归删除目录下的所有文件及子目录下所有文件
     *
     * @param dir 将要删除的文件目录
     * @return boolean Returns "true" if all deletions were successful. If a
     * deletion fails, the method stops attempting to delete and returns
     * "false".
     */
    public static boolean deleteDir(File dir) {
        if (dir.isDirectory()) {
            String[] children = dir.list();
            // 递归删除目录中的子目录下
            for (int i = 0; i < children.length; i++) {
                boolean success = deleteDir(new File(dir, children[i]));
                if (!success) {
                    return false;
                }
            }
        }
        // 目录此时为空，可以删除
        return dir.delete();
    }

    /**
     * 获取文件大小显示的字符串
     *
     * @param fileSize
     * @return
     */
    public static String getFileSizeStr(long fileSize) {
        String result = "";
        DecimalFormat df = new DecimalFormat("#.#");
        if (fileSize < 1024) {
            result = df.format((double) fileSize) + "B";
        } else if (fileSize < 1048576) {
            result = df.format((double) fileSize / 1024) + "KB";
        } else if (fileSize < 1073741824) {
            result = df.format((double) fileSize / 1048576) + "MB";
        } else {
            result = df.format((double) fileSize / 1073741824) + "GB";
        }
        if (result.indexOf(".") > 0) {
            result = result.replaceAll("0+?$", "");//去掉后面无用的零
            result = result.replaceAll("[.]$", "");//如小数点后面全是零则去掉小数点
        }
        return result;
    }

    public static File createOutputDCIMFile() {
        String timestamp = DateUtils.dateToStr(new Date(System.currentTimeMillis()),
                DateUtils.sYMD_HMSFormat.get());
        String fileName = "IMG_" + timestamp + ".png";
        return createFile(FileAccessor.getUserAccessPath(FileAccessor.DCIM_PATH), fileName);
    }

    public static File createOutputCropFile() {
        String timestamp = DateUtils.dateToStr(new Date(System.currentTimeMillis()),
                DateUtils.sYMD_HMSFormat.get());
        String fileName = "IMG_" + timestamp + ".png";
        return createFile(FileAccessor.getUserAccessPath(FileAccessor.CROP_PATH), fileName);
    }

    public static String getFileName(String path) {
        return path.substring(path.lastIndexOf('/') + 1, path.length());
    }

    /**
     * 是否是图片地址
     * @param url
     * @return
     */
    public static boolean isImagePath(String url) {
        if(url == null || TextUtils.equals("",url)){
            return false;
        }
        return (url.endsWith(".jpg") || url.endsWith(".jpeg") || url.endsWith(".png") || url.endsWith(".bmp") || url.endsWith(".gif"));
    }

    /** 复制文件，可以选择是否删除源文件 */
    public static boolean copyFile(File srcFile, File destFile,
                                   boolean deleteSrc) {
        if (!srcFile.exists() || !srcFile.isFile()) {
            return false;
        }
        InputStream in = null;
        OutputStream out = null;
        try {
            in = new FileInputStream(srcFile);
            out = new FileOutputStream(destFile);
            byte[] buffer = new byte[1024];
            int i = -1;
            while ((i = in.read(buffer)) > 0) {
                out.write(buffer, 0, i);
                out.flush();
            }
            if (deleteSrc) {
                srcFile.delete();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        } finally {
            if(out!=null){
                try {
                    out.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if(in !=null){
                try {
                    in.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
        return true;
    }

    /**
     * 保存Bitmap到sdk
     * @param photoBitmap
     * @param path
     */
    public static void saveBitMapToSDCard(Bitmap photoBitmap, String path) {
        if (FileUtils.isExistSdcard()) {
            File photoFile = new File(path);
            File dir = photoFile.getParentFile();
            if (!dir.exists())
                dir.mkdirs();
            FileOutputStream fileOutputStream = null;
            try {
                fileOutputStream = new FileOutputStream(photoFile);
                if (photoBitmap != null) {
                    if (photoBitmap.compress(Bitmap.CompressFormat.PNG, 0, fileOutputStream)) {
                        fileOutputStream.flush();
                    }
                }
            } catch (Exception e) {
                photoFile.delete();
                e.printStackTrace();
            } finally {
                if(fileOutputStream != null){
                    try {
                        fileOutputStream.close();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }


    /**
     * 返回自定文件或文件夹的大小
     *
     * @param f
     * @return
     * @throws Exception
     */
    public static long getFileSizes(File f) throws Exception {// 取得文件大小
        long s = 0;
        if (f.exists()) {
            FileInputStream fis = new FileInputStream(f);
            s = fis.available();
            fis.close();
        } else {
            f.createNewFile();
            System.out.println("文件不存在");
        }
        return s;
    }

    /**
     * 递归取得文件夹大小
     * */
    public static long getFileSize(File f) throws Exception
    {
        long size = 0;
        File flist[] = f.listFiles();
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getFileSize(flist[i]);
            } else {
                size = size + flist[i].length();
            }
        }
        return size;
    }

    /**
     * 转换文件大小
     * */
    public static String FormetFileSize(long fileS) {
        DecimalFormat df = new DecimalFormat("#0.00");
        String fileSizeString = "";
        if (fileS < 1024) {
            fileSizeString = df.format((double) fileS) + "B";
        } else if (fileS < 1048576) {
            fileSizeString = df.format((double) fileS / 1024) + "K";
        } else if (fileS < 1073741824) {
            fileSizeString = df.format((double) fileS / 1048576) + "M";
        } else {
            fileSizeString = df.format((double) fileS / 1073741824) + "G";
        }
        return fileSizeString;
    }

    /**
     * 递归求取目录文件个数
     * */
    public static long getlist(File f) {
        long size = 0;
        File flist[] = f.listFiles();
        size = flist.length;
        for (int i = 0; i < flist.length; i++) {
            if (flist[i].isDirectory()) {
                size = size + getlist(flist[i]);
                size--;
            }
        }
        return size;
    }
}
