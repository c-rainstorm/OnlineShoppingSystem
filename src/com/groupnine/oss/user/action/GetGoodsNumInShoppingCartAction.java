package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getGoodsNumInShoppingCart.action")
public class GetGoodsNumInShoppingCartAction extends HttpServlet {

    public GetGoodsNumInShoppingCartAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameter */

        String loginStatus = (String) request.getSession().getAttribute("userLoginStatus");
        String uid = (String) request.getSession().getAttribute("userId");

        /* 2. invoke service.method */

        String n = "0";
        if (loginStatus.equals("true")) {
            n = UserService.getGoodsNumInSC(uid);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        JsonObject result = new JsonObject();
        result.addProperty("goodsNum", n);
        gson.toJson(result, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
