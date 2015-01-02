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
					<form:form method="post" action="addUsher?${_csrf.parameterName}=${_csrf.token}" modelAttribute="usher" enctype="multipart/form-data">
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
								<td><form:input path="firstName" /></td>
							</tr>
							<tr>
							    <td><form:label path="middleName"><spring:message code="usher.middleName"/></form:label></td>
								<td><form:input path="middleName"  /></td>
							</tr>
							<tr>
							    <td><form:label path="lastName"><spring:message code="usher.lastName"/></form:label></td>
								<td><form:input path="lastName" /></td>
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
							    <td><form:label path="numberOfKids"><spring:message code="usher.numberOfKids"/></form:label></td>
								<td><form:input path="numberOfKids"  /></td>
							</tr>
							<tr>
                            	<td><form:label path="gender"><spring:message code="usher.gender"/></form:label></td>
                            	<td><form:select path="gender" items="${gender}" /></td>
                            </tr>
                            <tr>
							    <td><form:label path="birthDate"><spring:message code="usher.birthDate"/></form:label></td>
								<td><form:input path="birthDate" /></td>
							</tr>
							 <tr>
							    <td><form:label path="age"><spring:message code="usher.age"/></form:label></td>
								<td><form:input path="age"  /></td>
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
                            	<td><form:label path="preferredShift"><spring:message code="usher.preferredShift"/></form:label></td>
                            	<td><form:select path="preferredShift" items="${preferredShifts}"/></td>
                            </tr>
                            <tr>
							    <td><form:label path="mobileNumber"><spring:message code="usher.mobileNumber"/></form:label></td>
								<td><form:input path="mobileNumber" /></td>
							</tr>
							<tr>
							    <td><form:label path="landlineNumber"><spring:message code="usher.landlineNumber"/></form:label></td>
								<td><form:input path="landlineNumber" /></td>
							</tr>
							<tr>
							    <td><form:label path="height"><spring:message code="usher.height"/></form:label></td>
								<td><form:input path="height" /></td>
							</tr>
							<tr>
							    <td><form:label path="weight"><spring:message code="usher.weight"/></form:label></td>
								<td><form:input path="weight" /></td>
							</tr>
                            <tr>
                            	<td><form:label path="shirtSize"><spring:message code="usher.shirtSize"/></form:label></td>
                            	<td><form:select path="shirtSize" items="${shirtSizes}"/></td>
                            </tr>
                            <tr>
                            	<td><form:label path="pantsSize"><spring:message code="usher.pantsSize"/></form:label></td>
                            	<td><form:select path="pantsSize" items="${pantsSizes}"/></td>
                            </tr>
                            <tr>
                            	<td><form:label path="hairType"><spring:message code="usher.hairType"/></form:label></td>
                            	<td><form:select path="hairType" items="${hairTypes}"/></td>
                            </tr>
                            <tr>
                            	<td><form:label path="languages"><spring:message code="usher.languages"/></form:label></td>
                            	<td><form:select path="languages" items="${languages}" multiple="true" /></td>
                            </tr>
                            <tr>
							    <td><form:label path="referredBy"><spring:message code="usher.referredBy"/></form:label></td>
								<td><form:input path="referredBy" /></td>
							</tr>
							<tr>
							    <td><form:label path="university"><spring:message code="usher.university"/></form:label></td>
								<td><form:input path="university" /></td>
							</tr>
							<tr>
                            	<td><form:label path="universityDegree"><spring:message code="usher.universityDegree"/></form:label></td>
                            	<td><form:select path="universityDegree" items="${universityDegrees}"/></td>
                            </tr>
                            <tr>
							    <td><form:label path="graduationYear"><spring:message code="usher.graduationYear"/></form:label></td>
								<td><form:input path="graduationYear" /></td>
							</tr>
							<tr>
							    <td><form:label path="school"><spring:message code="usher.school"/></form:label></td>
								<td><form:input path="school" /></td>
							</tr>
							<tr>
							    <td><form:label path="facebookAccount"><spring:message code="usher.facebookAccount"/></form:label></td>
								<td><form:input path="facebookAccount" /></td>
							</tr>
							<tr>
							    <td><form:label path="emailAddress"><spring:message code="usher.emailAddress"/></form:label></td>
								<td><form:input path="emailAddress" /></td>
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
								<td><form:input path="socialInsuranceDate" /></td>
							</tr>
							<tr>
                            	<td><form:label path="socialInsuranceForm6"><spring:message code="usher.socialInsuranceForm6"/></form:label></td>
                            	<td><form:select path="socialInsuranceForm6" items="${socialInsuranceForm6}"/></td>
                            </tr>
                            <tr>
							    <td><form:label path="socialInsuranceExitDate"><spring:message code="usher.socialInsuranceExitDate"/></form:label></td>
								<td><form:input path="socialInsuranceExitDate"  /></td>
							</tr>
							<tr>
							    <td><form:label path="nationalIdNumber"><spring:message code="usher.nationalIdNumber"/></form:label></td>
								<td><form:input path="nationalIdNumber"  /></td>
							</tr>
							<tr>
							    <td><form:label path="additionalInformation"><spring:message code="usher.additionalInformation"/></form:label></td>
								<td><form:input path="additionalInformation" /></td>
							</tr>
							<tr>
                            	<td><form:label path="rate"><spring:message code="usher.rate"/></form:label></td>
                            	<td><form:select path="rate" items="${rates}"/></td>
                            </tr>
                             <tr>
							    <td><form:label path="photo1"><spring:message code="usher.photo1"/></form:label></td>
								<td><input type="file" name="file"></td>
							</tr>
							<tr>
							    <td><form:label path="photo2"><spring:message code="usher.photo2"/></form:label></td>
								<td><input type="file" name="file"></td>
							</tr>
							<tr>
							    <td><form:label path="photo3"><spring:message code="usher.photo3"/></form:label></td>
								<td><input type="file" name="file"></td>
							</tr>
							<tr>
							    <td><form:label path="photo4"><spring:message code="usher.photo4"/></form:label></td>
								<td><input type="file" name="file"></td>
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