package com.groupnine.oss.user.action;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.groupnine.oss.user.entity.SCOrder;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/confirmOrder.action")
public class ConfirmOrderAction extends HttpServlet {

    public ConfirmOrderAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String uid = (String) request.getSession().getAttribute("userId");
        String ordersToConfirmStr = request.getParameter("orders");
        String rid = request.getParameter("receiverId");
        String payMethod = request.getParameter("payMethod");

        // -- debug begin
        // uid = "2";
        // rid = "21";
        // payMethod = "在线支付";
        // ordersToConfirmStr = "[{\"shopId\":\"30\",
        // \"id\":[\"127\",\"220\"]},"
        // + "{\"shopId\":\"2\", \"id\":[\"18\"]}]";
        // -- debug end

        /* 2. invoke service */

        Gson gson = new Gson();
        Type t = new TypeToken<List<SCOrder>>() {
        }.getType();
        List<SCOrder> ordersToConfirm = gson.fromJson(ordersToConfirmStr, t);

        List<String> orders = UserService.confirmOrders(uid, ordersToConfirm, rid, payMethod);

        /* 3. return JSON */

        gson.toJson(orders, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
