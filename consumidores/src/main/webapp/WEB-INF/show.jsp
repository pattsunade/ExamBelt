<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <!-- Etiquetas meta de JavaScript Opcionales-->
    <!-- Primero jQuery, luego Popper.js, y por último Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
     <div class="container">
            <div class="alert alert-success" role="alert">
                <h1 class="display-3">Welcome, <c:out value="${user.firstName}"/> <c:out value="${user.lastName}"/></h1>
                <a href="/" class="salida">Logout</a>
                <h1>Your Current Package: <c:out value="${user.subss.name}"/></h1>
			    <h2>Packages Cost: <c:out value="${user.subss.price}"/></h2>
                <h2>Switch Package</h2>
		        <form:form action="/users/${user.id}" modelAttribute="user" method="post">
			        <form:select path="subss">
				        <c:forEach items="${suscripciones}" var="sus">
					        <c:if test="${sus.estado == true && user.subss.name != sus.name}">
						    <form:option value="${sus.id}"><c:out value="${sus.name}"/></form:option>
					        </c:if>
				        </c:forEach>
			        </form:select>
			        <input type="submit" value="Switch"> 
		        </form:form>
	        </div>
        </div>
</body>
</html>