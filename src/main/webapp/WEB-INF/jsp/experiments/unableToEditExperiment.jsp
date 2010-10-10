<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@taglib prefix="ui" tagdir="/WEB-INF/tags/" %>

<ui:experimentsTemplate pageTitle="pageTitle.youDontHavePermission">

  <h1><fmt:message key="pageTitle.youDontHavePermission"/></h1>

  <p>
    <fmt:message key="text.youNeedToBeOwnerOrCoexperimenterToEditExperiment"/>
  </p>

</ui:experimentsTemplate>
