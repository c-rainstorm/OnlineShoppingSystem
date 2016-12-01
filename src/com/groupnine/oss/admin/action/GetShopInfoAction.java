package com.groupnine.oss.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;
import com.groupnine.oss.seller.entity.Shop;

@WebServlet("/GetShopInfo.action")
public class GetShopInfoAction extends HttpServlet {
    private static final long serialVersionUID1 = 1L;

    public GetShopInfoAction() {
        super();
    }

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String phone = request.getParameter("searchshop");

        String shop_name = request.getParameter("searchshop");

        AdminService service = new AdminServiceImpl();
        ArrayList<Shop> shops = service.GetShopInfo(phone, shop_name);

        Gson gson = new Gson();
        String gsonShops = gson.toJson(shops);

        response.setCharacterEncoding("utf-8");
        response.getWriter().append(gsonShops);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
