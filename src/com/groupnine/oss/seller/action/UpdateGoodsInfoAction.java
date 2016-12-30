package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@MultipartConfig
@WebServlet("/updateGoodsInfo.action")
public class UpdateGoodsInfoAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateGoodsInfoAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Goods goods = new Goods();

        String dr = request.getParameter("discountRate2");
        if (!StringUtil.isEmpty(dr))
            goods.setDiscountRate(Double.parseDouble(dr));

        String gId = request.getParameter("goodsId2");
        if (!StringUtil.isEmpty(gId))
            goods.setGoodsId(Integer.parseInt(gId));
        int goodsId = Integer.parseInt(gId);

        goods.setGoodsName(request.getParameter("goodsName2"));
        goods.setGoodsDescribe(request.getParameter("goodsDescribe2"));

        System.out.println("gId" + goodsId);
        // System.out.println(goods.getGoodsName());
        String dd = request.getParameter("discountDeadline2");
        System.out.println("dd" + dd);
        // Timestamp ts = new Timestamp(System.currentTimeMillis());
        // String tsStr = dd + " 00:00:00";
        // try {
        // ts = Timestamp.valueOf(tsStr);
        // System.out.println(ts);
        // } catch (Exception e) {
        // e.printStackTrace();
        // }
        // if (!StringUtil.isEmpty(dd))
        // goods.setDiscountDeadline(ts);
        // goods.setDiscountDeadline(dd);

        SellerService service = new SellerServiceImpl();
        boolean flag = false;
        flag = service.updateGoodsInfo(goods);
        service.addGoodsImage(request, goodsId);

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
