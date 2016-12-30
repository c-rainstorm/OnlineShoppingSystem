package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.Receiver;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getReceivers.action")
public final class GetReceiversAction extends HttpServlet {

    public GetReceiversAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        String uid = (String) request.getSession().getAttribute("userId");

        // debug begin
        // uid = request.getParameter("uid");
        // debug end

        ArrayList<Receiver> receivers = UserService.getReceivers(uid);
        
        Gson gson = new Gson();
        gson.toJson(receivers, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
