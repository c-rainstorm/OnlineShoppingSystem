package com.groupnine.oss.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.admin.entity.Transaction;
import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;

@WebServlet("/SolveTransaction.action")
@MultipartConfig
public class SolveTransactionAction extends HttpServlet {

    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Transaction transaction = new Transaction();

        transaction.setTransactionId(Integer.parseInt(request.getParameter("transaction_id")));

        transaction.setAdminId(Integer.parseInt(request.getParameter("admin_id")));
        transaction.setTransactionStatus(request.getParameter("transactionstatus"));
        transaction.setAnnotation(request.getParameter("annotation"));

        AdminService service = new AdminServiceImpl();
        boolean flag = service.updatetransation(transaction);

        if (flag) {
            response.getWriter().println("处理完成！");
        } else {
            response.getWriter().println("处理失败！请稍后再试...");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
