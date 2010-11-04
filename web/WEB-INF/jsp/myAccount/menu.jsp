<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<ul class="commonButtonMenu">
    <li><a href="<c:url value='overview.html'/>"><fmt:message key='menuItem.myAccount.overview'/></a></li>
    <li><a href="<c:url value='change-password.html'/>"><fmt:message key='menuItem.myAccount.changePassword'/></a></li>
    <li><a href="<c:url value='apply-for-writing-permission.html'/>"><fmt:message key='menuItem.myAccount.applyForWritingPermission'/></a></li>
    <li><a href="<c:url value='change-default-group.html'/>"><fmt:message key='menuItem.myAccount.changeDefaultGroup'/></a></li>
</ul>
