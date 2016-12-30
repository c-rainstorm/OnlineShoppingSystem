package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.TrueResult;

@WebServlet("/userLogout.action")
public class UserLogoutAction extends HttpServlet {

    public UserLogoutAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. 调整 session */

        request.getSession().setAttribute("userLoginStatus", "setAddr");

        /* 2. return JSON */

        Gson gson = new Gson();
        gson.toJson(new TrueResult(), response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
