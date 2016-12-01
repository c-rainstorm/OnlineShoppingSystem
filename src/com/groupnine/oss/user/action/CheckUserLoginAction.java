package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.groupnine.oss.pub.entity.MalformedException;
import com.groupnine.oss.user.entity.UserLoginInfo;
import com.groupnine.oss.user.entity.UserSessionInfo;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/checkUserLogin.action")
public class CheckUserLoginAction extends HttpServlet {

    public CheckUserLoginAction() {
        super();
    }

    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. 读取登录信息 */

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        boolean malformedInput = false;
        UserLoginInfo userLoginInfo = null;
        try {
            userLoginInfo = new UserLoginInfo(username, phone, password);
        } catch (MalformedException e) {
            malformedInput = true;
        }

        /* 2. 若登录信息格式无误，尝试登录 */

        if (!malformedInput && UserService.userLogin(userLoginInfo)) {

            /* 2.1 若登录成功，调整 session，重定向 */

            HttpSession httpSession = request.getSession();
            UserSessionInfo session = UserService.getUserSession(userLoginInfo);
            httpSession.setAttribute("userLoginStatus", "true");
            httpSession.setAttribute("userId", session.getUserId());
            httpSession.setAttribute("nickname", session.getNickname());
            if (session.getAvatarAddr() != null)
                httpSession.setAttribute("userAvatarAddr", session.getAvatarAddr());
            httpSession.setAttribute("shopHasOpend", session.isShopHasOpend());
            if (!session.isShopHasOpend().equals("false"))
                httpSession.setAttribute("shopId", session.getShopId());
            response.sendRedirect(request.getContextPath() + "/index.jsp");
        }

        /* 3. 若格式有误 或 登录失败，调整 session，重定向登录页面 */

        else {
            request.getSession().setAttribute("userLoginStatus", "false");
            response.sendRedirect(request.getContextPath() + "/pages/login/user.jsp");
        }
    }

    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
