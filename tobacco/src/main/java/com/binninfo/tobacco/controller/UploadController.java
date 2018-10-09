package com.binninfo.tobacco.controller;


import com.binninfo.tobacco.util.ImageUpload;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.*;

@Controller
public class UploadController {

    @Value("${uploadFilePath}")
    private String uploadFilePath;
    @Value("${uploadPicturesPath}")
    private String uploadPicturesPath;

    /**
     * 上传文件
     */
    @RequestMapping(value = "/uploadFile.do", method = RequestMethod.POST)
    @ResponseBody
    public String upload(@RequestParam("file") MultipartFile multipartFile)
            throws Exception {

        String name = multipartFile.getOriginalFilename();
        File f = new File(uploadFilePath);
        if(null==f||!f.exists()){
            f.mkdir();
        }
        String filename = uploadFilePath + name;
        InputStream is = multipartFile.getInputStream();
        OutputStream os = new FileOutputStream(filename);
        byte s[] = new byte[1024];
        while (is.read(s) != -1) {
            os.write(s);
        }
        os.flush();
        os.close();
        is.close();
        return name;
    }

    @RequestMapping(value = "/uploadPic.do", method = RequestMethod.POST)
    @ResponseBody
    public String uploadPic(@RequestParam("pict") MultipartFile multipartFile)
            throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileUUID = uuid.toString().replace("-","");
        String name = multipartFile.getOriginalFilename();
        String pattern = name.substring(name.indexOf(".") + 1);
        Map map = new HashMap();
        map.put("png", "png");
        map.put("jpg", "jpg");
        map.put("gif", "gif");
        map.put("jpeg", "jpeg");

        if(map.containsKey(pattern)){
            String filename = uploadPicturesPath + fileUUID+"."+pattern;
            name = fileUUID+"."+pattern;
            InputStream is = multipartFile.getInputStream();
            OutputStream os = new FileOutputStream(filename);
            byte s[] = new byte[1024];
            while (is.read(s) != -1) {
                os.write(s);
            }
            os.flush();
            os.close();
            is.close();
            return name;
        }else{
            return "0";
        }

    }

    @RequestMapping(value = "/uploadVido.do", method = RequestMethod.POST)
    @ResponseBody
    public String uploadVido(@RequestParam("pict") MultipartFile multipartFile)
            throws Exception {
        UUID uuid = UUID.randomUUID();
        String fileUUID = uuid.toString().replace("-","");
        String name = multipartFile.getOriginalFilename();
        String pattern = name.substring(name.indexOf(".") + 1);
        Map map = new HashMap();
        map.put("mp4", "mp4");


        if(map.containsKey(pattern)){
            String filename = uploadPicturesPath + fileUUID+"."+pattern;
            name = fileUUID+"."+pattern;
            InputStream is = multipartFile.getInputStream();
            OutputStream os = new FileOutputStream(filename);
            byte s[] = new byte[1024];
            while (is.read(s) != -1) {
                os.write(s);
            }
            os.flush();
            os.close();
            is.close();
            return name;
        }else{
            return "0";
        }

    }

    /**
     * 上传图片的方法 对于上传图片的格式进行限制
     *
     * @param multipartFile
     * @return
     * @throws Exception
     */
    @RequestMapping(value = "/uploadLimitPic.do", method = RequestMethod.POST)
    @ResponseBody
    public String uploadLimitPic(
            @RequestParam("pict") MultipartFile multipartFile) {
        // 定义上传的图片师傅符合规定
        String filename = "1";
        try {
            UUID uuid = UUID.randomUUID();
            String fileName = multipartFile.getOriginalFilename();
            String name = uuid + "-"+multipartFile.getOriginalFilename();
            // 限制上传图片的样式
            String pattern = name.substring(name.indexOf(".") + 1);
            Map map = new HashMap();
            map.put("png", "png");
            map.put("jpg", "jpg");
            map.put("gif", "gif");
            map.put("jpeg", "jpeg");
            if (map.containsKey(pattern)) {
                // 这个就是表示这个可以上传的图片类型
                filename = uploadPicturesPath + name;
                InputStream is = multipartFile.getInputStream();
                OutputStream os = new FileOutputStream(filename);
                byte s[] = new byte[1024];
                while (is.read(s) != -1) {
                    os.write(s);
                }
                os.flush();
                os.close();

                is.close();

                return fileName+"/"+name;
            }
        } catch (Exception e) {
            // TODO: handle exception
            e.printStackTrace();
            filename = "2";
        }
        return filename;
    }

    /**
     * 将图片在界面上显示出来
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping(value = { "/showimage.do" })
    public String showimage(HttpServletRequest request,
                            HttpServletResponse response) throws Exception {
        // 这个方法就是以输出流的方式将图片在界面显示出来
        String name = request.getParameter("name");
        ImageUpload.showimage(uploadPicturesPath,name, response);
        return null;
    }
}
