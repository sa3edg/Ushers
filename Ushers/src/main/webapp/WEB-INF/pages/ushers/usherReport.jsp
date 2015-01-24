<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form method="post" action="exportReport?${_csrf.parameterName}=${_csrf.token}" modelAttribute="usherReport" enctype="multipart/form-data">
						<table>
							<tr>
							    <td><form:label path="fileType"><spring:message code="report.fileType"/></form:label></td>
								<td><form:select path="fileType" items="${fileTypes}" /></td>
							</tr>
							<tr>
							    <td><form:label path="usherType"><spring:message code="usher.usherType"/></form:label></td>
								<td><form:select path="usherType" items="${usherTypes}" /></td>
							</tr>
							<tr>
							    <td><form:label path="usherCaliber"><spring:message code="usher.usherCaliber"/></form:label></td>
								<td><form:select path="usherCaliber" items="${ushersCalibers}" /></td>
							</tr>
							<tr>
							    <td><form:label path="usherCode"><spring:message code="usher.usherCode"/></form:label></td>
								<td><form:input path="usherCode" /></td>
							</tr>
							<tr>
							    <td><form:label path="firstName"><spring:message code="usher.firstName"/></form:label></td>
								<td><form:input path="firstName" styleId="firstName" /></td>
							</tr>
							<tr>
							    <td><form:label path="middleName"><spring:message code="usher.middleName"/></form:label></td>
								<td><form:input path="middleName"  styleId="middleName" /></td>
							</tr>
							<tr>
							    <td><form:label path="lastName"><spring:message code="usher.lastName"/></form:label></td>
								<td><form:input path="lastName" styleId="lastName" /></td>
							</tr>
							<tr>
                            	<td><form:label path="gender"><spring:message code="usher.gender"/></form:label></td>
                            	<td><form:select path="gender" items="${gender}" /></td>
                            </tr>
							<tr>
							    <td><form:label path="address"><spring:message code="usher.address"/></form:label></td>
								<td><form:input path="address"  /></td>
							</tr>
							<tr>
							    <td><form:label path="appartmentNumber"><spring:message code="usher.appartmentNumber"/></form:label></td>
								<td><form:input path="appartmentNumber" /></td>
							</tr>
							<tr>
							    <td><form:label path="street"><spring:message code="usher.street"/></form:label></td>
								<td><form:input path="street" /></td>
							</tr>
							 <tr>
                            	<td><form:label path="area"><spring:message code="usher.area"/></form:label></td>
                            	<td><form:select path="area" items="${areas}" itemLabel="name" itemValue="id"/></td>
                            </tr>
							<tr>
                            	<td><form:label path="governorate"><spring:message code="usher.governorate"/></form:label></td>
                            	<td><form:select path="governorate" items="${governorates}" itemLabel="name" itemValue="id" /></td>
                            </tr>
                            <tr>
                            	<td><form:label path="preferredLocation"><spring:message code="usher.preferredLocation"/></form:label></td>
                            	<td><form:select path="preferredLocation" items="${preferredLocations}" itemLabel="name" itemValue="id" /></td>
                            </tr>
                            <tr>
                            	<td><form:label path="languages"><spring:message code="usher.languages"/></form:label></td>
                            	<td><form:select path="languages" items="${languages}" multiple="true" styleId="languages"/></td>
                            </tr>
							<tr>
							    <td><form:label path="university"><spring:message code="usher.university"/></form:label></td>
								<td><form:input path="university" styleId="university"/></td>
							</tr>
							<tr>
                            	<td><form:label path="socialInsurance"><spring:message code="usher.socialInsurance"/></form:label></td>
                            	<td><form:select path="socialInsurance" items="${socialInsurance}"/></td>
                            </tr>
                            <tr>
							    <td><form:label path="socialInsuranceNumber"><spring:message code="usher.socialInsuranceNumber"/></form:label></td>
								<td><form:input path="socialInsuranceNumber" /></td>
							</tr>
							<tr>
							    <td><form:label path="socialInsuranceDate"><spring:message code="usher.socialInsuranceDate"/></form:label></td>
								<td><form:input path="socialInsuranceDate" styleId="socialInsuranceDate"/></td>
								<td><form:label path="socialInsuranceDate"><spring:message code="usher.dateFormat"/></form:label></td>
							</tr>
							<tr>
                            	<td><form:label path="socialInsuranceForm6"><spring:message code="usher.socialInsuranceForm6"/></form:label></td>
                            	<td><form:select path="socialInsuranceForm6" items="${socialInsuranceForm6}"/></td>
                            </tr>
                            <tr>
							    <td><form:label path="socialInsuranceExitDate"><spring:message code="usher.socialInsuranceExitDate"/></form:label></td>
								<td><form:input path="socialInsuranceExitDate"  styleId="socialInsuranceExitDate"/></td>
								<td><form:label path="socialInsuranceExitDate"><spring:message code="usher.dateFormat"/></form:label></td>
							</tr>
							<tr>
                            	<td><form:label path="productId"><spring:message code="projects.projectProduct"/></form:label></td>
                            	<td><form:select path="productId" items="${products}" itemLabel="name" itemValue="id" /></td>
                            </tr>
							<tr>
							    <td><form:label path="projectCode"><spring:message code="projects.projectCode"/></form:label></td>
								<td><form:input path="projectCode" /></td>
							</tr>
							<tr>
							    <td><form:label path="projectName"><spring:message code="projects.projectName"/></form:label></td>
								<td><form:input path="projectName" /></td>
							</tr>
							<tr>
                            	<td><form:label path="projectTypeId"><spring:message code="projects.ProjectType"/></form:label></td>
                            	<td><form:select path="projectTypeId" items="${projectTypes}" itemLabel="name" itemValue="id" /></td>
                            </tr>
							<tr>
								<td><input type="submit" class="btn"
									value=<spring:message code="ushers.report.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>