package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.OrderDetail;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getOrderById.action")
public class GetOrderByIdAction extends HttpServlet {

    public GetOrderByIdAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String oid = request.getParameter("orderId");

        /* 2. invoke service */

        OrderDetail detail = null;
        if (oid != null) {
            detail = UserService.getOrderById(oid);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(detail, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
