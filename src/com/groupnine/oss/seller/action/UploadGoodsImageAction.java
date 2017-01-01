package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.seller.entity.FalseResult;
import com.groupnine.oss.seller.entity.TrueResult;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/uploadGoodsImage.action")
@MultipartConfig
public class UploadGoodsImageAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UploadGoodsImageAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        SellerService service = new SellerServiceImpl();

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);

        boolean flag = service.addGoodsImage(request, goodsId);// 为商品上传图片
        /*
         * if (flag) { response.getWriter().println("上传成功！"); } else {
         * response.getWriter().println("上传失败！"); }
         */

        Gson gson = new Gson();
        String str;

        if (flag) {
            str = gson.toJson(new TrueResult());
        } else {
            str = gson.toJson(new FalseResult());
        }
        response.getWriter().append(str);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
