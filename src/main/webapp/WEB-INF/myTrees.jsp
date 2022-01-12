<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<%@ page isErrorPage="true" %> 
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
<head>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/css/bootstrap.min.css" rel="stylesheet"
                integrity="sha384-F3w7mX95PdgyTmZZMECAngseQB83DfGTowi0iMjiWaeVhAn4FJkqJByhZMI3AhiU"
                crossorigin="anonymous">
<meta charset="UTF-8">
<title>DashBoard</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h1 class="m-2">Arbortrary Trees</h1>
<h4 class="m-2">A site to track arbortrary trees planted</h4>
<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<a href="/dashboard" class="m-2 btn btn-secondary">Home</a>
<a href="/tree/new" class="m-2 btn btn-primary">Plant a Tree</a>
<a href="/logout" class="m-2 btn btn-warning">Logout</a>
<hr>

<div class="container">
<table class="m-2 table table-hover ">
    <tr class="table-info">
        <th>Species</th>
        <th>Date Planted</th>
        <th>Actions</th>
        <th>Delete</th>
    </tr>
    <tbody>
        <c:forEach items="${user.trees}" var="tree">
            <tr>
                <td>${tree.species}</td>
                <td><fmt:formatDate type="date" value="${tree.plantDate}" /></td>

                <td> 
                <a href="tree/show/${tree.id}">Details</a> | 
                <a href="tree/edit/${tree.id}">Edit</a>
                </td>
                <td>
                <form class="delete-form" action="/tree/delete/${tree.id}" method="post">
					<input type="hidden" name="_method" value="delete" />
					<button>Delete</button>
				</form>
                
                </td>
            </tr>
        </c:forEach>
    </tbody>
</table>
</div>


<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.1/dist/js/bootstrap.bundle.min.js"
        integrity="sha384-/bQdsTh/da6pkI1MST/rWKFNjaCP5gBSY4sEBT38Q/9RBh9AH40zEOg7Hlq2THRZ"
        crossorigin="anonymous"></script>

</body>
</html>