<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">



		<div class="body">
			<h1>Ushers</h1>

			lang : <a href="?lang=en">English</a> | <a
				href="?lang=ar">Arabic</a>

			<h3>
				welcome.springmvc :
				<spring:message code="footer.content" text="default text" />
			</h3>
			
			<h3>
				hello :
				<spring:message code="footer.hello" text="default text" />
			</h3>


		</div>



	</tiles:putAttribute>
</tiles:insertDefinition>
<!-- <html>
<head>

</head>
<body>
	<h1>HTTP Status 403 - Access is denied</h1>

	<c:choose>
		<c:when test="${empty username}">
			<h2>You do not have permission to access this page!</h2>
		</c:when>
		<c:otherwise>
			<h2>Username : ${username} <br/>You do not have permission to access this page!</h2>
		</c:otherwise>
	</c:choose>

</body>
</html> -->