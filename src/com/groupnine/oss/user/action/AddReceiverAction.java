package com.groupnine.oss.user.action;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;
import com.groupnine.oss.user.entity.FalseResult;
import com.groupnine.oss.user.entity.Receiver;
import com.groupnine.oss.user.entity.TrueResult;
import com.groupnine.oss.user.service.UserService;

@WebServlet("/addReceiver.action")
public class AddReceiverAction extends HttpServlet {

    public AddReceiverAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String uid = (String) request.getSession().getAttribute("userId");
        String name = request.getParameter("receiverName");
        String address = request.getParameter("receiverAddress");
        String phone = request.getParameter("receiverPhone");

        Receiver r = new Receiver();
        r.setName(name);
        r.setAddress(address);
        r.setPhone(phone);

        // debug begin
        // uid = request.getParameter("uid");
        // debug end

        /* 2. invoke service */

        boolean added = false;
        if (name != null && address != null && phone != null) {
            added = UserService.addReceiver(uid, r);
        }

        /* 3 .JSON */

        Object result = added ? new TrueResult() : new FalseResult();
        Gson gson = new Gson();
        gson.toJson(result, response.getWriter());

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

}
