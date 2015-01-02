<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags"
	prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form method="post" action="" modelAttribute="usher">
					<div id="thumbnails">
                    
						<table style="display: inline-block; float: right;">
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="firstName">${usher.firstName}</form:label>
							    <form:label style="font-size: 22px;font-weight: bold;" path="middleName">${usher.middleName}&nbsp;</form:label>
							    <form:label style="font-size: 22px;font-weight: bold;" path="lastName">${usher.lastName}</form:label></td>
							    
							</tr>
							
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="usherCode">${usher.usherCode}</form:label></td>
							</tr>
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="usherType">${usher.usherType}</form:label></td>
							</tr>
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="usherCaliber">${usher.usherCaliber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="mobileNumber">${usher.mobileNumber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="emailAddress">${usher.emailAddress}</form:label></td>
							</tr>
							<tr>
							    <td><form:label style="font-size: 22px;font-weight: bold;" path="rate">${usher.rate}</form:label></td>
							</tr>
						</table>
						
						<ul class="clearfix">
						<table style="float: left;" >
						<tr>
								<td>
									<li><a href="<c:url value='/getUsherImage/${usher.usherCode}/0'/>" > <img src="<c:url value='/getUsherImage/${usher.usherCode}/0'/>" width="320" height="200" border="0" onerror="this.src='<c:url value='/resources/images/default_profile.png'/>'" /> </a></li>
								</td>
							</tr>
							<tr style="float: right;">
								<td><li><a href="<c:url value='/getUsherImage/${usher.usherCode}/1'/>"><spring:message code="usher.photo2" /> </a>&nbsp;</li></td>
								<td><li><a href="<c:url value='/getUsherImage/${usher.usherCode}/2'/>"><spring:message code="usher.photo3" /> </a>&nbsp;</li></td>
								<td><li><a href="<c:url value='/getUsherImage/${usher.usherCode}/3'/>"><spring:message code="usher.photo4" /> </a>&nbsp;</li></td>
							</tr>
							</table>
							</ul>
      						</div>
      						
      						<table class="usher">
                           <tbody>
   						   <tr>
   						    	<td><form:label path="maritalStatus"><spring:message code="usher.maritalStatus"/></form:label></td>
      							<td><form:label path="rate">${usher.maritalStatus}</form:label></td>
    						</tr>
    						<tr>
							    <td><form:label path="hasKids"><spring:message code="usher.hasKids"/></form:label></td>
								<td><form:label path="rate">${usher.rate}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="numberOfKids"><spring:message code="usher.numberOfKids"/></form:label></td>
								<td><form:label path="numberOfKids">${usher.numberOfKids}</form:label></td>
							</tr>
							<tr>
                            	<td><form:label path="gender"><spring:message code="usher.gender"/></form:label></td>
                            	<td><form:label  path="gender">${usher.gender}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="birthDate"><spring:message code="usher.birthDate"/></form:label></td>
								<td><form:label  path="birthDate">${usher.birthDate}</form:label></td>
							</tr>
							 <tr>
							    <td><form:label path="age"><spring:message code="usher.age"/></form:label></td>
								<td><form:label  path="age">${usher.age}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="address"><spring:message code="usher.address"/></form:label></td>
								<td><form:label  path="address">${usher.address}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="appartmentNumber"><spring:message code="usher.appartmentNumber"/></form:label></td>
								<td><form:label path="appartmentNumber">${usher.appartmentNumber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="street"><spring:message code="usher.street"/></form:label></td>
								<td><form:label  path="street">${usher.street}</form:label></td>
							</tr>
							 <tr>
                            	<td><form:label path="area"><spring:message code="usher.area"/></form:label></td>
                            	<td><form:label path="area">${usher.area}</form:label></td>
                            </tr>
							<tr>
                            	<td><form:label path="governorate"><spring:message code="usher.governorate"/></form:label></td>
                            	<td><form:label  path="governorate">${usher.governorate}</form:label></td>
                            </tr>
                            <tr>
                            	<td><form:label path="preferredLocation"><spring:message code="usher.preferredLocation"/></form:label></td>
                            	<td><form:label  path="preferredLocation">${usher.preferredLocation}</form:label></td>
                            </tr>
                            <tr>
                            	<td><form:label path="preferredShift"><spring:message code="usher.preferredShift"/></form:label></td>
                            	<td><form:label  path="preferredShift">${usher.preferredShift}</form:label></td>
                            </tr>
                            
							<tr>
							    <td><form:label path="landlineNumber"><spring:message code="usher.landlineNumber"/></form:label></td>
								<td><form:label  path="landlineNumber">${usher.landlineNumber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="height"><spring:message code="usher.height"/></form:label></td>
								<td><form:label  path="height">${usher.height}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="weight"><spring:message code="usher.weight"/></form:label></td>
								<td><form:label  path="weight">${usher.weight}</form:label></td>
							</tr>
                            <tr>
                            	<td><form:label path="shirtSize"><spring:message code="usher.shirtSize"/></form:label></td>
                            	<td><form:label  path="shirtSize">${usher.shirtSize}</form:label></td>
                            </tr>
                            <tr>
                            	<td><form:label path="pantsSize"><spring:message code="usher.pantsSize"/></form:label></td>
                            	<td><form:label  path="pantsSize">${usher.pantsSize}</form:label></td>
                            </tr>
                            <tr>
                            	<td><form:label path="hairType"><spring:message code="usher.hairType"/></form:label></td>
                            	<td><form:label  path="hairType">${usher.hairType}</form:label></td>
                            </tr>
                            <tr>
                            	<td><form:label path="languages"><spring:message code="usher.languages"/></form:label></td>
                            	<td><form:label  path="languages">${usher.languages}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="referredBy"><spring:message code="usher.referredBy"/></form:label></td>
								<td><form:label  path="referredBy">${usher.referredBy}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="university"><spring:message code="usher.university"/></form:label></td>
								<td><form:label  path="university">${usher.university}</form:label></td>
							</tr>
							<tr>
                            	<td><form:label path="universityDegree"><spring:message code="usher.universityDegree"/></form:label></td>
                            	<td><form:label  path="universityDegree">${usher.universityDegree}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="graduationYear"><spring:message code="usher.graduationYear"/></form:label></td>
								<td><form:label  path="graduationYear">${usher.graduationYear}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="school"><spring:message code="usher.school"/></form:label></td>
								<td><form:label  path="school">${usher.school}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="facebookAccount"><spring:message code="usher.facebookAccount"/></form:label></td>
								<td><form:label  path="facebookAccount">${usher.facebookAccount}</form:label></td>
							</tr>
							
							<tr>
                            	<td><form:label path="socialInsurance"><spring:message code="usher.socialInsurance"/></form:label></td>
                            	<td><form:label  path="socialInsurance">${usher.socialInsurance}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="socialInsuranceNumber"><spring:message code="usher.socialInsuranceNumber"/></form:label></td>
								<td><form:label  path="socialInsuranceNumber">${usher.socialInsuranceNumber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="socialInsuranceDate"><spring:message code="usher.socialInsuranceDate"/></form:label></td>
								<td><form:label  path="socialInsuranceDate">${usher.rate}</form:label></td>
							</tr>
							<tr>
                            	<td><form:label path="socialInsuranceForm6"><spring:message code="usher.socialInsuranceForm6"/></form:label></td>
                            	<td><form:label  path="socialInsuranceForm6">${usher.socialInsuranceForm6}</form:label></td>
                            </tr>
                            <tr>
							    <td><form:label path="socialInsuranceExitDate"><spring:message code="usher.socialInsuranceExitDate"/></form:label></td>
								<td><form:label  path="socialInsuranceExitDate">${usher.socialInsuranceExitDate}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="nationalIdNumber"><spring:message code="usher.nationalIdNumber"/></form:label></td>
								<td><form:label  path="nationalIdNumber">${usher.nationalIdNumber}</form:label></td>
							</tr>
							<tr>
							    <td><form:label path="additionalInformation"><spring:message code="usher.additionalInformation"/></form:label></td>
								<td><form:label  path="additionalInformation">${usher.additionalInformation}</form:label></td>
							</tr>
  							</tbody>
							</table>
					</form:form>
					<script type="text/javascript">
						$(function() {
   							 $('#thumbnails a').lightBox();
						});
					</script>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>