<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:if test="${command.menuId == 'S001_01_01'}">
	<c:set var="selected_01" value="selected"/>
</c:if>
<c:if test="${command.menuId == 'S001_01_02'}">
	<c:set var="selected_02" value="selected"/>
</c:if>
<c:if test="${command.menuId == 'S001_01_04'}">
	<c:set var="selected_04" value="selected"/>
</c:if>
<c:if test="${command.menuId == 'S001_01_05'}">
	<c:set var="selected_05" value="selected"/>
</c:if>
<c:if test="${command.menuId == 'S001_01_03'}">
	<c:set var="selected_03" value="selected"/>
</c:if>
<!-- JSTL END -->
<script type="text/javascript">
function newProject() {
	var qform = document.getElementById('qform');
	qform.action = '<c:url value="project.htm?act=insert"/>';
	qform.submit();
}
</script>
  <form id="qform" name="projectListForm" method="POST">
  	<table>
  		<tr>
  			<spring:bind path="command.menuId">
  			<td><spring:message code="project.menu.id"/> : </td>
  			<td>
  				<select name="menuId" id="menuId">
  				  	<option value="S001_01_01" ${selected_01}>計畫推手 - 經濟部國際貿易局</option>
  				  	<option value="S001_01_02" ${selected_02}>會展產業整體推動計畫</option>
  				  	<option value="S001_01_04" ${selected_04}>會展推廣與國際行銷計畫</option>
  				  	<option value="S001_01_05" ${selected_05}>會展人才培育與認證計畫</option>
  				  	<option value="S001_01_03" ${selected_03}>爭取國際會議在台舉辦計畫</option>
  				</select>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			
  			<spring:bind path="command.locale">
  				<input id="locale" type="hidden" name="locale" value="zh_TW"/>
  			</spring:bind>
  			<spring:bind path="command.currentPage">
  				<input id="currentPage" type="hidden" name="currentPage" value="${status.value}"/>
  			</spring:bind>
  			
			<td>
				<input type="button" onClick="go('<c:url value="project.htm?act=doList"/>', 1, '')" value="<spring:message code="text.query"/>">
				<input type="button" onClick="newProject()" value="<spring:message code="text.add"/>">
			</td>
		</tr>
  	</table>
  </form>
