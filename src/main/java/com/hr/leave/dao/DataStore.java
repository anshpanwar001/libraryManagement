package com.hr.leave.dao;

import com.hr.leave.model.LeaveRequest;
import com.hr.leave.model.User;

import java.time.LocalDate;
import java.util.*;

/**
 * In-memory data store so the project runs with no database setup.
 * To use MySQL later, keep these method signatures and re-implement them with JDBC.
 * Data resets when the server restarts.
 */
public final class DataStore {
    private static final Map<String, User> USERS = new HashMap<>();
    private static final List<LeaveRequest> LEAVES = new ArrayList<>();
    private static int nextId = 1;

    static {
        USERS.put("hr",    new User("hr",    "hr123",  "Priya Sharma (HR)", "HR"));
        USERS.put("amit",  new User("amit",  "amit123", "Amit Verma",       "EMPLOYEE"));
        USERS.put("neha",  new User("neha",  "neha123", "Neha Gupta",       "EMPLOYEE"));
    }

    private DataStore() {}

    public static User authenticate(String username, String password) {
        User u = USERS.get(username == null ? "" : username.trim());
        return (u != null && u.getPassword().equals(password)) ? u : null;
    }

    public static synchronized void addLeave(User u, String type, LocalDate from, LocalDate to, String reason) {
        LEAVES.add(new LeaveRequest(nextId++, u.getUsername(), u.getName(), type, from, to, reason));
    }

    public static synchronized List<LeaveRequest> leavesOf(String username) {
        List<LeaveRequest> out = new ArrayList<>();
        for (LeaveRequest l : LEAVES) if (l.getUsername().equals(username)) out.add(l);
        Collections.reverse(out); // newest first
        return out;
    }

    public static synchronized List<LeaveRequest> allLeaves() {
        List<LeaveRequest> out = new ArrayList<>(LEAVES);
        Collections.reverse(out);
        return out;
    }

    public static synchronized void setStatus(int id, String status) {
        for (LeaveRequest l : LEAVES)
            if (l.getId() == id && LeaveRequest.PENDING.equals(l.getStatus())) l.setStatus(status);
    }
}
