package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.pub.entity.Order;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/sendGoods.action")
public class SendGoodsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public SendGoodsAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Order order = new Order();

        String orderId = request.getParameter("orderId");
        if (!StringUtil.isEmpty(orderId))
            order.setOrderId(Integer.parseInt(orderId));

        // order.setOrderStatus(request.getParameter("orderStatus"));
        order.setTrackingNumber(request.getParameter("trackingNumber"));

        boolean flag = false;
        SellerService service = new SellerServiceImpl();

        // String status = "待发货";
        // if (order.getOrderStatus().equals(status)) {
        flag = service.sendGoods(order);
        // }

        /*
         * if (flag) { response.getWriter().println("运单号填写成功！"); } else {
         * response.getWriter().println("当前不能发货。"); }
         */

        // Gson gson = new Gson();
        // String str;
        //
        // if (flag) {
        // str = gson.toJson(new TrueResult());
        // } else {
        // str = gson.toJson(new FalseResult());
        // }
        // response.getWriter().append(str);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
