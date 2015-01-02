<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER', 'ROLE_DATAENTRY')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form method="post" action="addProject" modelAttribute="project">
					    <c:set var="validateName"><spring:message code="ushers.valid.name" /></c:set>
						<table>
							<tr>
							    <td><form:label path="firstName"><spring:message code="usher.firstName"/></form:label></td>
								<td><form:input path="firstName" /></td>
							</tr>
							 <tr>
                            	<td><form:label path="clientId"><spring:message code="projects.projectClient"/></form:label></td>
                            	<td><form:select path="clientId" items="${clients}" itemLabel="name" itemValue="id"/></td>
                            </tr>
							<tr>
                            	<td><form:label path="productId"><spring:message code="projects.projectProduct"/></form:label></td>
                            	<td><form:select path="productId" items="${products}" itemLabel="name" itemValue="id" /></td>
                            </tr>
                            <tr>
                            	<td><form:label path="projectTypeId"><spring:message code="projects.ProjectType"/></form:label></td>
                            	<td><form:select path="projectTypeId" items="${projectTypes}" itemLabel="name" itemValue="id" /></td>
                            </tr>
                            <tr>
							    <td><form:label path="projectDate"><spring:message code="projects.projectDate"/></form:label></td>
								<td><form:input path="projectDate" /></td>
							</tr>
							<tr>
								<td><input type="submit" class="btn"
									value=<spring:message code="ushers.add.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>