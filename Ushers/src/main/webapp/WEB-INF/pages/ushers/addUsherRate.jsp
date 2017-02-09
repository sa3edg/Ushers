<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER')">
				<div style="padding: 30px;">
					<form:form method="post" action="addUsher?${_csrf.parameterName}=${_csrf.token}" modelAttribute="usher" enctype="multipart/form-data">
					   
						<table class="reset_table mynew_style">
						     <tr>
						   	<td><form:hidden path="id" /></td>
						   </tr>
							<tr>
							    <td><form:label class="required" path="usherCode"><spring:message code="usherRate.usherCode"/></form:label></td>
								<td><form:select path="usherCode" items="${usherTypes}" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="projectCode"><spring:message code="usherRate.project"/></form:label></td>
								<td><form:select path="projectCode" items="${ushersCalibers}" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="productId"><spring:message code="usherRate.product"/></form:label></td>
								<td><form:select path="productId" items="${ushersCalibers}" /></td>
							</tr>
							<tr>
							    <td><form:label class="required" path="projectTypeId"><spring:message code="usherRate.projectType"/></form:label></td>
								<td><form:select path="projectTypeId" items="${ushersCalibers}" /></td>
							</tr>
							<tr>
							    <td><form:label path="clientFeedback"><spring:message code="usherRate.clientFeedback"/></form:label></td>
								<td><form:input path="clientFeedback" styleId="clientFeedback" /></td>
							</tr>
							<tr>
							    <td><form:label path="supervisorFeedback"><spring:message code="usherRate.supervisorFeedback"/></form:label></td>
								<td><form:input path="supervisorFeedback"  styleId="supervisorFeedback" /></td>
							</tr>
							<tr>
							    <td><form:label path="usherCoordinatorFeedback"><spring:message code="usherRate.usherCoordinatorFeedback"/></form:label></td>
								<td><form:input path="usherCoordinatorFeedback" styleId="usherCoordinatorFeedback" /></td>
							</tr>
							
							<tr>
                            	<td><form:label path="rate"><spring:message code="usherRate.rate"/></form:label></td>
                            	<td><form:select path="rate" items="${rates}"/></td>
                            </tr>
							<tr>
								<td><input type="submit" class="btn" onClick="return validateUsher('${validateLanguage}', '${validateFirstName}', '${validateSecondName}', '${validateLastName}'
								, '${validateUnvirsity}', '${validateAge}', '${validateKidsNumber}', '${validateNationalNumber}', '${validateBirthdate}', '${validateInsuranceDate}', '${validateInsuranceExitDate}', '${validatePhonee}' 
								, '${validateMobile}', '${validateEmail}', '${validateYear}');"
									value=<spring:message code="ushers.add.btn" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>