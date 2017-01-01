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

@WebServlet("/modifyReceiver.action")
public class ModifyReceiverAction extends HttpServlet {

    public ModifyReceiverAction() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("application/json");

        /* 1. read parameters */

        String rid = request.getParameter("receiverId");
        String name = request.getParameter("receiverName");
        String address = request.getParameter("receiverAddress");
        String phone = request.getParameter("receiverPhone");

        /* 2. invoke service */

        boolean changed = false;
        if (rid != null && name != null && address != null && phone != null) {
            Receiver r = new Receiver();
            r.setReceiverId(rid);
            r.setName(name);
            r.setAddress(address);
            r.setPhone(phone);
            changed = UserService.modifyReceiver(r);
        }

        /* 3 .JSON */

        Object result = changed ? new TrueResult() : new FalseResult();
        Gson gson = new Gson();
        gson.toJson(result, response.getWriter());
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }
}
