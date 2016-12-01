package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getShopInfo.action")
public class GetShopInfoAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetShopInfoAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        int userId = 0;
        HttpSession session = request.getSession(true);
        String uId = (String) session.getAttribute("userId");
        if (!StringUtil.isEmpty(uId))
            userId = Integer.parseInt(uId);

        Shop shop = service.getShopById(userId);

        // System.out.println(shop.getUserId());
        // System.out.println(shop.getAnnouncement());
        // System.out.println(shop.getShopDescribe());
        // System.out.println(shop.getEvaluateNumber());
        // System.out.println(shop.getEvaluateSum());
        Gson gson = new Gson();
        String jShop = gson.toJson(shop);

        // System.out.println(jShop);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jShop);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}