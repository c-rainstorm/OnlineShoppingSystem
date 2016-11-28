package com.groupnine.oss.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public final class LoginFilter implements Filter {

    public void destroy() {
    }

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request2 = (HttpServletRequest) request;
        HttpServletResponse response2 = (HttpServletResponse) response;

        HttpSession session = request2.getSession();
        if (session.getAttribute("userLoginStatus") == null) {
            session.setAttribute("userLoginStatus", "false");
            session.setAttribute("shopHasOpend", "false");
            session.setAttribute("userId", "..");
            session.setAttribute("nickname", "..");
            session.setAttribute("shopId", "..");
        }
        if (session.getAttribute("adminLoginStatus") == null) {
            session.setAttribute("adminLoginStatus", "false");
            session.setAttribute("adminId", "..");
        }
        // String requestUrl = request2.getRequestURL().toString();
        //
        // if (requestUrl.indexOf("home/user.jsp") > 0) {
        // // 若请求用户后台界面
        // String userLoginStatus = (String)
        // session.getAttribute("userLoginStatus");
        // if (!userLoginStatus.equals("true")) {
        // // 如果用户未登录
        // response2.sendRedirect(request2.getContextPath() +
        // "/pages/login/user.jsp");
        // }
        // } else if (requestUrl.indexOf("home/seller.jsp") > 0) {
        // // 若请求商家后台页面
        // String userLoginStatus = (String)
        // session.getAttribute("userLoginStatus");
        // if (!userLoginStatus.equals("true")) {
        // // 如果用户未登录
        // response2.sendRedirect(request2.getContextPath() +
        // "/pages/login/user.jsp");
        // } else {
        // String shopHasOpend = (String) session.getAttribute("shopHasOpend");
        // if (!shopHasOpend.equals("true")) {
        // // 如果未开店
        // response2.sendRedirect(request2.getContextPath() +
        // "/pages/home/user.jsp");
        // }
        // }
        //
        // } else if (requestUrl.indexOf("home/admin.jsp") > 0) {
        // // 若请求管理员后台页面
        // String adminLoginStatus = (String)
        // session.getAttribute("adminLoginStatus");
        // if (!adminLoginStatus.equals("true")) {
        // // 如果管理员未登录
        // response2.sendRedirect(request2.getContextPath() +
        // "/pages/login/admin.jsp");
        // }
        // }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
