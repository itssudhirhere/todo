<%@tag description="User Header template" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@attribute name="hideModals" required="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<t:IsBase>
	<jsp:attribute name="header">
<div class="navbar navbar-fixed-top">
    <div class="navbar-inner">
        <div class="container">
            <a class="brand" href="/index">Todolist</a>
           
            <c:if test="${sessionScope['scopedTarget.sessionData'].user != null}">
            <ul class="nav">
                <li class="${homeTabStyle}"><a href="/todo/todos">Home</a></li>
                <li class="${aboutTabStyle}"><a href="/about">About</a></li>
            </ul>
            <div class="btn-group pull-right">
                <a class="btn dropdown-toggle" data-toggle="dropdown" href="#">
                    <i class="icon-user"></i> Hi ${sessionScope['scopedTarget.sessionData'].user.name} ! <span class="caret"></span>
                </a>
                <ul class="dropdown-menu">
                    <li><a href="/user/account">My account</a></li>
                    <li class="divider"></li>
                    <li><a href="/logout">Sign out</a></li>
                </ul>
            </div>
            </c:if>

            <%--not logged in mode --%>
            <c:if test="${sessionScope['scopedTarget.sessionData'].user == null}">
            <ul class="nav pull-right">
                <li class="${registerTabStyle}"><a href="/register">Register</a></li>
                <li class="${loginTabStyle}"><a href="/login">Login</a></li>
            </ul>
            </c:if>

        </div>
    </div>
</div>
</jsp:attribute>
	<jsp:body>
		<jsp:doBody />
	</jsp:body>
</t:IsBase>