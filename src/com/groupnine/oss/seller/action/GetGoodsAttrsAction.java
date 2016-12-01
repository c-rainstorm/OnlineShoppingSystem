package com.groupnine.oss.seller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.GoodsAttr;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getGoodsAttrs.action")
public class GetGoodsAttrsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetGoodsAttrsAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);

        ArrayList<GoodsAttr> goodsAttrs = service.getAttrs(goodsId);

        Gson gson = new Gson();
        String ga = gson.toJson(goodsAttrs);

        // System.out.println("属性" + ga);

        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(ga);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
