<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="ushersTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasRole('ROLE_USHER')">
				<a href="<c:url value="addUsherForm" />"><spring:message
						code="usher.addUsher" /></a>
				<c:if test="${not empty areas}">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3>Users</h3>
					</div>
					<display:table id="usherTable" pagesize="10" requestURI="" name="areas" class="CSSTableGenerator">
						<display:column property="usherCode" titleKey="usher.usherCode" ></display:column>
						<display:column property="firstName" titleKey="usher.firstName" ></display:column>
						<display:column property="middleName" titleKey="usher.middleName" ></display:column>
						<display:column property="lastName" titleKey="usher.lastName" ></display:column>
						<display:column><a href="<c:url value="/editUsher?id=${usherTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/deleteUsher?id=${usherTable.id}" />"
				        onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					</display:table>
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>