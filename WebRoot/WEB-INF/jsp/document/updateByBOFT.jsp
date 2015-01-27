<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<!-- JSTL START -->
<c:forEach items="${parent}" var="p">
	<c:set var="groupId"><spring:message code="text.unknow"/></c:set>
	<c:if test="${p.categoryGroupId eq 1}">
		<c:set var="groupId"><spring:message code="text.nonLoginUser"/></c:set>
	</c:if>
	<c:if test="${p.categoryGroupId eq 3}">
		<c:set var="groupId"><spring:message code="text.media"/></c:set>
	</c:if>
	<c:if test="${p.categoryGroupId eq 5}">
		<c:set var="groupId"><spring:message code="text.loginUser"/></c:set>
	</c:if>
	<c:if test="${p.categoryGroupId eq 10}">
		<c:set var="groupId"><spring:message code="text.projectUser"/></c:set>
	</c:if>
	<c:if test="${p.id eq command.parentId}">
		<c:set var="parentCategory">
			${p.description}(${groupId})
		</c:set>
	</c:if>
</c:forEach>

<c:forEach items="${sub}" var="s">
	<c:set var="groupId"><spring:message code="text.unknow"/></c:set>
	<c:if test="${s.categoryGroupId eq 1}">
		<c:set var="groupId"><spring:message code="text.nonLoginUser"/></c:set>
	</c:if>
	<c:if test="${s.categoryGroupId eq 3}">
		<c:set var="groupId"><spring:message code="text.media"/></c:set>
	</c:if>
	<c:if test="${s.categoryGroupId eq 5}">
		<c:set var="groupId"><spring:message code="text.loginUser"/></c:set>
	</c:if>
	<c:if test="${s.categoryGroupId eq 10}">
		<c:set var="groupId"><spring:message code="text.projectUser"/></c:set>
	</c:if>
	<c:if test="${s.id eq command.documentCategoryId}">
		<c:set var="subCategory">
			${s.description}(${groupId})
		</c:set>
	</c:if>
</c:forEach>



<!-- JSTL END -->
<script type='text/javascript' src='<c:url value="dwr/interface/dwrUtil.js"/>'></script>
<script type='text/javascript' src='<c:url value="dwr/engine.js"/>'></script>
<script type='text/javascript' src='<c:url value="dwr/util.js"/>'></script>
<script type="text/javascript">
$(document).ready(function() {
	$('#first_input').focus();
	$('.need').addClass('required');
	$('#result').html('${subCategory}');
});

