package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.FalseResult;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.entity.Users;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/checkPhone.action")
public class CheckPhoneAction extends HttpServlet {

    public CheckPhoneAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String phone = request.getParameter("phone");

        /* 2. invoke service.method */

        boolean phoneAvil = Users.isPhoneWellFormed(phone) &&
                UserService.checkPhoneAvil(phone);

        /* 3. return JSON */

        Object result = phoneAvil ? new TrueResult() : new FalseResult();
        Gson gson = new Gson();
        gson.toJson(result, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
