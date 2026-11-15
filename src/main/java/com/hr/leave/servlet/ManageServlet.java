package com.hr.leave.servlet;

import com.hr.leave.dao.DataStore;
import com.hr.leave.model.LeaveRequest;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

/** HR page: GET lists all requests, POST approves or rejects one. */
@WebServlet("/manage")
public class ManageServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.setAttribute("leaves", DataStore.allLeaves());
        req.getRequestDispatcher("/manage.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException {
        try {
            int id = Integer.parseInt(req.getParameter("id"));
            String action = req.getParameter("action");
            if ("approve".equals(action)) DataStore.setStatus(id, LeaveRequest.APPROVED);
            else if ("reject".equals(action)) DataStore.setStatus(id, LeaveRequest.REJECTED);
        } catch (NumberFormatException ignored) { }
        resp.sendRedirect(req.getContextPath() + "/manage");
    }
}
