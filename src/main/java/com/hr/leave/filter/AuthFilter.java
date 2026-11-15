package com.hr.leave.filter;

import com.hr.leave.model.User;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.*;
import java.io.IOException;

/** Blocks /leave and /manage for anonymous users, and /manage for non-HR users. */
@WebFilter(urlPatterns = {"/leave", "/manage"})
public class AuthFilter implements Filter {
    /**
     * Keep these methods explicit so the filter also works with servlet
     * containers that expose the older Filter contract at runtime.
     */
    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // No filter configuration is required.
    }

    @Override
    public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        HttpServletRequest request = (HttpServletRequest) req;
        HttpServletResponse response = (HttpServletResponse) res;
        HttpSession session = request.getSession(false);
        User user = session == null ? null : (User) session.getAttribute("user");

        if (user == null) { response.sendRedirect(request.getContextPath() + "/login"); return; }
        if (request.getServletPath().equals("/manage") && !user.isManager()) {
            response.sendError(HttpServletResponse.SC_FORBIDDEN, "HR access only");
            return;
        }
        chain.doFilter(req, res);
    }

    @Override
    public void destroy() {
        // No resources to release.
    }
}
