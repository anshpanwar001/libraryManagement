<div align="center">

# 🗓️ Employee Leave Management System
### Request · Review · Approve

A Java Servlets and JSP web application where employees apply for leave and HR managers approve or reject it.

![Java](https://img.shields.io/badge/Java-11%2B-orange?logo=openjdk&logoColor=white)
![Servlet](https://img.shields.io/badge/Servlet-4.0-blue)
![JSP](https://img.shields.io/badge/JSP%20%2F%20JSTL-2.3-green)
![Maven](https://img.shields.io/badge/Build-Maven-C71A36?logo=apachemaven&logoColor=white)
![Status](https://img.shields.io/badge/status-in%20development-yellow)

</div>

---

## ✨ Overview

This is a role-based web app for managing employee leave. Employees submit leave requests and track their status. HR sees every request in one place and approves or rejects the pending ones. It follows an MVC-style structure (model, data layer, servlets as controllers, JSP views) and needs no database setup to run.

## 🚀 Features

### 👤 Employee
- Sign in and out with session handling
- Apply for leave: **Casual, Sick, Earned, Maternity/Paternity**
- Pick a date range; the number of days is calculated automatically
- See your full request history, newest first, with a colour-coded status (`PENDING`, `APPROVED`, `REJECTED`)

### 🧑‍💼 HR Manager
- View leave requests from all employees
- Approve or reject pending requests with one click
- Only pending requests can be actioned, so a decision can't be overwritten

### 🔒 Security and Validation
- `AuthFilter` blocks anonymous users from `/leave` and `/manage`
- Role check: employees get `403 Forbidden` on the HR page
- A fresh session is created on login, which guards against session fixation
- Form validation: end date not before start date, no past start dates, reason required
- Output escaped with `<c:out>` to prevent XSS

## 🧰 Tech Stack

| Layer        | Technology                                  |
|--------------|---------------------------------------------|
| Language     | Java 11                                     |
| Web          | Servlet API 4.0 (`javax.servlet`)           |
| Views        | JSP + JSTL 1.2                              |
| Styling      | Plain CSS (responsive)                      |
| Build        | Maven (WAR packaging)                       |
| Dev server   | Jetty (via `jetty-maven-plugin`)            |
| Data         | In-memory store (JDBC/MySQL planned)        |

## 📁 Project Structure

```
LeaveManagement/
├── pom.xml
└── src/main/
    ├── java/com/hr/leave/
    │   ├── model/
    │   │   ├── User.java            # username, name, role (EMPLOYEE / HR)
    │   │   └── LeaveRequest.java    # dates, type, reason, status, day count
    │   ├── dao/
    │   │   └── DataStore.java       # In-memory users and leave requests
    │   ├── servlet/
    │   │   ├── LoginServlet.java    # /login
    │   │   ├── LogoutServlet.java   # /logout
    │   │   ├── LeaveServlet.java    # /leave   (employee dashboard)
    │   │   └── ManageServlet.java   # /manage  (HR dashboard)
    │   └── filter/
    │       └── AuthFilter.java      # Authentication + role check
    └── webapp/
        ├── index.jsp                # Redirects to /login
        ├── login.jsp
        ├── dashboard.jsp            # Employee view
        ├── manage.jsp               # HR view
        └── css/style.css
```

## ⚙️ Getting Started

### Prerequisites
- JDK 11 or higher
- Maven 3.6 or higher

### Run locally
```bash
git clone https://github.com/<your-username>/<your-repo>.git
cd <your-repo>
mvn jetty:run
```
Open **http://localhost:8080/leave-management/**

### Deploy to Tomcat
```bash
mvn clean package
```
Copy `target/leave-management.war` into Tomcat's `webapps/` folder.

> **Tomcat version:** use **Tomcat 9** or Jetty 9. Tomcat 10+ uses `jakarta.*` packages, so the `javax.servlet` imports would need to change.

## 🔑 Demo Accounts

| Role     | Username | Password  |
|----------|----------|-----------|
| Employee | `amit`   | `amit123` |
| Employee | `neha`   | `neha123` |
| HR       | `hr`     | `hr123`   |

> Demo data lives in memory and **resets when the server restarts**.

## 🔄 How It Works

```
Browser ──► AuthFilter ──► Servlet (controller) ──► DataStore (model/data)
                                  │
                                  └──► JSP (view) ──► Browser
```

1. A user signs in at `/login`; `LoginServlet` checks credentials and stores the `User` in the session.
2. HR users are redirected to `/manage`, employees to `/leave`.
3. `AuthFilter` checks the session and role before each protected request.
4. Servlets read and update `DataStore`, then forward to a JSP to render the page.
5. POST requests redirect back to the page (Post/Redirect/Get), so a refresh doesn't resubmit the form.

## 🗺️ Roadmap

- [ ] Persist data in MySQL with JDBC and `PreparedStatement`
- [ ] Hash passwords (BCrypt) instead of storing plain text
- [ ] Add CSRF tokens to the POST forms
- [ ] Per-employee leave balance and quotas by leave type
- [ ] Detect overlapping leave requests
- [ ] Let employees cancel pending requests
- [ ] HR notes or a reason when rejecting
- [ ] Filter and search requests (by status, employee, date)
- [ ] Unit tests for `DataStore` and the validation logic
- [ ] Migrate to `jakarta.*` for Tomcat 10+

## 🤝 Contributing

Issues and pull requests are welcome. Fork the repo, create a feature branch and open a PR.

## 👨‍💻 Author

Built by **Ansh Panwar** · [GitHub](https://github.com/anshpanwar001)

## 📄 License

Distributed under the MIT License. See `LICENSE` for details.
