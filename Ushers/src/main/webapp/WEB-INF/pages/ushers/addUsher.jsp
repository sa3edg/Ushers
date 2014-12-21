<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="dataEntryTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasRole('ROLE_DATAENTRY')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form method="post" action="addArea" modelAttribute="area" enctype="multipart/form-data">
					    <c:set var="validateName"><spring:message code="ushers.valid.name" /></c:set>
						<table>
							<tr>
							    <td><form:hidden path="id" /></td>
							    <td><form:label path="name"><spring:message code="ushers.name"/></form:label></td>
								<td><form:input path="name" styleId="name" id="name" /></td>
								<td><form:input path="name" type="file" styleId="name" id="name" /></td>
							</tr>
							<tr>
								<td><input type="submit" class="btn" onClick="return addObject('${validateName}');"
									value=<spring:message code="ushers.add.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>