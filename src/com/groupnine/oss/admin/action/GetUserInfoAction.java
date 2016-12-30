package com.groupnine.oss.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.admin.entity.User;
import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;

@WebServlet("/GetUserInfo.action")
public class GetUserInfoAction extends HttpServlet {
    private static final long serialVersionUID1 = 1L;

    public GetUserInfoAction() {
        super();
    }

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phone = request.getParameter("searchuser");

        String username = request.getParameter("searchuser");

        AdminService service = new AdminServiceImpl();
        ArrayList<User> users = service.GetUserInfo(phone, username);

        Gson gson = new Gson();
        String gsonUsers = gson.toJson(users);

        response.setCharacterEncoding("utf-8");
        response.getWriter().append(gsonUsers);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
