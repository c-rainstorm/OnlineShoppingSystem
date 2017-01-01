package com.groupnine.oss.admin.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupnine.oss.admin.entity.Admin;
import com.groupnine.oss.admin.service.AdminService;
import com.groupnine.oss.admin.service.AdminServiceImpl;

@WebServlet("/AdminLogin.action")
public class AdminLoginAction extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        Admin admin = new Admin();
        admin.setAdminId(Integer.parseInt(request.getParameter("form-AdminId")));
        admin.setPassword(request.getParameter("form-password"));

        AdminService service = new AdminServiceImpl();
        boolean success = service.checkLoginInfo(admin);

        HttpSession session = request.getSession();
        if (success) {

            session.setAttribute("adminLoginStatus", "true");
            session.setAttribute("adminid", admin.getAdminId());

            response.sendRedirect(request.getContextPath() + "/pages/home/admin.jsp");
        } else {
            session.setAttribute("adminLoginStatus", "false");

            String url = request.getHeader("referer");
            if (url.lastIndexOf('?') > 0)
                url = url.substring(0, url.lastIndexOf('?'));

            response.sendRedirect(url);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
