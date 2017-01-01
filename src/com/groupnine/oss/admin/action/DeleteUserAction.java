package com.groupnine.oss.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;

@WebServlet("/DeleteUser.action")
public class DeleteUserAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int uid = Integer.parseInt(request.getParameter("deleteuser"));

        AdminService service = new AdminServiceImpl();
        boolean flag = false;

        flag = service.DeleteUser(uid);

        if (flag) {
            response.getWriter().println("删除成功！");
        } else {
            response.getWriter().println("删除失败！请稍后再试...");
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
