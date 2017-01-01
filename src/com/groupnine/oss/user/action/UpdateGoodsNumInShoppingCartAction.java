package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.FalseResult;
import com.groupnine.oss.user.entity.Goods;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/updateGoodsNumInShoppingCart.action")
public class UpdateGoodsNumInShoppingCartAction extends HttpServlet {

    public UpdateGoodsNumInShoppingCartAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        boolean hasLogined = request.getSession().getAttribute("userLoginStatus").equals("true");
        String uid = (String) request.getSession().getAttribute("userId");
        String rid = request.getParameter("id");
        String goodNum = request.getParameter("goodsNum");
        // -- debug begin
        // hasLogined = true;
        // uid = request.getParameter("uid");
        // -- debug end

        /* 2. invoke service.method */

        boolean wf = Goods.isRecordIdWellFormed(rid) &&
                Goods.isGoodsNumWellFormed(goodNum);

        boolean updated = false;
        if (hasLogined && wf) {
            updated = UserService.updateGoodsNumInShoppingCat(uid, rid, goodNum);
        }

        /* 3. return JSON */

        Gson gson = new Gson();
        Object result = updated ? new TrueResult() : new FalseResult();
        gson.toJson(result, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
