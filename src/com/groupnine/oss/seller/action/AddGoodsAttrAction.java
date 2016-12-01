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

@WebServlet("/addGoodsAttr.action")
public class AddGoodsAttrAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public AddGoodsAttrAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        SellerService service = new SellerServiceImpl();
        // boolean flag = false;

        int goodsId = 0;
        String gId = request.getParameter("goodsId");
        if (!StringUtil.isEmpty(gId))
            goodsId = Integer.parseInt(gId);
        // System.out.println(goodsId);

        String jsonAttrs = request.getParameter("attributes");
        // String jsonAttrs3 = (String)request.getAttribute("attributes");
        // String jsonAttrs2 =
        // (String)request.getSession().getAttribute("attributes");
        // String jsonAttrs4 = request.getParameter("attributes[]");
        // String[] jsonAttrs5 = request.getParameterValues("attributes[]");

        // System.out.println(jsonAttrs);
        // System.out.println(jsonAttrs2);
        // System.out.println(jsonAttrs3);
        // System.out.println(jsonAttrs4);
        // System.out.println(jsonAttrs5);

        JsonParser parser = new JsonParser();
        JsonArray attrArray = parser.parse(jsonAttrs).getAsJsonArray();

        // System.out.println(attrArray.size());

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
        // String goodsId = request.getParameter("goodsId");
        // if (!StringUtil.isEmpty(goodsId))
        // attr.setGoodsId(Integer.parseInt(goodsId));
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
        // attr.setCost(Double.parseDouble(price));
        //
        // response.setCharacterEncoding("UTF-8");
        //
        // Gson gson = new Gson();
        // String str;
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