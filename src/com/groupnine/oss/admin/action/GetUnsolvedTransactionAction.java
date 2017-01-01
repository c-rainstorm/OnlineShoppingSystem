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

@WebServlet("/GetUnsolvedTransaction.action")
public class GetUnsolvedTransactionAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        AdminService service = new AdminServiceImpl();

        ArrayList<Transaction> todotransactions = service.GetUnsolvedTransaction();

        Gson gson = new Gson();
        String gsontodotransactions = gson.toJson(todotransactions);

        response.setCharacterEncoding("utf-8");
        System.out.println(gsontodotransactions);
        response.getWriter().append(gsontodotransactions);

        // response.getWriter().println(gsontodotransactions);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
