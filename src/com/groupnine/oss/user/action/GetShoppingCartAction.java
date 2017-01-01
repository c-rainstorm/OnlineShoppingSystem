package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.ShoppingCart;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getShoppingCart.action")
public class GetShoppingCartAction extends HttpServlet {

    public GetShoppingCartAction() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read session info */

        boolean hasLogined = request.getSession().getAttribute("userLoginStatus").equals("true");
        String userId = (String) request.getSession().getAttribute("userId");
        // -- debug begin
        // hasLogined = true;
        // userId = request.getParameter("uid");
        // -- debug end

        /* 2. invoke service.method */

        // 当用户未登录时候，返回 JSON 为空数组，而不是 null
        // 所以此处要先使 sc 指向对象
        ArrayList<ShoppingCart> sc = new ArrayList<>();

        if (hasLogined) {
            sc = UserService.getShoppingCart(userId);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(sc, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
