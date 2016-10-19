<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>

<%-- <t:IsBase></t:IsBase> --%>
<t:IsHeader hideModals="true">

<div class="container">

    <div class="row">
        <div class="span6 offset3">
            <div class="page-header">
                <h1>Sign in</h1>
            </div>
			<t:error/>
           
            <sf:form class="well form-horizontal" method="post" action="/login" modelAttribute="loginForm">
                <fieldset>

                    <div class="control-group">
                        <label class="control-label" for="email">Email:</label>
                        <div class="controls">
                            <sf:input path="email" id="email" type="text" class="input-medium" placeholder="your@email.com" required="required"/>
                     
                            <p class="help-block alert-error"><sf:errors path="email" cssClass="error"/></p>
                        </div>
                    </div>

                    <div class="control-group">
                        <label class="control-label" for="password">Password:</label>
                        <div class="controls">
                            <sf:input type="password" path="password" id="password" class="input-medium" placeholder="min 6 characters" required="required"/>
                            <p class="help-block alert-error"><sf:errors path="password" cssClass="error"/></p>
                        </div>
                    </div>

                    <div class="form-actions">
                        <button type="submit" class="btn btn-primary"><i class="icon-lock icon-white"></i> Sign in</button>
                    </div>

                    <div align="center">
                        You don't have an account yet? <a href="/register">Register here for free!</a>
                    </div>

                </fieldset>
            </sf:form>

        </div>
    </div>
</div>
</t:IsHeader>
<%--end content--%>
<%-- <%@ include file="../common/footer.jspf"%> --%>
<t:footer/>
