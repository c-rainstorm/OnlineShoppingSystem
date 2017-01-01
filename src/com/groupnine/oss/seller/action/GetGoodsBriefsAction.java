package com.groupnine.oss.seller.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.Goods;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/getGoodsBriefs.action")
public class GetGoodsBriefsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public GetGoodsBriefsAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();

        ArrayList<Goods> goodsBriefs = new ArrayList<Goods>();

        HttpSession session = request.getSession(true);
        int shopId = 0;
        String sId = (String) session.getAttribute("shopId");
        if (!StringUtil.isEmpty(sId))
            shopId = Integer.parseInt(sId);

        int page = 0;
        String spage = (String) request.getParameter("page");
        if (!StringUtil.isEmpty(spage))
            page = Integer.parseInt(spage);

        goodsBriefs = service.getGoodsBriefs(shopId, page);

        Gson gson = new Gson();
        String gb = gson.toJson(goodsBriefs);
        response.setCharacterEncoding("UTF-8");
        response.getWriter().append(gb);

        // System.out.println(gb);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
