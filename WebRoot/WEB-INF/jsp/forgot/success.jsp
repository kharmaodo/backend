<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<script type="text/javascript">
function toLogin() {
	location.href="${httpsPrefix}/login.htm";
}
</script>
<center>
 <div>
  	<table class="login">
  		<tr>
  			<td align="center">
				<h1><spring:message code="text.forgot.success" arguments="${member.name}, ${member.account}"/></h1>
			</td>
		</tr>
		<tr>
  			<td align="center">
				<input type="button" value='<spring:message code="text.back.to.login"/>' onClick="javascript:toLogin()">
			</td>
		</tr>
	</table>
</div>
</center>