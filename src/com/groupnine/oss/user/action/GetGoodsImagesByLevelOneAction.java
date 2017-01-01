package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.GoodsBrief;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getGoodsImagesByLevelOne.action")
public class GetGoodsImagesByLevelOneAction extends HttpServlet {

    public GetGoodsImagesByLevelOneAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameter */

        String levelOne = request.getParameter("levelOne");
        String imageNum = request.getParameter("imageNum");

        /* 2. invoke service */

        ArrayList<GoodsBrief> gs = null;
        if (levelOne != null && imageNum != null) {
            gs = UserService.getGoodsImageByL1(levelOne, imageNum);
        } else {
            gs = new ArrayList<>();
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(gs, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
