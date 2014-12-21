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
				<a href="<c:url value="addGovernorateForm" />"><spring:message
						code="governorate.addGovernorate" /></a>
				<c:if test="${not empty governorates}">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3>Users</h3>
					</div>
					<display:table id="governoratesTable" pagesize="10" requestURI="" name="governorates" class="CSSTableGenerator">
						<display:column property="name" titleKey="ushers.name" ></display:column>
						<display:column><a href="<c:url value="/editGovernorate?id=${governoratesTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/deleteGovernorate?id=${governoratesTable.id}" />"
				        onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					</display:table>
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>