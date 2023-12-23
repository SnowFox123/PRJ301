<%-- 
    Document   : admin
    Created on : Mar 1, 2022, 8:29:12 PM
    Author     : hd
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Capstone list</title>
    </head>
    <body>
        <!--your code here-->
        <h3>Welcome, ${sessionScope.user.fullName}</h3>
        <form action="MainController">
            Search <input type="text" name="txtSearchValue" value="${param.txtSearchValue}" />
            <input type="submit" name="action" value="SEARCH" value="" />
        </form>
        <c:set var="result" value="${requestScope.result}" />
        <c:if test="${not empty result}">
            <table border="1">
                <thead>
                    <tr>
                        <td>No</td>
                        <td>id</td>
                        <td>name</td>
                        <td>description</td>
                        <td>userID</td>
                        <td>delete</td>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${result}" var="dto" varStatus="counter">
                        <tr>
                            <td>${counter.count}</td>
                            <td>${dto.id}</td>
                            <td>${dto.name}</td>
                            <td>${dto.description}</td>
                            <td>${dto.userID}</td>
                            <td>
                                <c:url var="deletelink" value="MainController">
                                    <c:param name="action" value="DELETE" />
                                    <c:param name="pk" value="${dto.id}" />
                                    <c:param name="lastSearchValue" value="${param.txtSearchValue}" />
                                </c:url>
                                <a href="${deletelink}">Delete</a>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </c:if>
        <a href="MainController?action=LOGOUT">Logout</a>
    </body>
</html>
