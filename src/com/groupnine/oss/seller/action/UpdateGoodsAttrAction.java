package com.groupnine.oss.seller.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.groupnine.oss.seller.entity.GoodsAttr;
import com.groupnine.oss.seller.service.SellerService;
import com.groupnine.oss.seller.service.SellerServiceImpl;
import com.groupnine.oss.util.StringUtil;

@WebServlet("/updateGoodsAttr.action")
public class UpdateGoodsAttrAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public UpdateGoodsAttrAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();
        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);

        service.deleteGoodsAttrs(goodsId);

        String jsonAttrs = request.getParameter("attributes");
        JsonParser parser = new JsonParser();
        JsonArray attrArray = parser.parse(jsonAttrs).getAsJsonArray();

        for (int i = 0; i < attrArray.size(); i++) {
            GoodsAttr attr = new GoodsAttr();
            JsonObject jattr = attrArray.get(i).getAsJsonObject();
            int cost = jattr.get("cost").getAsInt();
            int price = jattr.get("price").getAsInt();
            int inventory = jattr.get("inventory").getAsInt();
            String attributeValue = jattr.get("value").getAsString();

            // System.out.println(cost);
            // System.out.println(price);
            // System.out.println(inventory);
            // System.out.println(attributeValue);
            //
            // System.out.println(jattr.toString());

            attr.setGoodsId(goodsId);
            attr.setAttributeValue(attributeValue);
            attr.setCost(cost);
            attr.setPrice(price);
            attr.setInventory(inventory);

            service.addGoodsAttr(attr);
        }
        // GoodsAttr attr = new GoodsAttr();
        // {
        // attr.setAttributeValue(request.getParameter("attributeValue"));
        //
        // String attributeId = request.getParameter("attributeId");
        // if (!StringUtil.isEmpty(attributeId))
        // attr.setAttributeId(Integer.parseInt(attributeId));
        //
        // String inventory = request.getParameter("inventory");
        // if (!StringUtil.isEmpty(inventory))
        // attr.setInventory(Integer.parseInt(inventory));
        //
        // String cost = request.getParameter("cost");
        // if (!StringUtil.isEmpty(cost))
        // attr.setCost(Double.parseDouble(cost));
        //
        // String price = request.getParameter("price");
        // if (!StringUtil.isEmpty(price))
        // attr.setPrice(Double.parseDouble(price));
        // }
        //
        // SellerService service = new SellerServiceImpl();
        // boolean flag = false;
        // flag = service.updateGoodsAttr(attr);

        /*
         * if (flag) { response.getWriter().println("修改成功！"); } else {
         * response.getWriter().println("修改失败！请稍后再试..."); }
         */
        //
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
