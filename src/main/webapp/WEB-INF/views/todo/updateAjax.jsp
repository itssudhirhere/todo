<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>


<div class="container">
    <div class="row">
        <div class="span3">
          
        </div>
        <div class="span9">
            <div class="well">
                <div class="page-header">
                    <h1>Update todo</h1>
                </div>

                <sf:form id="update" action="/todo/updateAjax" method="post" class="form-horizontal"
                 modelAttribute="todo" onsubmit="return updateTodo()">
<legend style="text-align: center;"> <p class="${messageCss}">${message}</p></legend>
                     <fieldset>

                        <div class="control-group">
                            <label class="control-label" for="id">Todo Id:</label>
                            <div class="controls">
                               <sf:input type="text" path="id" value="${todo.id}"/>
<%--                                 <input type="text" id="id" name="id" value="${todo.id}" disabled="disabled"/> --%>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="title">Title:</label>
                            <div class="controls">
                                <sf:input type="text" id="title" path="title" value="${todo.title}" required="required" autofocus="autofocus" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="dueDate">Due date:</label>
                            <div class="controls">
                     
                                <sf:input type="text" id="dueDate" path="dueDate" value="${dueDate}" required="required" />
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="status">Status:</label>
                            <div class="controls">
                                <sf:select id="status" path="done">
                                  <sf:option value="false">Todo</sf:option>
                                  <sf:option value="true">Done</sf:option>
                              </sf:select>
                            </div>
                        </div>

                        <div class="control-group">
                            <label class="control-label" for="priority">Priority:</label>
                            <div class="controls">
                                <sf:select id="priority" path="priority">
                                  <sf:option value="LOW">Low</sf:option>
                                  <sf:option value="MEDIUM">Medium</sf:option>
                                  <sf:option value="HIGH">High</sf:option>
                              </sf:select>
                            </div>
                        </div>

                        <sf:input type="hidden" path="userId" value="${todo.userId}"/>

                        <div class="form-actions">
                            <button type="submit" class="btn btn-primary"> <i class="icon-refresh icon-white"></i> Update</button>
                            <button type="button" class="btn" onclick="history.go(-1)"><i class="icon-remove"></i> Cancel</button>
                        </div>

                    </fieldset> 
                    

                    <script>
                        $('#dueDate').datepicker({
                            format : 'dd/mm/yyyy'
                            });
                    </script>

                </sf:form>

            </div>
        </div>
    </div>
</div>
