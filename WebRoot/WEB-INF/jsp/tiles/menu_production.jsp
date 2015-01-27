<%@page pageEncoding="UTF-8"%>
<%@include file="/WEB-INF/jsp/common/tags.jsp"%>
<link rel="stylesheet" type="text/css" href="<c:url value="css/menu.css"/>" media="screen"/>
<center>
	<div class="menu">
		<div id="menu">
  			<ul>
  			    <li>${validated_user.name}<spring:message code="text.hello"/></li>
  				<li class="menudeco"></li>
			  	<li><a href="<c:url value="home.htm?act=task"/>"><spring:message code="text.home"/></a></li>
			  	
			  	<!-- vendor -->
			  	<c:if test="${validated_user.groupId eq '5'}">   
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="conference.htm?act=list"/>"><spring:message code="text.conference.management"/></a></li>
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="card.htm?act=list"/>"><spring:message code="text.card.approve.management"/></a></li>
			  	</c:if>
			  	
			  	<!-- po -->
			  	<c:if test="${validated_user.groupId eq '2'}">
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="card.htm?act=query"/>"><spring:message code="text.card.query.management"/></a></li>
			  	</c:if>
			  	
			  	<!-- ao -->
  				<c:if test="${validated_user.groupId eq '4'}">
  				<li class="menudeco"></li>
			  	<li><a href="<c:url value="member.htm?act=list"/>"><spring:message code="text.member.management"/></a></li>
  				</c:if>
  				
  				
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="member.htm?act=edit"/>"><spring:message code="text.personal.management"/></a></li>
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="member.htm?act=changePassword"/>"><spring:message code="text.change.password"/></a></li>
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="logout.htm"/>"><spring:message code="text.logout"/></a></li>
			  	<li class="menudeco"></li>
			  	
			  	<!-- 
				<li><a href="<c:url value="category.htm?act=list"/>"><spring:message code="text.category.management"/></a></li>
			  	<li class="menudeco"></li>
			  	<li><a href="<c:url value="doc.htm?act=listByVerified"/>"><spring:message code="text.document.management"/></a></li>
			  	<li class="menudeco"></li>
			  	-->
			  	
  			</ul>
		</div>
	</div>
</center>