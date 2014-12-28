<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="dataEntryTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasRole('ROLE_USHER')">
				<a href="<c:url value="addProjectForm" />"><spring:message
						code="projects.addProject" /></a>
				<c:if test="${not empty projects}">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3>Users</h3>
					</div>
					<display:table id="projectTable" pagesize="10" requestURI="" name="projects" class="CSSTableGenerator">
						<display:column property="projectCode" titleKey="projects.projectCode" ></display:column>
						<display:column property="projectName" titleKey="projects.projectName" ></display:column>
						<display:column property="projectDate" titleKey="projects.projectDate" ></display:column>
						<display:column><a href="<c:url value="/editProject?id=${projectTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/deleteProject?id=${projectTable.id}" />"
				        onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					</display:table>
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>