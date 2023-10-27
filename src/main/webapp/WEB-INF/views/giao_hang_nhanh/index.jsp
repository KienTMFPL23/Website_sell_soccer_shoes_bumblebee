<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <title>Your JSP Page</title>
</head>
<body>

<%--    <select>--%>
<%--        <c:forEach items="${list}" var="list">--%>
<%--            <option>${list.ProvinceName}</option>--%>
<%--        </c:forEach>--%>
<%--    </select>--%>
    ${list}
</body>
</html>