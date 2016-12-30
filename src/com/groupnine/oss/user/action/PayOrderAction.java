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
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/payOrder.action")
public class PayOrderAction extends HttpServlet {

    public PayOrderAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String ordersJsonStr = request.getParameter("orderId");

        /* 2. invoke service */

        Gson gson = new Gson();
        Type t = new TypeToken<List<String>>() {
        }.getType();
        List<String> orders = gson.fromJson(ordersJsonStr, t);
        int totalOrdersNum = orders.size();
        int successOrdersNum = UserService.payOrder(orders);

        /* 3. return JSON */

        JsonObject result = new JsonObject();
        result.addProperty("success", new Integer(successOrdersNum).toString());
        result.addProperty("fail", new Integer(totalOrdersNum - successOrdersNum).toString());
        gson.toJson(result, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
