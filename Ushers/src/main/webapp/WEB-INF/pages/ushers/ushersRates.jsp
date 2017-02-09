<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@ taglib prefix="display" uri="http://displaytag.sf.net"%>
<%@taglib prefix="sec"
	uri="http://www.springframework.org/security/tags"%>


<tiles:insertDefinition name="defaultTemplate">
	<tiles:putAttribute name="body">
	   
		<div class="body">
		<c:set var="deleteConfirmation"><spring:message code="ushers.delecteConfirmation" /></c:set>
		         <script type="text/javascript">
        $(function () {
			$('table').footable();

					$('#change-page-size').change(function (e) {
						e.preventDefault();
						var pageSize = $(this).val();
						$('.footable').data('page-size', pageSize);
						$('.footable').trigger('footable_initialized');
					});

					$('#change-nav-size').change(function (e) {
						e.preventDefault();
						var navSize = $(this).val();
						$('.footable').data('limit-navigation', navSize);
						$('.footable').trigger('footable_initialized');
					});
        });
        
        $(function () {
            $('table').footable().bind('footable_filtering', function (e) {
              var selected = $('.filter-status').find(':selected').text();
              if (selected && selected.length > 0) {
                e.filter += (e.filter && e.filter.length > 0) ? ' ' + selected : selected;
                e.clear = !e.filter;
              }
            });

            $('.clear-filter').click(function (e) {
              e.preventDefault();
              $('.filter-status').val('');
              $('table.demo').trigger('footable_clear_filter');
            });

            $('.filter-status').change(function (e) {
              e.preventDefault();
              $('table.demo').trigger('footable_filter', {filter: $('#filter').val()});
            });

            $('.filter-api').click(function (e) {
              e.preventDefault();

              //get the footable filter object
              var footableFilter = $('table').data('footable-filter');

              alert('about to filter table by "tech"');
              //filter by 'tech'
              footableFilter.filter('tech');

              //clear the filter
              if (confirm('clear filter now?')) {
                footableFilter.clearFilter();
              }
            });
          });
        </script>
        <c:if test="${not empty ushers}">
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER','ROLE_USHER')">
        <table>
	    	<tr>
            	<form:label><spring:message code="ushers.search"/></form:label> <input id="filter" type="text"/></br>
            </tr>
            <tr>
                <td>
                <form:label><spring:message code="ushers.pageSize"/></form:label>
				<select id="change-page-size">
					<option value="2">2</option>
					<option value="3">3</option>
                    <option value="5">5</option>
                    <option value="10">10</option>
                </select>
                </td>
            	<td>
            	<form:label><spring:message code="ushers.navSize"/></form:label>
                <select id="change-nav-size">
					<option value="0">None</option>
					<option value="2">2</option>
					<option value="3">3</option>
					<option value="4">4</option>
				</select>
				
				</td>
				
                </tr>
				</table>
				<table  class="table demo" data-page-size="5" data-filter="#filter" data-filter-text-only="true">
					<thead>
						<tr>
							<th data-toggle="true">
								<form:label><spring:message code="usher.usherCode"/></form:label>
							</th>
							<th>
								<form:label><spring:message code="usher.firstName"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="usher.middleName"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="usher.lastName"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="usher.mobileNumber"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="ushers.view"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="ushers.edit"/></form:label>
							</th>
							<th >
								<form:label><spring:message code="ushers.delete"/></form:label>
							</th>
						</tr>
					</thead>
					<tbody>
						 <c:forEach var="item" items="${ushers}">
            				<tr>
                				<td>${item.usherCode}</td>
                				<td>${item.firstName}</td>
                				<td>${item.middleName}</td>
                				<td>${item.lastName}</td>
                				<td>${item.mobileNumber}</td>
                				<td><a href="<c:url value="/viewUsher?id=${item.id}" />">
				        			<img src="<c:url value='/resources/images/profile.png'/>" width="30" height="30"  border="0" alt="View"> </a></td>
				        		<td><a href="<c:url value="/editUsher?id=${item.id}" />">
				        			<img src="<c:url value='/resources/images/edit.png'/>" width="30" height="30"  border="0" alt="Edit"> </a></td>
				        		<sec:authorize access="hasRole('ROLE_ADMIN')">
				        		<td><a href="<c:url value="/deleteUsher?id=${item.id}" />"
				        			onClick="return confirmDelete('${deleteConfirmation}');"><img src="<c:url value='/resources/images/delete.png'/>" width="30" height="30"  border="0" alt="Delete"> </a></td>
				        		</sec:authorize>
            				</tr>
        				</c:forEach>
					</tbody>
					<tfoot>
						<tr>
							<td colspan="5">
								<div class="pagination pagination-centered"></div>
							</td>
						</tr>
					</tfoot>
				</table>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER','ROLE_USHER')">
        	    <a href="<c:url value="addUsherForm" />"><spring:message
						code="usher.addUsher" /></a></br>
				<a href="<c:url value="importCSVForm" />"><spring:message
						code="usher.importCSVFile" /></a>
                </sec:authorize>
				<sec:authorize access="hasAnyRole('ROLE_ADMIN', 'ROLE_SUPER_USER')">
					<c:if test="${not empty ushers}">
					<br>
					<a href="<c:url value="exportUshers" />"><spring:message
						code="usher.exportToExcel" /></a>
					</c:if>
					</sec:authorize>
			</div>
			</sec:authorize>
		   </c:if>
		   
	</tiles:putAttribute>
</tiles:insertDefinition>