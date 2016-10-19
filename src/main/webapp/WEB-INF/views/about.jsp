<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page session="true"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<%-- <t:IsBase></t:IsBase> --%>
<t:IsHeader hideModals="true"><%-- </t:IsHeader> --%>

<div class="container">
    <div class="row">
        <div class="span3">
       <t:sidebar/>
           <%--  <%@ include file="common/sidebar.jspf"%> --%>
        </div>
        <div class="span9">
            <div class="well">
                <div class="page-header">
                    <h1>About Todolist </h1>
                </div>
                <table class="table table-bordered table-striped">
                    <tbody>
                    <tr>
                        <td colspan="2"><strong>About</strong></td>
                    </tr>
                    <tr>
                        <td>Home page</td>
                        <td><a href="#">https://github.com/sudhir/todolist</a></td>
                    </tr>
                    
                    <tr>
                        <td colspan="2"><strong>Frameworks</strong></td>
                    </tr>
                    
                    <tr>
                        <td><a href="http://spring.io/">Spring</a></td>
                        <td>3.2</td>
                    </tr>
                    <tr>
                        <td><a href="http://www.hibernate.org/">Hibernate</a></td>
                        <td>4.3</td>
                    </tr>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>

</t:IsHeader>
<t:footer/>
<%--end content--%>
<%-- <%@ include file="common/footer.jspf"%> --%>