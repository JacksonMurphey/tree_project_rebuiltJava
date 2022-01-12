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
<title>DashBoard</title>
</head>
<body>
<jsp:include page="base.jsp"></jsp:include>

<h1 class="m-2">Arbortrary Trees</h1>
<h4 class="m-2">A site to track arbortrary trees planted</h4>
<h5 class="m-2">Welcome, ${user.firstName} ${user.lastName}</h5>
<a href="/mytrees" class="m-2 btn btn-danger">My Trees</a>
<a href="/tree/new" class="m-2 btn btn-primary">Plant a Tree</a>
<a href="/logout" class="m-2 btn btn-warning">Logout</a>
<hr>

<div class="container">
<table class="m-2 table table-hover ">
    <tr class="table-info">
        <th>Species</th>
        <th>Planted By</th>
        <th>Visitors</th>
        <th>Actions</th>
    </tr>
    <tbody>
        <c:forEach items="${trees}" var="tree">
            <tr>
                <td>${tree.species}</td>
                <td>${tree.planter.firstName} ${tree.planter.lastName}</td>

                <td>${tree.usersVisited.size()}</td> 
                <td>
                <c:choose>
						<c:when test="${ tree.planter.id == user.id }">
                            <a href="tree/show/${tree.id}">View</a> | 
								<a href="/tree/edit/${tree.id}">Edit</a>
								</c:when>
								<c:otherwise>

									<c:choose>
										<c:when test="${ tree.usersVisited.contains(user) }">
											<span><a href="/tree/${tree.id}/leave/${user.id}">Leave</a> |</span>
                                            <a href="tree/show/${tree.id}">View</a>
										</c:when>
										<c:otherwise>
											<a href="/tree/${tree.id}/join/${user.id}">Visit</a> | 
                                            <a href="tree/show/${tree.id}">View</a>								
										</c:otherwise>
									
									</c:choose>
								</c:otherwise>
							</c:choose>
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