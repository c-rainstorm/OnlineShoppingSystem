package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.FalseResult;
import com.groupnine.oss.user.entity.GoodsItemInSC;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/addToShoppingCart.action")
public class AddToShoppingCartAction extends HttpServlet {

    public AddToShoppingCartAction() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        boolean hasLogined = request.getSession().getAttribute("userLoginStatus").equals("true")
                ? true
                : false;
        boolean wf = true;
        String uid = (String) request.getSession().getAttribute("userId");
        // -- debug begin
        // hasLogined = true;
        // uid = request.getParameter("uid");
        // -- debug end

        String goodsId = request.getParameter("goodsId");
        String attrId = request.getParameter("attributeId");
        String goodNum = request.getParameter("goodsNum");
        GoodsItemInSC g = new GoodsItemInSC();
        try {
            g.setGoodsId(goodsId);
            g.setAttributeId(attrId);
            g.setGoodsNum(goodNum);
        } catch (Exception e) {
            wf = false;
        }

        /* 2. invoke service.method */

        if (hasLogined && wf)
            UserService.addGoodsToShoppingCat(uid, g);

        /* 3. return JSON */

        Object result = hasLogined && wf ? new TrueResult() : new FalseResult();
        Gson gson = new Gson();
        gson.toJson(result, response.getWriter());
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
