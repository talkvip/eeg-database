<%@page contentType="text/html" pageEncoding="UTF-8" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/" %>
<%@taglib prefix="security" uri="http://www.springframework.org/security/tags" %>
<ui:personsTemplate pageTitle="pageTitle.personDetail">

    <h1><fmt:message key="pageTitle.personDetail"/></h1>

    <table class="standardValueTable">
        <tr>
            <th><fmt:message key="label.name"/></th>
            <td><c:out value="${personDetail.givenname}"/> <c:out value="${personDetail.surname}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="label.gender"/></th>
            <td>
                <c:if test="${personDetail.gender == 'M'}">
                    <fmt:message key="label.gender.male"/>
                </c:if>
                <c:if test="${personDetail.gender == 'F'}">
                    <fmt:message key="label.gender.female"/>
                </c:if>
            </td>
        </tr>
        <tr>
            <th><fmt:message key="label.dateOfBirth"/></th>
            <td><fmt:formatDate value="${personDetail.dateOfBirth}" pattern="dd.MM.yyyy"/></td>
        </tr>
        <tr>
            <th><fmt:message key="label.email"/></th>
            <td><c:out value="${personDetail.email}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="label.phoneNumber"/></th>
            <td><c:out value="${personDetail.phoneNumber}"/></td>
        </tr>
        <tr>
            <th><fmt:message key="label.userNote"/></th>
            <td><c:out value="${personDetail.note}"/></td>
        </tr>
    </table>



    <h2><fmt:message key="heading.optionalParameters"/></h2>
    <table class="dataTable" style="width: 450px;">
        <thead>
        <tr>
            <th style="width: 250px;"><fmt:message key="dataTable.heading.personOptionalParamName"/></th>
            <th><fmt:message key="dataTable.heading.personOptionalParamValue"/></th>
        </tr>
        </thead>
        <c:forEach items="${personDetail.personOptParamVals}" var="additionalParameter">
            <tr>
                <td><c:out value="${additionalParameter.personOptParamDef.paramName}"/></td>
                <td><c:out value="${additionalParameter.paramValue}"/></td>
            </tr>
        </c:forEach>
    </table>

    <div class="actionBox">
        <a href="<c:url value='add-optional-parameter.html?personId=${personDetail.personId}'/>"
           class="lightButtonLink"><fmt:message key="button.addOptionalParameter"/></a>
        <c:if test="${canEdit}">
            <a href="<c:url value='/people/edit.html?id=${personDetail.personId}'/>"
               class="lightButtonLink"><fmt:message key="button.edit"/></a>
        </c:if>
    </div>

</ui:personsTemplate>
