package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
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

@WebServlet("/deleteGoodsAttr.action")
public class DeleteGoodsAttrAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public DeleteGoodsAttrAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int attributeId = 0;
        String attrId = request.getParameter("attributeId");
        if (!StringUtil.isEmpty(attrId))
            attributeId = Integer.parseInt(attrId);

        SellerService service = new SellerServiceImpl();
        boolean flag = false;
        if (attributeId > 0)
            flag = service.deleteGoodsAttr(attributeId);

        Gson gson = new Gson();
        String str;

        /*
         * if (flag) { response.getWriter().println("修改成功！"); } else {
         * response.getWriter().println("修改失败！请稍后再试..."); }
         */

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
