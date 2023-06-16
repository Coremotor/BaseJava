<%@ page import="com.urise.webapp.model.ContactsType" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!doctype html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="css/style.css">
    <meta http-equiv="X-UA-Compatible" content="ie=edge">
    <title>Список всех резюме</title>
</head>
<body>
<div class="wrapper">
    <jsp:include page="fragments/header.jsp"/>
    <div class="content">
        <a href="resume?action=add"><img src="img/add.png"></a>
        <br>
        <table>
            <tr>
                <th>Full name</th>
                <th>UUID</th>
                <th>Edit</th>
                <th>Delete</th>
            </tr>
            <c:forEach items="${resumes}" var="resume">
                <jsp:useBean id="resume" type="com.urise.webapp.model.Resume"/>
                <tr>
                    <td><a href="resume?uuid=${resume.uuid}&action=view">${resume.fullName}</a></td>
                    <td><%=ContactsType.EMAIL.toHtml(resume.getContact(ContactsType.EMAIL))%>
                    </td>
                    <td><a href="resume?uuid=${resume.uuid}&action=edit"><img src="img/pencil.png"></a></td>
                    <td><a href="resume?uuid=${resume.uuid}&action=delete"><img src="img/delete.png"></a></td>
                </tr>
            </c:forEach>
        </table>
    </div>
    <jsp:include page="fragments/footer.jsp"/>
</div>
</body>
</html>
