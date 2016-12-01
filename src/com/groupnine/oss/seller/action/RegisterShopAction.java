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

@WebServlet("/registerShop.action")
public class RegisterShopAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public RegisterShopAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Shop shop = new Shop();
        shop.setAddress(request.getParameter("address"));
        shop.setPhone(request.getParameter("phone"));
        shop.setShopName(request.getParameter("shopName"));
        shop.setShopDescribe(request.getParameter("shopDescribe"));

        HttpSession session = request.getSession();
        int userId = 0;
        String uId = (String) session.getAttribute("userId");
        if (!StringUtil.isEmpty(uId))
            userId = Integer.parseInt(uId);
        shop.setUserId(userId);

        SellerService service = new SellerServiceImpl();
        boolean flag = service.registerShop(shop);

        session.setAttribute("shopHasOpend", "reviewing");
        response.sendRedirect("http://localhost:8080/OSS/pages/home/sellerSignup.jsp");

        System.out.println(uId);
        System.out.println(flag);
        System.out.println(session.getAttribute("shopHasOpend"));

        //
        // Gson gson = new Gson();
        // String str;
        //
        // if (flag) {
        // session.setAttribute("shopHasOpend", "reviewing");
        // } else {
        // str = gson.toJson(new FalseResult());
        // response.getWriter().append(str);
        // }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
