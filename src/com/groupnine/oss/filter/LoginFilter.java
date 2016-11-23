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
        if (session.getAttribute("userLoginStatus") == null)
            session.setAttribute("userLoginStatus", "setAttr");
        if (session.getAttribute("adminLoginStatus") == null)
            session.setAttribute("adminLoginStatus", "setAttr");
        // TODO: 修改过滤器
        String requestUrl = request2.getRequestURL().toString();

        // 若请求用户后台界面
        if (requestUrl.indexOf("profiles/user.jsp") > 0) {
            String userLoginStatus = (String) session.getAttribute("userLoginStatus");
            // 如果用户未登录
            if (!userLoginStatus.equals("true")) {
                response2.sendRedirect(request2.getContextPath() + "/pages/signin/user.jsp");
            }

            // 若请求商家后台页面
        } else if (requestUrl.indexOf("profiles/seller_behind.jsp") > 0) {
            String sellerLoginStatus = (String) session.getAttribute("sellerLoginStatus");

            // 如果商家未登录
            if (!sellerLoginStatus.equals("true")) {
                response2.sendRedirect(request2.getContextPath() + "/pages/signin/seller.jsp");
            }

            // 若请求管理员后台页面
        } else if (requestUrl.indexOf("profiles/admin.jsp") > 0) {
            String adminLoginStatus = (String) session.getAttribute("adminLoginStatus");

            // 如果管理员未登录
            if (!adminLoginStatus.equals("true")) {
                response2.sendRedirect(request2.getContextPath() + "/pages/signin/admin.jsp");
            }
        }
        chain.doFilter(request, response);
    }

    public void init(FilterConfig fConfig) throws ServletException {
    }

}
