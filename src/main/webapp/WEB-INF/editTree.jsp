<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%> 
<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>New Tree</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h1 class="m-2">Arbortrary Trees</h1>
<h4 class="m-2">A site to track arbortrary trees planted</h4>
<a href="/logout" class="m-2 btn btn-warning">Logout</a>
<hr>

<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<a href="/mytrees" class="m-2 btn btn-danger">My Trees</a>
<a href="/dashboard" class="m-2 btn btn-secondary">Home</a>


<h1 class="m-4">Plant A New Tree</h1>
    <strong>
        <p class="text-lg-left m-2" style="font-size: larger;">${error}</p>
    </strong>
<form:form action="/tree/${tree.id}" method="POST" modelAttribute="tree" class="m-4">
    <input type="hidden" name="_method" value="put">
    <p>
        <form:label path="species">Species:</form:label>
        <%-- <form:errors path="species"/> --%>
        <form:input path="species"/>
    </p>
    <p>
        <form:label path="location">Location:</form:label>
        <%-- <form:errors path="location"/> --%>
        <form:input path="location"/>
    </p>
    <p>
        <form:label path="reason">Reason:</form:label>
        <%-- <form:errors path="reason"/> --%>
        <form:input path="reason"/>
    </p>
    <p>
        <form:label path="plantDate">Date Planted:</form:label>
        <%-- <form:errors path="plantDate"/> --%>
        <form:input type="date" path="plantDate"/>
    </p>
    <form:hidden path="planter" value="${ user.id }"/>
    <input type="submit" value="Edit Tree"/>
</form:form>   


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>
</body>
</html>