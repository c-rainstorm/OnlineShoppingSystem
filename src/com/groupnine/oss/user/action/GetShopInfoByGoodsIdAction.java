package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.ShopBrief;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getShopInfoByGoodsId.action")
public class GetShopInfoByGoodsIdAction extends HttpServlet {

    public GetShopInfoByGoodsIdAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String goodsId = request.getParameter("goodsId");

        /* 2. invoke service.method */
        ShopBrief shopInfo = null;
        if (goodsId != null) {
            shopInfo = UserService.getShopBriefInfoByGoodsId(goodsId);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(shopInfo, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
