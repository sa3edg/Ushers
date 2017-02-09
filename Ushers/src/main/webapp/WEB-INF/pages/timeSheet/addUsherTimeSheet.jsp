<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER', 'ROLE_DATAENTRY')">
				<div style="padding: 30px;">
					<form:form method="post" action="addUsherTimeSheet" modelAttribute="UsherTimeSheet">
					    <c:set var="validateName"><spring:message code="ushers.valid.name" /></c:set>
						<table>
						    <tr>
						   	<td><form:hidden path="id" /></td>
						   </tr>
							 <tr>
                            	<td><form:label path="projectLocationId"><spring:message code="timesheet.usher.projectLocation"/></form:label></td>
                            	<td><form:select path="projectLocationId" style="width: 150px;" items="${projectsLocations}" itemLabel="name" itemValue="id"/></td>
                            </tr>
							<tr>
                            	<td><form:label path="projectCode"><spring:message code="timesheet.usher.project"/></form:label></td>
                            	<td><form:select path="projectCode" style="width: 150px;" items="${projects}" itemLabel="projectName" itemValue="projectCode" /></td>
                            </tr>
                            <tr>
                            	<td><form:label path="usherCode"><spring:message code="timesheet.usher.usher"/></form:label></td>
                            	<td><form:select path="usherCode" style="width: 150px;">
                            	    <c:forEach var="theUsher" items="${ushers}">
        								<!--<form:option value="${theUsher.usherCode}"><c:out value="${theUsher.firstName} ${theUsher.middleName} ${theUsher.lastName}"/></form:option>-->
    									<form:option value="${theUsher.usherCode}"><c:out value="${theUsher.usherCode}"/></form:option>
    								</c:forEach>
									</form:select>
                            	</td>
                            </tr>
                            <tr>
							    <td><form:label path="date"><spring:message code="timesheet.usher.date"/></form:label></td>
								<td><form:input path="date"  style="width: 150px;"/></td>
								<td><form:input path="date1" style="width: 85px;"/></td>
								<td><form:input path="date2" style="width: 85px;"/></td>
								<td><form:input path="date3" style="width: 85px;"/></td>
								<td><form:input path="date4" style="width: 85px;"/></td>
								<td><form:input path="date5" style="width: 85px;"/></td>
								<td><form:label path="date"><spring:message code="usher.dateFormat"/></form:label></td>
							</tr>
							 <tr>
							    <td><form:label path="daySalary"><spring:message code="timesheet.usher.daySalary"/></form:label></td>
								<td><form:input path="daySalary"  style="width: 150px;" type="number" min="0" max="999" /></td>
							</tr>
							 <tr>
							    <td><form:label path="deduction"><spring:message code="timesheet.usher.deduction"/></form:label></td>
								<td><form:input path="deduction"  style="width: 150px;" type="number" min="0.0" max="1.0" step="0.01" /></td>
							</tr>
							 <tr>
							    <td><form:label path="debit"><spring:message code="timesheet.usher.debit"/></form:label></td>
								<td><form:input path="debit" style="width: 150px;" type="number" min="0" max="999" /></td>
							</tr>
							<tr>
                            	<td><form:label path="uniformDelivered"><spring:message code="timesheet.usher.uniform"/></form:label></td>
                            	<td><form:select path="uniformDelivered" style="width: 150px;" items="${uniform}"/></td>
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