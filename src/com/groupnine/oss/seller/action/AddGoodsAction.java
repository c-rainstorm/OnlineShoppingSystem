package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/addGoods.action")
@MultipartConfig
public class AddGoodsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddGoodsAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Goods goods = new Goods();

        goods.setGoodsName(request.getParameter("goodsName"));
        goods.setGoodsDescribe(request.getParameter("goodsDescribe"));
        // System.out.println(goods.getGoodsName());
        // System.out.println(goods.getGoodsDescribe());

        int shopId = 0;
        HttpSession session = request.getSession(true);
        String sId = (String) session.getAttribute("shopId");
        if (!StringUtil.isEmpty(sId))
            shopId = Integer.parseInt(sId);
        goods.setShopId(shopId);

        // goods.setFirstCategory(request.getParameter("firstCategory"));
        // goods.setSecondCategory(request.getParameter("secondCategory"));
        goods.setFirstCategory("电器");
        goods.setSecondCategory("手机");

        SellerService service = new SellerServiceImpl();
        int goodsId = service.addNewGoods(goods);       // 添加新商品

        System.out.println(goodsId);

        service.addGoodsImage(request, goodsId);// 为新商品上传图片

        String gId = String.valueOf(goodsId);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(gId);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
