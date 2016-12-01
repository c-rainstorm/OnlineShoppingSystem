package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupnine.oss.seller.entity.Shop;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/updateShopInfo.action")
public class UpdateShopInfoAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateShopInfoAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Shop shop = new Shop();

        int shopId = 0;
        HttpSession session = request.getSession(true);
        String sId = (String) session.getAttribute("shopId");
        if (!StringUtil.isEmpty(sId))
            shopId = Integer.parseInt(sId);
        shop.setShopId(shopId);

        shop.setAddress(request.getParameter("address2"));
        shop.setPhone(request.getParameter("phone2"));
        shop.setShopName(request.getParameter("shopName2"));
        shop.setShopDescribe(request.getParameter("shopDescribe2"));
        shop.setAnnouncement(request.getParameter("announcement2"));

        SellerService service = new SellerServiceImpl();
        boolean flag = service.updateShopInfo(shop);

        System.out.println(shop.getShopId());
        System.out.println(shop.getAnnouncement());
        System.out.println(shop.getShopDescribe());

        // Gson gson = new Gson();
        // String str;
        //
        // if (flag) {
        // str = gson.toJson(new TrueResult());
        // } else {
        // str = gson.toJson(new FalseResult());
        // }
        response.sendRedirect("http://localhost:8080/OSS/pages/home/sellerBehind.jsp");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
