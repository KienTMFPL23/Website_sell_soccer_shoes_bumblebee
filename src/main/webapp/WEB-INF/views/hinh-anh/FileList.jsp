<%@ page pageEncoding="utf-8" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<head>
    <title>All files</title>
    <meta charset="utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1"/>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/
      4.1.0/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/
        jquery.min.js"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.0/
        umd/popper.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.1.0/js/
        bootstrap.min.js"></script>
</head>

<body>
<div class="container h-100">
    <h4>Upload Multiple Files:</h4>
    <form method="POST" action="/uploadFiles" enctype="multipart/form-data">
        <input type="file" name="files" multiple/> <br/><br/>
        <button type="submit">Submit</button>
    </form>

    <hr/>
    <c:if test="${message}">
    <h2>${message}</h2>
</div>
</c:if>
</div>
</body>
</html>