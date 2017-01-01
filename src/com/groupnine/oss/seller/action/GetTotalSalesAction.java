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
import com.groupnine.oss.seller.entity.StatisticsData;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getTotalSales.action")
public class GetTotalSalesAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetTotalSalesAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        int days = 0;
        String d = request.getParameter("days");
        if (!StringUtil.isEmpty(d))
            days = Integer.parseInt(d);

        HttpSession session = request.getSession(true);
        int shopId = 0;
        String sId = (String) session.getAttribute("shopId");
        if (!StringUtil.isEmpty(sId))
            shopId = Integer.parseInt(sId);

        ArrayList<StatisticsData> dt = service.getTotalSales(days, shopId);

        Gson gson = new Gson();
        String jData = gson.toJson(dt);

        System.out.println(jData);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jData);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
