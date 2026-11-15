package com.hr.leave.servlet;

import com.hr.leave.dao.DataStore;
import com.hr.leave.model.User;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;

/** Employee page: GET shows form + own requests, POST submits a new request. */
@WebServlet("/leave")
public class LeaveServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        req.setAttribute("leaves", DataStore.leavesOf(user.getUsername()));
        req.getRequestDispatcher("/dashboard.jsp").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        User user = (User) req.getSession().getAttribute("user");
        String type = req.getParameter("type");
        String reason = req.getParameter("reason") == null ? "" : req.getParameter("reason").trim();
        String error = null;
        try {
            LocalDate from = LocalDate.parse(req.getParameter("fromDate"));
            LocalDate to = LocalDate.parse(req.getParameter("toDate"));
            if (to.isBefore(from))            error = "The end date must be on or after the start date.";
            else if (from.isBefore(LocalDate.now())) error = "The start date cannot be in the past.";
            else if (reason.isEmpty())        error = "Add a short reason for your leave.";
            else {
                DataStore.addLeave(user, type, from, to, reason);
                resp.sendRedirect(req.getContextPath() + "/leave");
                return;
            }
        } catch (DateTimeParseException | NullPointerException e) {
            error = "Choose both a start date and an end date.";
        }
        req.setAttribute("error", error);
        doGet(req, resp);
    }
}
