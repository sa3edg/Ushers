<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER')">
				<a href="<c:url value="addUsherForm" />"><spring:message
						code="usher.addUsher" /></a>
				<c:if test="${not empty ushers}">
				<sec:authorize access="hasAnyRole('ROLE_USHER', 'ROLE_DATAENTRY')">
				<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3><spring:message code="menu.ushers" /></h3>
					</div>
					<display:table id="usherTable" pagesize="10" requestURI="" name="ushers" class="CSSTableGenerator" >
						<display:column property="usherCode" titleKey="usher.usherCode" ></display:column>
						<display:column property="firstName" titleKey="usher.firstName" ></display:column>
						<display:column property="middleName" titleKey="usher.middleName" ></display:column>
						<display:column property="lastName" titleKey="usher.lastName" ></display:column>
						<display:column><a href="<c:url value="/viewUsher?id=${usherTable.id}" />">
				        <img src="<c:url value='/resources/images/profile.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/editUsher?id=${usherTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<display:column><a href="<c:url value="/deleteUsher?id=${usherTable.id}" />"
				        	onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					    </sec:authorize>
					</display:table>
					</sec:authorize>
					<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER')">
					<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
					<div class="table-title">
						<h3><spring:message code="menu.ushers" /></h3>
					</div>
					<display:table id="usherTable" pagesize="10" requestURI="" name="ushers" class="CSSTableGenerator" eport="true">
						<display:column property="usherCode" titleKey="usher.usherCode" ></display:column>
						<display:column property="firstName" titleKey="usher.firstName" ></display:column>
						<display:column property="middleName" titleKey="usher.middleName" ></display:column>
						<display:column property="lastName" titleKey="usher.lastName" ></display:column>
						<display:column><a href="<c:url value="/viewUsher?id=${usherTable.id}" />">
				        <img src="<c:url value='/resources/images/profile.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<display:column><a href="<c:url value="/editUsher?id=${usherTable.id}" />">
				        <img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
						<sec:authorize access="hasRole('ROLE_ADMIN')">
						<display:column><a href="<c:url value="/deleteUsher?id=${usherTable.id}" />"
				        	onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Link to this page"> </a></display:column>
					    </sec:authorize>
					</display:table>
					</sec:authorize>
				</c:if>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>