package com.groupnine.oss.demo.service;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.Part;

import org.apache.commons.io.FileUtils;

public class ImageUpload {

    public boolean addImage(HttpServletRequest request) {
        boolean success = false;

        Part image = null;
        try {
            image = request.getPart("image");
        } catch (IOException e2) {
            e2.printStackTrace();
        } catch (ServletException e2) {
            e2.printStackTrace();
        }

        InputStream imageInputStream = null;

        if (image != null && image.getSize() != 0) {
            try {
                imageInputStream = image.getInputStream();

                if (imageInputStream != null) {
                    String imagedir = request.getServletContext().getInitParameter("imagedir")
                            + "/avatars/";

                    // 图片名格式：20161123204206613375.jpg。
                    // 代表 2016-11-23 20:42:06.613 + 3 位 0 - 9 间随机数字
                    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyyMMddHHmmssSSS");
                    StringBuilder imageName = new StringBuilder(dateFormat.format(new Date()));
                    Random random = new Random();
                    for (int i = 0; i < 3; ++i) {
                        imageName.append(random.nextInt(10));
                    }
                    imageName.append(".jpg");

                    String targetFile = imagedir + imageName;
                    try {
                        FileUtils.copyInputStreamToFile(imageInputStream, new File(targetFile));

                        success = true;
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } catch (IOException e1) {
                e1.printStackTrace();
            }
        }

        return success;
    }

}
