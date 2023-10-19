<%@ page pageEncoding="utf-8" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<!DOCTYPE html>
<html>
<head>
    <neta charset="UTF-8"/>
    <title>Paypal Payment with Spring Boot ShareEverythings.com</title>
</head>
<body>
<h1>Paypal Payment with Spring Boot - ShareEverythings.com</h1>
<form method="post" action="/pay">
    <input type="text" value="${price}" name="price"/>
    <button type="submit"> Payment with Paypal</button>
</form>
</body>
</html>