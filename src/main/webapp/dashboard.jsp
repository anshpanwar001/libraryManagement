<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>My leave – Leave Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <header>
    <strong>Leave Management</strong>
    <span><c:out value="${user.name}"/> · <a href="${pageContext.request.contextPath}/logout">Sign out</a></span>
  </header>
  <main>
    <section class="card">
      <h2>Request leave</h2>
      <c:if test="${not empty error}"><p class="alert"><c:out value="${error}"/></p></c:if>
      <form method="post" action="${pageContext.request.contextPath}/leave" class="grid">
        <label>Leave type
          <select name="type">
            <option>Casual</option><option>Sick</option><option>Earned</option><option>Maternity/Paternity</option>
          </select>
        </label>
        <label>From <input type="date" name="fromDate" required></label>
        <label>To <input type="date" name="toDate" required></label>
        <label class="full">Reason <textarea name="reason" rows="2" required></textarea></label>
        <button type="submit">Submit request</button>
      </form>
    </section>

    <section class="card">
      <h2>My requests</h2>
      <c:choose>
        <c:when test="${empty leaves}"><p class="muted">No requests yet. Use the form above to submit your first one.</p></c:when>
        <c:otherwise>
          <div class="scroll"><table>
            <tr><th>#</th><th>Type</th><th>From</th><th>To</th><th>Days</th><th>Reason</th><th>Status</th></tr>
            <c:forEach var="l" items="${leaves}">
              <tr>
                <td>${l.id}</td><td><c:out value="${l.type}"/></td><td>${l.fromDate}</td><td>${l.toDate}</td>
                <td>${l.days}</td><td><c:out value="${l.reason}"/></td>
                <td><span class="tag ${l.status}">${l.status}</span></td>
              </tr>
            </c:forEach>
          </table></div>
        </c:otherwise>
      </c:choose>
    </section>
  </main>
</body>
</html>
