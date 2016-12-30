package com.groupnine.oss.user.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.GoodsBrief;
import com.groupnine.oss.user.entity.SearchOption;
import com.groupnine.oss.user.entity.Searches;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/getGoodsBriefByCategory.action")
public class GetGoodsBriefByCategoryAction extends HttpServlet {

    public GetGoodsBriefByCategoryAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameter */

        String l1 = request.getParameter("levelOne");
        String l2 = request.getParameter("levelTwo");
        String nPerPage = request.getParameter("maxNumInOnePage");
        String pageNum = request.getParameter("pageNum");
        String sortByPrice = request.getParameter("sortByPrice");
        String priceUp = request.getParameter("priceUp");

        /* 2. check validity, populate SearchOption */

        boolean wellFormedInput = (l1 != null) &&
                Searches.isMaxNumInOnePageWF(nPerPage) &&
                Searches.isCurrentPageWF(pageNum) &&
                Searches.isSortMethodWF(sortByPrice);

        SearchOption option = new SearchOption();
        option.setLevelOne(l1)
                .setLevelTwo(l2)
                .setnPerPage(nPerPage)
                .setPageNum(pageNum)
                .setSortByPrice(sortByPrice)
                .setPriceUp(priceUp);

        /* 3. invoke service */

        ArrayList<GoodsBrief> gs = null;
        if (wellFormedInput) {
            gs = UserService.getGoodsBriefByCategory(option);
        } else {
            // 为了返回空 JSON 数组，gs 必须指向存在对象
            gs = new ArrayList<>();
        }

        /* 4. return JSON */

        Gson gson = new Gson();
        gson.toJson(gs, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
