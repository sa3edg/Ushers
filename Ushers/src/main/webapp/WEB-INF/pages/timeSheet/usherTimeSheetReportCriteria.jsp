<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%> 
<script>
    function popup() {
        window.open('', 'window', 'width=600,height=600');
        var myForm = document.getElementById('criteriaForm');
        myForm.target = 'window';
    }
</script>
<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
		<div class="body">
			<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER', 'ROLE_USHER', 'ROLE_DATAENTRY')">
				<div style="text-align: center; padding: 30px; width: 850px;">
					<form:form id="criteriaForm" method="post" action="handleUsherTimeSheetReportActions?${_csrf.parameterName}=${_csrf.token}" modelAttribute="UsherTimeSheetCriteria">
						<table>
							 <tr>
                            	<td><form:label path="locationType"><spring:message code="timesheet.usher.projectLocation"/></form:label></td>
                            	<td><form:select path="locationType" items="${types}" id = "type"/></td>
                            </tr>
							<tr>
                            	<td><form:label path="projectCode"><spring:message code="timesheet.usher.project"/></form:label></td>
                            	<td><form:select path="projectCode" items="${projects}" itemLabel="projectName" itemValue="projectCode" /></td>
                            </tr>
                            <tr>
                            	<td><form:label path="fromDate"><spring:message code="timesheet.usher.fromDate"/></form:label></td>
                            	<td><form:select path="fromDate" items="${dates}" /></td>
                            	<td><form:label path="toDate"><spring:message code="timesheet.usher.toDate"/></form:label></td>
                            	<td><form:select path="toDate" items="${dates}" /></td>
                            </tr>
							<tr>
								<td><input type="submit" class="btn" onClick="popup();" name="showReportPopupParam"
									value=<spring:message code="timesheet.usher.btn.createReport" /> /></td>
								<td><input type="submit" class="btn" onClick="this.form.target='_blank';return true;" name="showReportNewWindowParam"
									value=<spring:message code="timesheet.usher.btn.createReport" /> /></td>
								<td><input type="submit" class="btn" name="exportReportToPDF"
									value=<spring:message code="timesheet.usher.btn.createReport" /> /></td>
							</tr>
						</table>
					</form:form>
				</div>
			</sec:authorize>
		</div>
	</tiles:putAttribute>
</tiles:insertDefinition>