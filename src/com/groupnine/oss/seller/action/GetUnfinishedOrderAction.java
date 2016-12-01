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

@WebServlet("/getUnfinishedOrder.action")
public class GetUnfinishedOrderAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetUnfinishedOrderAction() {
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

        orders = service.getUnfinishedOrder(shopId, page);

        Gson gson = new Gson();
        String uOrder = gson.toJson(orders);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(uOrder);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
