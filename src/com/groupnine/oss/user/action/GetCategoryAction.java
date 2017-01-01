package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.Category;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getCategory.action")
public class GetCategoryAction extends HttpServlet {

    public GetCategoryAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. invoke service */
        System.out.println("entered");
        ArrayList<Category> cs = UserService.getCategories();

        /* 2. return JSON */

        Gson gson = new Gson();
        gson.toJson(cs, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }
}
