package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getGoodsInfo.action")
public class GetGoodsInfoAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetGoodsInfoAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);
        // System.out.println(gId);
        // System.out.println(goodsId);
        Goods goods = service.getGoodsById(goodsId);

        // goods.setGoodsImages(service.getImages(goodsId)); // 获得图片集合
        goods.setGoodsImagesUrl(service.getImagesUrl(goodsId));// 获得图片地址
        goods.setGoodsAttrs(service.getAttrs(goodsId));

        Gson gson = new Gson();
        String jGoods = gson.toJson(goods);

        // System.out.println("商品" + jGoods);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(jGoods);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
