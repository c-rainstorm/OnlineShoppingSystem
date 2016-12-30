package com.groupnine.oss.user.action;

import java.io.IOException;
import java.io.InputStream;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/UpdateAvatarAction")
public class UpdateAvatarAction extends HttpServlet {

    public UpdateAvatarAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameter */

        String uid = (String) request.getSession().getAttribute("usreId");
        Part image = request.getPart("image");

        /* 2. invoke service */

        InputStream imageIS = null;
        if (image != null && image.getSize() != 0)
            imageIS = image.getInputStream();

        String newAvatarAddr = UserService.updateAvatar(uid, request.getServletContext(), imageIS);

        /* 3. set session */

        request.getSession().setAttribute("userAvatarAddr", newAvatarAddr);

        /* 4. return JSON */

        Gson gson = new Gson();
        JsonObject result = new JsonObject();
        result.addProperty("avatar", newAvatarAddr);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
