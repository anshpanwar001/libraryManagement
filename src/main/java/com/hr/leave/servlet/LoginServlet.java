package com.hr.leave.servlet;

import com.hr.leave.dao.DataStore;
import com.hr.leave.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("/login.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = DataStore.authenticate(req.getParameter("username"), req.getParameter("password"));
        if (user == null) {
            req.setAttribute("error", "Wrong username or password. Check them and try again.");
            req.getRequestDispatcher("/login.jsp").forward(req, resp);
            return;
        }
        req.getSession().invalidate();               // new session on login
        req.getSession(true).setAttribute("user", user);
        resp.sendRedirect(req.getContextPath() + (user.isManager() ? "/manage" : "/leave"));
    }
}
