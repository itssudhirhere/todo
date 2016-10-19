<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
 <%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
 
<t:IsHeader hideModals="true">
<jsp:body>
<div class="container">
    <div class="row well">
        <div class="span10 offset1">
            <div class="row">

                <div class="span5">
                    <h3>Welcome to Todolist MVC</h3>
                    <p>
                    <h4>Todolist MVC is a web-based task manager which allows you to:</h4>
                    <ul>
                        <li>Save and organize your todo list</li>
                        <li>Search easily your todo list</li>
                        <li>Sort and filter your todo list</li>
                        <li>Export and report your todo list</li>
                    </ul>
                    <h4>And which is totally Free! Enjoy !</h4>
                    </p>

                    <c:if test="${sessionScope['scopedTarget.sessionData'].user == null}">
                    <p>
                        <a class="btn btn-primary btn-large" href="/login"> Sign in </a> or <a class="btn btn-primary btn-large" href="/register"> Sign up </a>
                    </p>
                    </c:if>

                    <c:if test="${sessionScope['scopedTarget.sessionData'].user != null}">
                        <p>
                            <a class="btn btn-primary btn-large" href="/todo/todos"> Go to my Home page </a>
                        </p>
                    </c:if>

                </div>

                <div class="span5">
                    <img src="/resources/img/todolist.jpg" alt="todolist">
                </div>

            </div>

        </div>

    </div>
</div>
 </jsp:body>
</t:IsHeader>
<t:footer/>
<%-- <%@ include file="common/footer.jspf"%> --%>
