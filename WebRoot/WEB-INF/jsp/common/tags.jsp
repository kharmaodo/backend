<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %> 
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
<c:set var="httpsPrefix" value="http://${pageContext.request.serverName}${pageContext.request.contextPath}"/>
<c:set var="httpPrefix" value="http://${pageContext.request.serverName}${pageContext.request.contextPath}"/>
<c:set var="ctx" value="http://www.meettaiwan.com"/>