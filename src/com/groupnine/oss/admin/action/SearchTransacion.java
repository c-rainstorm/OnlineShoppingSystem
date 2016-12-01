package com.groupnine.oss.admin.action;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;

@WebServlet("/searchTransaction.action")
public class SearchTransacion extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int transactionId = Integer.parseInt(request.getParameter("searchtran"));

        String comment = request.getParameter("searchtran");

        AdminService service = new AdminServiceImpl();
        ArrayList<Transaction> transactions = service.SearchTransaction(transactionId, comment);

        Gson gson = new Gson();
        String gsontransaction = gson.toJson(transactions);

        response.setCharacterEncoding("utf-8");
        System.out.println(gsontransaction);
        response.getWriter().append(gsontransaction);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
