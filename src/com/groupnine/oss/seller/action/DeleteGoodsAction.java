package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/deleteGoods.action")
public class DeleteGoodsAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteGoodsAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);

        System.out.print(goodsId);

        SellerService service = new SellerServiceImpl();
        boolean flag = false;
        if (goodsId > 0)
            flag = service.deleteGoods(goodsId);
        //
        // Gson gson = new Gson();
        // String str;
        // /*
        // * if (flag) { response.getWriter().println("删除成功！"); } else {
        // * response.getWriter().println("删除失败！请稍后再试..."); }
        // */
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
