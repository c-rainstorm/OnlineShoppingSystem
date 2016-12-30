package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.Goods;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/deleteGoodsFromFavorite.action")
public class DeleteGoodsFromFavoriteAction extends HttpServlet {

    public DeleteGoodsFromFavoriteAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String uid = (String) request.getSession().getAttribute("userId");
        String gid = request.getParameter("goodsId");
        // debug begin
        // uid = request.getParameter("uid");
        // debug end

        /* 2. invoke service */

        if (Goods.isGoodsIdWellFormed(gid)) {
            UserService.deleteGoodsFromFavorite(uid, gid);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(new TrueResult(), response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
