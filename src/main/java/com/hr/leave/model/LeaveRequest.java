package com.hr.leave.model;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

public class LeaveRequest {
    public static final String PENDING = "PENDING", APPROVED = "APPROVED", REJECTED = "REJECTED";

    private final int id;
    private final String username, employeeName, type, reason;
    private final LocalDate fromDate, toDate;
    private String status = PENDING;

    public LeaveRequest(int id, String username, String employeeName, String type,
                        LocalDate fromDate, LocalDate toDate, String reason) {
        this.id = id; this.username = username; this.employeeName = employeeName;
        this.type = type; this.fromDate = fromDate; this.toDate = toDate; this.reason = reason;
    }
    public int getId()              { return id; }
    public String getUsername()     { return username; }
    public String getEmployeeName() { return employeeName; }
    public String getType()         { return type; }
    public String getReason()       { return reason; }
    public LocalDate getFromDate()  { return fromDate; }
    public LocalDate getToDate()    { return toDate; }
    public String getStatus()       { return status; }
    public void setStatus(String s) { this.status = s; }
    public long getDays()           { return ChronoUnit.DAYS.between(fromDate, toDate) + 1; }
}
