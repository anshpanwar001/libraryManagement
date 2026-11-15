<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Sign in – Leave Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body class="center">
  <main class="card narrow">
    <h1>Employee Leave Management</h1>
    <p class="muted">Sign in to request or review leave.</p>
    <c:if test="${not empty error}"><p class="alert"><c:out value="${error}"/></p></c:if>
    <form method="post" action="${pageContext.request.contextPath}/login">
      <label>Username <input name="username" required autofocus></label>
      <label>Password <input name="password" type="password" required></label>
      <button type="submit">Sign in</button>
    </form>
    <p class="muted small">Demo logins: <b>amit / amit123</b> (employee), <b>hr / hr123</b> (HR manager)</p>
  </main>
</body>
</html>