function getCategory() {
	var parentId = document.getElementById('parentId').value;
    dwrUtil.getCategory(parentId, function(ret){
        if (ret == '0') {
            alert("<spring:message code="text.no.subcategory"/>");
        } else {
        	document.getElementById("result").innerHTML = ret;
        }
    });
}
</script>
<center>
  <form name="documentEditForm" method="POST" action="<c:url value="doc.htm?act=doUpdateByBOFT"/>">
  <div class="login">
  	<table>
  		<tr>
  			<spring:bind path="command.description">
  			<td class="label"><spring:message code="text.en.description"/></td>
  			<td class="field">
  				${status.value}
  				<input type="hidden" name="description" value="${status.value}" size="50" id="first_input"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.parentId">
  			<td class="label"><spring:message code="text.en.parentId"/></td>
  			<td class="field">
  				${parentCategory}
  				<input type="hidden" name="parentId" value="${status.value}" size="50"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.documentCategoryId">
  			<td class="label"><spring:message code="text.en.documentCategoryId"/></td>
  			<td class="field">
  				${subCategory}
  				<input type="hidden" name="documentCategoryId" value="${status.value}" size="50"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.en.attachment.current"/></td>
  			<td class="field">
  				<a href="<c:url value="att.htm?act=download&id=${command.attachmentId}"/>" target="_blank">${command.originNanme}</a>
  			</td>
  			<td>&nbsp;</td>
  		</tr>
  		<tr>
  			<spring:bind path="command.issuuId">
  			<td class="label"><spring:message code="text.en.issuu"/></td>
  			<td class="field">${status.value}<input type="hidden" name="issuuId" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<spring:bind path="command.groupIds">
  			<td class="label"><spring:message code="text.groupIds"/></td>
  			<td class="field">${status.value}<input type="hidden" name="groupIds" value="${status.value}"/></td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		</table>
  	</div>
  	<br/>
  	<div class="login">
  		<table>
  		<tr>
  			<td class="column">&nbsp;</td>
  			<td class="column" align="center"><spring:message code="text.en.topic"/></td>
  			<td class="column" align="center"><spring:message code="text.en.source"/></td>
  			<td class="column" align="center"><spring:message code="text.en.visible"/></td>
  		</tr>
  		<tr>
  			<td class="label"><spring:message code="text.nameTW"/></td>
  			<spring:bind path="command.topicTW">
  			<td>${status.value}<input type="hidden" name="topicTW" value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.sourceTW">
  			<td>${status.value}<input type="hidden" name="sourceTW" value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.visibleTW">
  			<td>
  			    <c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="visibleTW"  value="${status.value}"/>
  			</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="text.nameEN"/></td>
  			<spring:bind path="command.topicEN">
  			<td>${status.value}<input type="hidden" name="topicEN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.sourceEN">
  			<td>${status.value}<input type="hidden" name="sourceEN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.visibleEN">
  			<td>
  				<c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="visibleEN"  value="${status.value}"/>
  			</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="text.nameCN"/></td>
  			<spring:bind path="command.topicCN">
  			<td>${status.value}<input type="hidden" name="topicCN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.sourceCN">
  			<td>${status.value}<input type="hidden" name="sourceCN"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.visibleCN">
  			<td>
  				<c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="visibleCN"  value="${status.value}"/>
  			</td>
  			</spring:bind>
  		</tr>	
  		<tr>
  			<td class="label"><spring:message code="text.nameJP"/></td>
  			<spring:bind path="command.topicJP">
  			<td>${status.value}<input type="hidden" name="topicJP"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.sourceJP">
  			<td>${status.value}<input type="hidden" name="sourceJP"  value="${status.value}"/>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.visibleJP">
  			<td>
  				<c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="visibleJP"  value="${status.value}"/>
  			</td>
  			</spring:bind>
  		</tr>	
  		</table>
  		</div>
  		<br/>
  		<div class="login">
  		<table>
  		<tr>
  			<spring:bind path="command.downloadable">
  			<td class="label"><spring:message code="text.downloadable"/></td>
  			<td>
  				<c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="downloadable"  value="${status.value}"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.copyRight">
  			<td class="label"><spring:message code="text.copyRight"/></td>
  			<td>
  				<c:choose>
	  			    <c:when test="${status.value == 'Y'}">
	  			    	Y
	  			    </c:when>
	  			    <c:otherwise>
	  			    	N
	  			    </c:otherwise>
  			    </c:choose>
  				<input type="hidden" name="copyRight"  value="${status.value}"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
  		<tr>
  			<spring:bind path="command.isPass">
  			<td class="label"><spring:message code="text.isPass"/></td>
  			<td>
  				<input type="radio" name="isPass" value="Y"/><spring:message code="text.yes"/>
  				<input type="radio" name="isPass" value="N"/><spring:message code="text.no"/>
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  			<spring:bind path="command.verifyNote">
  			<td class="label"><spring:message code="text.verifyNote"/></td>
  			<td>
  				<input type="text" name="verifyNote" value="" />
  			</td>
  			<td>${status.errorMessage}</td>
  			</spring:bind>
  		</tr>
	  		<tr>
	  			<td colspan="6" style="background-color: #FFDAB5">
	  				<spring:message code="text.doc.approval.ao"/>，<spring:message code="text.doc.approval.reviewer"/> : ${command.approval1}，<spring:message code="text.doc.approval.review.time"/> :${command.modifyTime} 
	  			</td>
	  		</tr>
  			<tr>
	  			<td colspan="6" style="background-color: #FFDAB5">
	  				<spring:message code="text.verifyNote"/> : ${command.verifyNote}
	  			</td>
	  		</tr>
  			<tr>
  				<spring:bind path="command.documentId"><input type="hidden" name="documentId" value="${status.value}"/></spring:bind>
				<td colspan="6" align="right">
					<input type="submit" value="<spring:message code="text.submit"/>">
					<input type="button" value="<spring:message code="text.back"/>" onClick="window.history.back()">
				</td>
			</tr>
  		</table>
  		</div>
  </form>
</center>
