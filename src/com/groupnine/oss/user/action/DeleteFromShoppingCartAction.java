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

@WebServlet("/deleteFromShoppingCart.action")
public class DeleteFromShoppingCartAction extends HttpServlet {

    public DeleteFromShoppingCartAction() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        boolean hasLogined = request.getSession().getAttribute("userLoginStatus").equals("true")
                ? true
                : false;
        String uid = (String) request.getSession().getAttribute("userId");
        String rid = request.getParameter("id");
        boolean wf = Goods.isRecordIdWellFormed(rid);
        // -- debug begin
        // hasLogined = wf = true;
        // uid = request.getParameter("uid");
        // -- debug end

        /* 2. invoke service.method */

        if (hasLogined && wf) {
            UserService.deleteFromShoppingCart(uid, rid);
        }

        /* 3. return JSON */

        Object result = (hasLogined && wf) ? new TrueResult() : new FalseResult();
        Gson gson = new Gson();
        gson.toJson(result, response.getWriter());

    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        doGet(request, response);
    }

}
