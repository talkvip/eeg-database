<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<ui:listsTemplate pageTitle="pageTitle.addEyesDefect">
    <h1><fmt:message key="pageTitle.addEyesDefect"/></h1>

    <c:url value="/lists/eyes-defects/add.html" var="formUrl"/>
    <form:form action="${formUrl}" method="post" commandName="addEyesDefect" cssClass="standardInputForm">
        <fieldset>

            <div class="itemBox">
                <form:label path="description" cssClass="textFieldLabel" cssErrorClass="textFieldLabel errorLabel"><fmt:message key="label.description"/></form:label>

                <form:input path="description" cssClass="textField" cssErrorClass="textField errorField" />

                <form:errors path="description" cssClass="errorBox" />
            </div>


            <div class="itemBox">
                <input type="submit" value="<fmt:message key='button.addEyesDefect'/>" class="submitButton lightButtonLink" />
            </div>

        </fieldset>
    </form:form>
</ui:listsTemplate>