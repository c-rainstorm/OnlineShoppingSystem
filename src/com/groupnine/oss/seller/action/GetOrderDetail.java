package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getOrderDetail.action")
public class GetOrderDetail extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetOrderDetail() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();
        Order order = new Order();

        int orderId = 0;
        String id = (String) request.getParameter("orderId");
        if (!StringUtil.isEmpty(id))
            orderId = Integer.parseInt(id);

        order = service.getOrderDetail(orderId);

        Gson gson = new Gson();
        String str = gson.toJson(order);
        System.out.println(str);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(str);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
