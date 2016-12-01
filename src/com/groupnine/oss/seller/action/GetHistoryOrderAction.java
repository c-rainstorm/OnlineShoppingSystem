package com.groupnine.oss.seller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getHistoryOrder.action")
public class GetHistoryOrderAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetHistoryOrderAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();
        ArrayList<Order> orders = new ArrayList<Order>();

        HttpSession session = request.getSession(true);
        int shopId = 0;
        String sId = (String) session.getAttribute("shopId");
        if (!StringUtil.isEmpty(sId))
            shopId = Integer.parseInt(sId);

        int page = 0;
        String spage = (String) request.getParameter("page");
        if (!StringUtil.isEmpty(spage))
            page = Integer.parseInt(spage);

        orders = service.getHistoryOrder(shopId, page);

        Gson gson = new Gson();
        String hOrder = gson.toJson(orders);
        // System.out.println(hOrder);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(hOrder);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
