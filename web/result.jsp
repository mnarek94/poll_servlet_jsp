<%@ page import="model.Result" %><%--
  Created by IntelliJ IDEA.
  User: DELL
  Date: 26.11.2021
  Time: 5:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%Result result = (Result) request.getAttribute("result");%>
<h3>Your result:</h3><br>
<h2><%if (result!= null){
    <%=result.getExplanation()%>
    } else {
    <p>Your score does not match the norm</p>
    }<%}%>

</h2><br>

<a href="/poll">New poll</a>
</body>
</html>
