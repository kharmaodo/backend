<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/pro_drop_1.css"/>" />
<script src="<c:url value="js/stuHover.js"/>" type="text/javascript"></script>
<script type="text/javascript">
function logout() {
	if(confirm("確定要登出？")) {
		window.location = 'logout.htm';
		return true;
	} else {
		return false;
	}
}
</script>

<ul id="nav">
	<!-- 首頁 -->
	<li class="top"><a href="<c:url value="home.htm?act=task"/>" class="top_link"><span><spring:message code="text.home"/></span></a></li>
	
	<!-- 系統管理 4 = ao --> 
 	<c:if test="${validated_user.groupId eq '4'}">
 	<li class="top"><a href="<c:url value="docmem.htm?act=list"/>" class="top_link"><span class="down"><spring:message code="text.system.admin"/></span></a>
 		<ul class="sub">
			<li><a href="<c:url value="docmem.htm?act=list"/>"><spring:message code="text.member.management"/></a></li>
			<li><a href="<c:url value="member.htm?act=listMT"/>"><spring:message code="text.mt.member.management"/></a></li>
			<li><a href="<c:url value="news.htm?act=list"/>"><spring:message code="text.news.management"/></a></li>
			<li><a href="<c:url value="log.htm?act=list"/>"><spring:message code="text.log.management"/></a></li>
		</ul>
	</li>
	</c:if>
	
	
	<!-- CI審核管理 4/2 = ao/po--> 
	<c:if test="${validated_user.groupId eq '4' || validated_user.groupId eq '2'}"> <!-- AO/PO角色 -->
 	<li class="top"><a href="<c:url value="ciapp.htm?act=list"/>" class="top_link"><span class="down"><spring:message code="text.ciapply.management"/></span></a>
 		<ul class="sub">
			<li><a href="<c:url value="ciapp.htm?act=list"/>"><spring:message code="text.ciapply.ci"/></a></li>
			<c:if test="${validated_user.groupId eq '4'}">    <!-- AO角色 -->
				<li><a href="<c:url value="imgapp.htm?act=listImg"/>"><spring:message code="text.ciapply.img"/></a></li>
				<li><a href="<c:url value="mapp.htm?act=list"/>"><spring:message code="text.mapp.manage"/></a></li>
				<li><a href="<c:url value="eventApp.htm?act=doListAppResult"/>"><spring:message code="event.app.menu"/></a></li>
				<li><a href="<c:url value="industryNewsApp.htm?act=doListAppResult"/>"><spring:message code="industryNews.app.menu"/></a></li>
				<li><a href="<c:url value="ciapp.htm?act=report"/>"><spring:message code="ci.report"/></a></li>
				<li><a href="<c:url value="prop.htm?act=list"/>"><spring:message code="text.prop.manage"/></a></li>
			</c:if>
		</ul>
	</li>
	</c:if>
	
	<!-- 會展卡專區 -->
	<!--
	<c:if test="${validated_user.groupId eq '5' || validated_user.groupId eq '2'}">	  	
		<li class="top"><a href="<c:url value="card.htm?act=query"/>" class="top_link"><span class="down"><spring:message code="text.meetcard"/></span></a>
			<ul class="sub">
				<!-- vendor --
			  	<c:if test="${validated_user.groupId eq '5'}"> 
	 				<li><a href="<c:url value="card.htm?act=list"/>"><spring:message code="text.card.approve.management"/></a></li>
					<li><a "<c:url value="card.htm?act=query"/>"><spring:message code="text.card.query.management"/></a></li>
			  	</c:if>
			
				<!-- po --
			  	<c:if test="${validated_user.groupId eq '2'}">
	 				<li><a href="<c:url value="vendor.htm?act=list"/>"><spring:message code="text.vendor.management"/></a></li>
					<li><a href="<c:url value="conference.htm?act=list"/>"><spring:message code="text.conference.management"/></a></li>
					<li><a href="<c:url value="card.htm?act=query"/>"><spring:message code="text.card.query.management"/></a></li>
			  	</c:if>
			</ul>
		</li>
	</c:if>
	-->
	
	<!-- 文件管理 1/4/7 = 一般/ao/boft --> 	
	<li class="top"><a class="top_link"><span class="down"><spring:message code="text.document.management"/></a></span>
		<ul class="sub">
			<c:if test="${validated_user.groupId eq '4'}">
				<li><a href="<c:url value="category.htm?act=list"/>"><spring:message code="text.category.management"/></a></li>
				<li><a href="<c:url value="doc.htm?act=listByVerified"/>"><spring:message code="text.document.management"/></a></li>
			</c:if>
			<c:if test="${validated_user.groupId eq '1' || validated_user.groupId eq '2'}">
				<li><a href="<c:url value="doc.htm?act=listByOwnerId"/>"><spring:message code="text.document.management"/></a></li>
			</c:if>
			<c:if test="${validated_user.groupId eq '7'}">
				<li><a href="<c:url value="doc.htm?act=listByBOFT"/>"><spring:message code="text.document.management"/></a></li>
			</c:if>
		</ul>
	</li>
	
	<!-- 內容管理 4 = ao --> 
 	<c:if test="${validated_user.groupId eq '4' || validated_user.groupId eq '2' || validated_user.groupId eq '7'}">
 	<li class="top"><a href="<c:url value="video.htm?act=list"/>" class="top_link"><span class="down"><spring:message code="text.content.admin"/></span></a>
 		<ul class="sub">
 				<li><a href="<c:url value="project.htm?act=list"/>"><spring:message code="text.project"/></a></li>
 				<li><a href="<c:url value="venue.htm?act=list"/>"><spring:message code="text.venue"/></a></li>
 			<c:if test="${validated_user.groupId eq '4'}">
				<li><a href="<c:url value="video.htm?act=list"/>"><spring:message code="text.video.expo"/></a></li>
				<li><a href="<c:url value="case.htm?act=list"/>"><spring:message code="text.case.study"/></a></li>
				<li><a href="<c:url value="industryNews.htm?act=list"/>"><spring:message code="text.industry.news.manager"/></a></li>
				<li><a href="<c:url value="clip.htm?act=list"/>"><spring:message code="text.clip.manager"/></a></li>
				<li><a href="<c:url value="event.htm?act=list"/>"><spring:message code="event.menu"/></a></li>
				<li><a href="<c:url value="pr.htm?act=list"/>"><spring:message code="press.release.menu"/></a></li>
				<li><a href="<c:url value="ann.htm?act=list"/>"><spring:message code="announcement.menu"/></a></li>
				<li><a href="<c:url value="epaper.htm?act=list"/>"><spring:message code="epaper.menu"/></a></li>
				<li><a href="<c:url value="oversea.htm?act=list"/>"><spring:message code="oversea.menu"/></a></li>
			</c:if>
		</ul>
	</li>
	</c:if>
	
	<!-- 個人資訊 -->
	<li class="top"><a href="<c:url value="private.htm?act=edit"/>" id="services" class="top_link"><span class="down"><spring:message code="text.personal"/></span></a>
		<ul class="sub">
			<li><a href="<c:url value="private.htm?act=edit"/>"><spring:message code="text.personal.management"/></a></li>
			<li><a href="<c:url value="private.htm?act=changePassword"/>"><spring:message code="text.change.password"/></a></li>
		</ul>
	</li>
	
	<!-- 登出 -->
	<li class="top"><a href="#" id="services" class="top_link" onclick="return logout()"><span><spring:message code="text.logout"/></span></a></li>
	<li class="top"><a id="services" class="top_link"><span><spring:message code="text.last.login"/> : <fmt:formatDate pattern="yyyy-MM-dd HH:mm:ss" value="${lastLogin.createTime}" /></a></span></li>
</ul>

<div style="background-color: #FFFFFF; color: #000000;">
${navi}
</div>