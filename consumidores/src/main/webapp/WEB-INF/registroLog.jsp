<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>  

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
        <link rel="stylesheet" href="/css/registroLog.css">

    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">
    <!-- Etiquetas meta de JavaScript Opcionales-->
    <!-- Primero jQuery, luego Popper.js, y por último Bootstrap JS -->
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js" integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"></script>
    <title>Subscritions</title>
</head>
<body>
    <div class="container">
        <h3 class="display-3 header">Welcome</h3>
		<div class="contenedor" style="background-color: #ffd2a5;">
			<h3 class="display-4">Register</h3>
			<p><form:errors path="user.*"/></p>
	    
	    	<form:form method="POST" action="/" modelAttribute="user">

	        <p>
	            <form:label class="col-sm-4 col-form-label" path="firstName">FirstName</form:label>
	            <form:input class="form-control col-sm-6 " type="text" path="firstName"/>
	        </p>
             <p>
	            <form:label class="col-sm-4 col-form-label" path="lastName">LastName</form:label>
	            <form:input class="form-control col-sm-6 " type="text" path="lastName"/>
	        </p>
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="email">Email</form:label>
	            <form:input class="form-control col-sm-6" type="email" path="email"/>
	        </p>
	        <p>
	            <form:label  class="col-sm-4 col-form-label" path="password">Password</form:label>
	            <form:password class="form-control col-sm-6" path="password"/>
	        </p>
	        <p>
	            <form:label class="col-sm-4 col-form-label" path="passwordConfirmation">Password Confirmation</form:label>
	            <form:password class="form-control col-sm-6" path="passwordConfirmation"/>
	        </p>
            <p>

	         <button  class="boton">Register</button>
	        </form:form>
       
		</div>
		<div class="contenedor" style="background-color: #ffd2a5;">
			<h3 class="display-4">Login</h3>
			    <p><c:out value="${error}" /></p>
		    <form method="post" action="/login">
		        <p>
		            <label class="col-sm-3 col-form-label" for="email">Email</label>
		            <input class="form-control-sm-6 log" type="text" id="email" name="email"/>
		        </p>
		        <p>
		            <label class="col-sm-3 col-form-label" for="password">Password</label>
		            <input class="form-control-sm-6 log" type="password" id="password" name="password"/>
		        </p>
		        <button  class="boton">Login</button>
		    </form>  
	</div>
	
	</div>
</body>
</html>