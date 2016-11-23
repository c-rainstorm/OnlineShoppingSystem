package com.groupnine.oss.demo.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.demo.service.ImageUpload;

@WebServlet("/addImage.action")
@MultipartConfig
public class UploadImageAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        ImageUpload service = new ImageUpload();
        boolean success = service.addImage(request);

        if (success) {
            response.getWriter().print("success");
        } else {
            response.getWriter().print("添加失败！请稍后再试...");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
