<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div class="menu">
Menu
	<ul dir="rtl">
	   <li>
	    	<a href="<c:url value="/addUserForm" />">
	    	<spring:message code="menu.ushers.timesheet" /></a>
 	   </li>
	   <li>
	    	<a href="<c:url value="/addUserForm" />">
	    	<spring:message code="menu.superUshers.timesheet" /></a>
 		</li>
 		<li>
	    	<a href="<c:url value="/addUserForm" />">
	    	<spring:message code="menu.projects" /></a>
 		</li>
 		<li>
	    	<a href="<c:url value="/governorates" />">
	    	<spring:message code="menu.governorate" /></a>
 		</li>
 		<li>
	    	<a href="<c:url value="/areas" />">
	    	<spring:message code="menu.area" /></a>
 		</li>
 		<li>
	    	<a href="<c:url value="/preferredLocations" />">
	    	<spring:message code="menu.preferredLocations" /></a>
 		</li>
	</ul>
</div>