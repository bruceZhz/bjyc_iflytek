package com.binninfo.tobacco.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.UUID;

public class ImageUpload {

    //显示图片
    public static void showimage(String path,String name,HttpServletResponse response) throws Exception{
        String filename=path+name;
        //创建输入流
        InputStream is=new FileInputStream(filename);
        //关联目标文件
        OutputStream os=response.getOutputStream();
        byte []s=new byte[1024];
        while((is.read(s))!=-1){
            os.write(s);
        }
        os.flush();
        os.close();

        is.close();

    }
    //删除图片
    public static void imagedelete(String path,String name){
        File file=new File(path+name);
        if(file.exists()&&file.isFile()){
            file.delete();
        }
    }
}
