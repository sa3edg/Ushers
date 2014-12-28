<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="dataEntryTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasRole('ROLE_DATAENTRY')">
				<a href="<c:url value="addAreaForm" />"><spring:message
						code="areas.addarea" /></a>
				<c:if test="${not empty projectTypes}">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3>Users</h3>
					</div>
					<display:table id="projectTypesTable" pagesize="10" requestURI="" name="projectTypes" class="CSSTableGenerator">
						<display:column property="name" titleKey="ushers.name" ></display:column>
						<display:column><a href="<c:url value="/editProjectType?id=${projectTypesTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/deleteProjectType?id=${projectTypesTable.id}" />"
				        onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					</display:table>
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>