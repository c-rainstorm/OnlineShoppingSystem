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
import com.groupnine.oss.user.entity.Users;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/addNewUser.action")
public class AddNewUserAction extends HttpServlet {

    public AddNewUserAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String username = request.getParameter("username");
        String phone = request.getParameter("phone");
        String password = request.getParameter("password");

        boolean wellFormedInput = Users.isUsernameWellFormed(username) &&
                Users.isPhoneWellFormed(phone) &&
                Users.isPasswordWellFormed(password);

        /* 2. 若登录信息格式无误，尝试登录 */

        if (wellFormedInput) {
            UserLoginInfo userLoginInfo = null;
            try {
                userLoginInfo = new UserLoginInfo(username, phone, password);
            } catch (MalformedException e) {
                // Omit this exception: we have checked the parameters already.
            }
            boolean newUserAvil = UserService.checkUsernameAvail(username) &&
                    UserService.checkPhoneAvil(phone);
            boolean addUserSuccess = false;
            if (newUserAvil) {
                addUserSuccess = UserService.addNewUser(userLoginInfo);
            }

            /* 3. 若注册成功，调整 session，重定向网站首页 */

            if (addUserSuccess) {
                HttpSession httpSession = request.getSession();
                UserSessionInfo session = UserService.getUserSession(userLoginInfo);
                httpSession.setAttribute("userLoginStatus", "true");
                httpSession.setAttribute("userId", session.getUserId());
                httpSession.setAttribute("nickname", session.getNickname());

                response.sendRedirect(request.getContextPath() + "/index.jsp");

                // 正常流程做完后，函数结束
                return;
            }
        }

        /* 4. 所有不成功的情况，重定向回注册页面 */

        response.sendRedirect(request.getContextPath() + "/pages/user-signup.jsp");
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
