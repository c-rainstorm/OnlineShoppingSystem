package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.OrderBrief;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getOrderByStatus.action")
public class GetOrderByStatusAction extends HttpServlet {

    public GetOrderByStatusAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String uid = (String) request.getSession().getAttribute("userId");
        String orderStatus = request.getParameter("orderStatus");
        String nPerPage = request.getParameter("maxNumInOnePage");
        String pageNum = request.getParameter("pageNum");

        // -- debug begin
        // uid = request.getParameter("uid");
        // -- debug end

        /* 2. invoke servie */

        List<OrderBrief> orders = UserService.getOrderByStatus(uid, orderStatus, nPerPage, pageNum);

        /* 3. return JSON */

        Gson gson = new Gson();
        gson.toJson(orders, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
