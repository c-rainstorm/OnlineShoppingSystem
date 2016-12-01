package com.groupnine.oss.seller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.StatisticsData;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getSingleSales.action")
public class GetSingleSalesAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetSingleSalesAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);

        int days = 0;
        String d = request.getParameter("days");
        if (!StringUtil.isEmpty(d))
            days = Integer.parseInt(d);

        ArrayList<StatisticsData> dt = service.getSingleSales(days, goodsId);

        Gson gson = new Gson();
        String jData = gson.toJson(dt);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jData);
        System.out.println(jData);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
