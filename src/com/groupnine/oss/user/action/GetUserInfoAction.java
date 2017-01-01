package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.UserFullInfo;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getUserInfo.action")
public final class GetUserInfoAction extends HttpServlet {

    public GetUserInfoAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String uid = (String) request.getSession().getAttribute("userId");
        // debug begin
        // uid = request.getParameter("uid");
        // debug end

        UserFullInfo info = UserService.getUserInfo(uid);

        Gson gson = new Gson();
        gson.toJson(info, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
