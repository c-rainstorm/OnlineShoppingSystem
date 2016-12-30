package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.GoodsDetail;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getGoodsDetail.action")
public class GetGoodsDetailAction extends HttpServlet {

    public GetGoodsDetailAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String goodsId = request.getParameter("goodsId");

        /* 2. invoke service.method */
        GoodsDetail detail = null;
        if (goodsId != null) {
            detail = UserService.getGoodsDetail(goodsId);
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
