<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  
<!DOCTYPE html>
<html lang="en">
<head>
      <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <link rel="stylesheet" href="/css/dashboard.css">
    <!-- Etiquetas meta de JavaScript Opcionales-->
    <!-- Primero jQuery, luego Popper.js, y por último Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <title>Document</title>
</head>
<body>
    <div class="container">
        <h1>Admin Dashboard</h1>
        <a href="/" class="salida">Logout</a>
     <h3>Customers</h3>
		<table class="table table-sm">
		<thead>
			<tr>
				<th>Name</th>
				<th>Next Due Date</th>
				<th>Amount Due</th>
				<th>Package Type</th>
												
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${users}" var="user">
					<c:if test="${user.userRol !=  1}">
						<tr>
							<td><c:out value="${user.firstName}"/></td>
							<td><c:out value="${user.date}"/></td>
							<td><c:out value="${user.subss.price}"/></td>
							<td><c:out value="${user.subss.name}"/></td>
						</tr>
					</c:if>
				</c:forEach>

		</tbody>
	</table> 
    
    <h3>Packages</h3>
		<table class="table table-sm">
		<thead>
			<tr>
				<th>Packages Name</th>
				<th>Packages Cost</th>
				<th>Avalible</th>
				<th>User</th>
                <th>Actions</th>
												
			</tr>
		</thead>
		<tbody>
			<c:forEach items="${subs}" var="p">
        		<tr>
        			<td><c:out value="${p.name}"/></td>
        			<td>$<c:out value="${p.price}"/></td>
                    <td>
                        <c:if test="${p.estado == true}">
                            Availible
                        </c:if>
                        <c:if test="${p.estado == false}" >
                            Unavailable
                        </c:if>
                    </td>
        			<td><c:out value="${p.users.size()}"/></td>
                    <td>
                        <c:if test="${p.users.size() > 0 && p.estado == false}">desactivate</c:if>
                        <c:if test="${p.users.size() == 0 && p.estado == false}">
                            <a href="/packages/${p.id}">activate</a>
                        </c:if>
                        <c:if test="${p.users.size() == 0 && p.estado == true}">
                            <a href="/packages/${p.id}">desactivate</a>
                        </c:if>
                        <a href="/packages/${p.id}/edit">edit</a>
                    </td>
        		</tr>
        	</c:forEach>
		</tbody>
	</table>
    <div class="creator" style="background-color: #ffcef3;">
		<h1 class="display-4">Create Packages</h1>
 <form:form action="/packages" method="post" modelAttribute="paquete">
    <p>
        <form:label class="col-sm-1 col-form-label"  path="price">Name</form:label>
        <form:errors path="name"/>
        <form:input class="form-control-sm-1 log log" type="text"  path="name"/>
    </p>
        <p>
        <form:label class="col-sm-1 col-form-label"  path="price">Cost</form:label>
        <form:errors path="price"/>
        <form:input class="form-control-sm-1 log log" type="text"  path="price"/>
    </p>
   
    <button  class="boton">Create Packages</button>
</form:form>
	</div>   
    </div>
    
</body>
</html>