<%@ page import="model.Poll" %>
<%@ page import="model.Question" %>
<%@ page import="java.util.List" %>
<%@ page import="model.Answer" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<% Poll poll = (Poll) request.getAttribute("poll"); %>
<%List<Question> questions = (List<Question>) request.getAttribute("question");%>
<p><%poll.getDescription(); %></p>
<form action="/result">
    <%      for (Question question : questions) {
    %>
    <input type="hidden" name="pollId" value="<%=poll.getId()%>">

    <p><b><%question.getText();%></b></p>
    <p><%
        for (Answer answer : question.getAnswers()) {
    %>
    <p><label>
        <input type="radio" name="<%=question.getId()%>" value="<%=answer.getWeight()%>">
    </label><% answer.getText();%><br>
    </p>
    <%}%>
    <br
    <%}%>

    <p><input type="submit"></p>
</form>
</body>
</html>
