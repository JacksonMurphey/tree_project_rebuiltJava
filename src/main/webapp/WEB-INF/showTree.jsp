<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>Show Product</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>


<h1 class="m-2">Arbortrary Trees</h1>
<h4 class="m-2">A site to track arbortrary trees planted</h4>
<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<a href="/dashboard" class="m-2 btn btn-secondary">Home</a>
<a href="/mytrees" class="m-2 btn btn-danger">My Trees</a>
<a href="/logout" class="m-2 btn btn-warning">Logout</a>
<hr>
<hr>



<h1 class="m-4">${tree.species}</h1>  
<h4 class="m-4">Planted by: ${tree.planter.firstName} ${tree.planter.lastName}</h4>
<h4 class="m-4">Where: ${tree.location}</h4>
<h4 class="m-4">Reason: ${tree.reason}</h4>
<hr>
<hr>




<c:choose>
    <c:when test="${ tree.planter.id == user.id }">
        <h5 class="m-2">You planted this Tree, You cannot visit it again</h5>
        <a href> edit <>
    </c:when>
    <c:otherwise>
        
        <c:choose>
            <c:when test="${tree.usersVisited.contains(user)}">
                <span class="m-2">You have already Visited this Tree: </span> <a href="/tree/${tree.id}/leave/${user.id}" class="m-2 btn btn-primary">Leave</a>
            </c:when>
        <c:otherwise>
            <h4 class="m-2">Have you been to visit this Tree?</h4>
            <a href="/tree/${tree.id}/join/${user.id}" class="m-2 btn btn-primary">Visit</a>
        </c:otherwise>
        </c:choose>
    </c:otherwise>
</c:choose>



<table class="m-2 table table-hover">
    <thead>
        <tr>
            <th>Visited By ${tree.usersVisited.size()} user(s)</th>
        </tr>
    </thead>
    <tbody>
        <c:forEach items="${tree.usersVisited}" var="user">
        <tr>
            <td> ${user.firstName} ${user.lastName}</td>
        </tr>
        </c:forEach>
    </tbody>
</table>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>