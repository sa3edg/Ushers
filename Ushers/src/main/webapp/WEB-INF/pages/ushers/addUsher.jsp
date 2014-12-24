<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="ushersTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasRole('ROLE_USHER')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form method="post" action="addUsher" modelAttribute="usher" enctype="multipart/form-data">
					    <c:set var="validateName"><spring:message code="ushers.valid.name" /></c:set>
						<table>
							<tr>
							    <td><form:label path="usherType"><spring:message code="usher.usherType"/></form:label></td>
								<td><form:select path="usherType" items="${usherTypes}" /></td>
							</tr>
							<tr>
							    <td><form:label path="usherCaliber"><spring:message code="usher.usherCaliber"/></form:label></td>
								<td><form:select path="usherCaliber" items="${ushersCalibers}" /></td>
							</tr>
							<tr>
							    <td><form:label path="firstName"><spring:message code="usher.firstName"/></form:label></td>
								<td><form:input path="firstName" styleId="name" id="name" /></td>
							</tr>
							<tr>
							    <td><form:label path="middleName"><spring:message code="usher.middleName"/></form:label></td>
								<td><form:input path="middleName" styleId="name" id="name" /></td>
							</tr>
							<tr>
							    <td><form:label path="lastName"><spring:message code="usher.lastName"/></form:label></td>
								<td><form:input path="lastName" styleId="name" id="name" /></td>
							</tr>
							<tr>
							    <td><form:label path="maritalStatus"><spring:message code="usher.maritalStatus"/></form:label></td>
								<td><form:select path="maritalStatus" items="${maritalStatus}" /></td>
							</tr>
							<tr>
							    <td><form:label path="hasKids"><spring:message code="usher.hasKids"/></form:label></td>
								<td><form:select path="hasKids">
									<form:option value="true" label="Yes"/>
                            	    <form:option value="false" label="No"/>
                            	</form:select></td>
                            </tr>
                            <tr>
                            <td><form:label path="maritalStatus"><spring:message code="usher.languages"/></form:label></td>
                            <td><form:select path="languages" items="${maritalStatus}" multiple="true" /></td>
                            </tr>
                            <tr>
							    <td><form:label path="photo1"><spring:message code="usher.photo1"/></form:label></td>
								<td><form:input path="photo1" type="file" styleId="photo1"/></td>
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