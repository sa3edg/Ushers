<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_DATAENTRY')">
				<a href="<c:url value="addUsherTimeSheetForm" />"><spring:message
						code="timesheet.usher.add" /></a>
				<c:if test="${not empty usherTimeSheets}">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3><spring:message code="menu.projects" /></h3>
					</div>
					<display:table id="usherTimeSheetsTable" pagesize="10" requestURI="" name="usherTimeSheets" class="CSSTableGenerator">
						<display:column property="projectLocationName" titleKey="timesheet.usher.projectLocationNane" ></display:column>
						<display:column property="projectName" titleKey="timesheet.usher.projectName" ></display:column>
						<display:column property="usherName" titleKey="timesheet.usher.usherName" ></display:column>
						<display:column property="deduction" titleKey="timesheet.usher.deduction" ></display:column>
						<display:column property="debit" titleKey="timesheet.usher.debit" ></display:column>
						<display:column property="date" titleKey="timesheet.usher.date" ></display:column>
						<display:column><a href="<c:url value="/editUsherTimeSheet?id=${usherTimeSheetsTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<display:column><a href="<c:url value="/deleteUsherTimeSheet?id=${usherTimeSheetsTable.id}" />"
				        	onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					    </sec:authorize>
					</display:table>
					
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>