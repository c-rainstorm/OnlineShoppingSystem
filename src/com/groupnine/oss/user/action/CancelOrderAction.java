package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/cancelOrder.action")
public class CancelOrderAction extends HttpServlet {

    public CancelOrderAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String oid = request.getParameter("orderId");
        String newStat = "已取消";

        /* 2. invoke service */

        UserService.updateOrderStatus(oid, newStat);

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(new TrueResult(), response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
