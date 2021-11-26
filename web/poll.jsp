<%@ page import="model.Poll" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

        <%List<Poll> polls  = (List<Poll>) request.getAttribute("poll"); %>

        <% if (polls != null && !polls.isEmpty())
        {
            for (Poll poll : polls) {%>

        <a href="/question?id=<%=poll.getId()%>"><%=poll.getName()%></a>        <%
                }
            }%>

    </a>
</body>
</html>
