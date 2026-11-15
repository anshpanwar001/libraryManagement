<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8"><meta name="viewport" content="width=device-width, initial-scale=1">
  <title>Review leave – Leave Management</title>
  <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
  <header>
    <strong>Leave Management · HR</strong>
    <span><c:out value="${user.name}"/> · <a href="${pageContext.request.contextPath}/logout">Sign out</a></span>
  </header>
  <main>
    <section class="card">
      <h2>All leave requests</h2>
      <c:choose>
        <c:when test="${empty leaves}"><p class="muted">No leave requests have been submitted yet.</p></c:when>
        <c:otherwise>
          <div class="scroll"><table>
            <tr><th>#</th><th>Employee</th><th>Type</th><th>From</th><th>To</th><th>Days</th><th>Reason</th><th>Status</th><th>Action</th></tr>
            <c:forEach var="l" items="${leaves}">
              <tr>
                <td>${l.id}</td><td><c:out value="${l.employeeName}"/></td><td><c:out value="${l.type}"/></td>
                <td>${l.fromDate}</td><td>${l.toDate}</td><td>${l.days}</td><td><c:out value="${l.reason}"/></td>
                <td><span class="tag ${l.status}">${l.status}</span></td>
                <td>
                  <c:if test="${l.status == 'PENDING'}">
                    <form method="post" action="${pageContext.request.contextPath}/manage" class="inline">
                      <input type="hidden" name="id" value="${l.id}">
                      <button name="action" value="approve" class="ok">Approve</button>
                      <button name="action" value="reject" class="no">Reject</button>
                    </form>
                  </c:if>
                </td>
              </tr>
            </c:forEach>
          </table></div>
        </c:otherwise>
      </c:choose>
    </section>
  </main>
</body>
</html>
