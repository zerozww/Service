package com.lost.utils;


import java.io.File;
import java.io.FileOutputStream;

/**
 * @Author: Manitozhang
 * @Data: 2019/1/9 16:51
 * @Email: manitozhang@foxmail.com
 *
 * 文件工具类
 */
public class FileUtil {

    public static String uploadFile(byte[] file, String filePath, String fileName) throws Exception {
        File targetFile = new File(filePath);
        if(!targetFile.exists()){
            targetFile.mkdirs();
        }
        String fullPathName = filePath+"/"+fileName;
        FileOutputStream out = new FileOutputStream(fullPathName);
        out.write(file);
        out.flush();
        out.close();

        return fullPathName;
    }

}
