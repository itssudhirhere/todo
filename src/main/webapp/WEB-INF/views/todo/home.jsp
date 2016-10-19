<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tl" uri="http://todo/taglib" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@taglib prefix="tag" uri="/WEB-INF/paginationTagLib.tld"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<t:IsHeader hideModals="true">
<jsp:body>
<div class="container">
    <div class="row">
        <div class="span3">
           
            <t:sidebar/>
        </div>
      
        <div class="span9">
            <div class="well">
                <div class="page-header">
                    <h1>My Todo list</h1>
                </div>

                <table class="table table-bordered table-striped">

                    <thead>
                    <tr>
                        <th>ID</th>
                        <th>Title</th>
                        <th>Due Date</th>
                        <th>Priority</th>
                        <th>Status</th>
                        <th>Action</th>
                    </tr>
                    </thead>

                    <tbody>
                   <%--  <c:forEach items="${todoList}" var="currentTodo">
                        <tr>
                            <td>${currentTodo.id}</td>
                            <td>${currentTodo.title}</td>
                            <td><fmt:formatDate value="${currentTodo.dueDate}" pattern="dd/MM/yyyy"/></td>
                            <td><i class="icon-circle-arrow-<tl:priorityIcon priority="${currentTodo.priority}"/>"></i> ${currentTodo.priority}</td>
                            <td><span class="label <tl:statusStyle status="${currentTodo.done}"/> "> <tl:statusLabel status="${currentTodo.done}"/></span></td>
                            <td>
                                <a class="btn btn-mini btn-primary" href="/todo/todos/${currentTodo.id}/update"><i class="icon-edit icon-white"></i> Edit</a>
                                <a class="btn btn-mini btn-danger" data-toggle="modal" href="#confirm_delete_${currentTodo.id}"><i class="icon-remove icon-white"></i> Delete</a>
                                <div class="modal hide" id="confirm_delete_${currentTodo.id}">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                        <h3>Confirmation</h3>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure to delete todo ${currentTodo.id} '${currentTodo.title}' ?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="/todo/todos/${currentTodo.id}/delete" method="post">
                                            <a href="#" class="btn" data-dismiss="modal">Cancel</a> <button type="submit" class="btn btn-primary">Confirm</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach> --%>
                    <c:forEach items="${todoList}" var="currentTodo">
                        <tr>
                            <td>${currentTodo.id}</td>
                            <td>${currentTodo.title}</td>
                            <td><fmt:formatDate value="${currentTodo.dueDate}" pattern="dd/MM/yyyy"/></td>
                            <td><i class="icon-circle-arrow-<tl:priorityIcon priority="${currentTodo.priority}"/>"></i> ${currentTodo.priority}</td>
                            <td><span class="label <tl:statusStyle status="${currentTodo.done}"/> "> <tl:statusLabel status="${currentTodo.done}"/></span></td>
                            <td>
                                <a class="btn btn-mini btn-primary" onclick="updateTodoGet(${currentTodo.id})"><i class="icon-edit icon-white"></i> Edit</a>
                                <a class="btn btn-mini btn-danger" data-toggle="modal" href="#confirm_delete_${currentTodo.id}"><i class="icon-remove icon-white"></i> Delete</a>
                                <div class="modal hide" id="confirm_delete_${currentTodo.id}">
                                    <div class="modal-header">
                                        <button type="button" class="close" data-dismiss="modal">×</button>
                                        <h3>Confirmation</h3>
                                    </div>
                                    <div class="modal-body">
                                        <p>Are you sure to delete todo ${currentTodo.id} '${currentTodo.title}' ?</p>
                                    </div>
                                    <div class="modal-footer">
                                        <form action="/todo/todos/${currentTodo.id}/delete" method="post">
                                            <a href="#" class="btn" data-dismiss="modal">Cancel</a> <button type="submit" class="btn btn-primary">Confirm</button>
                                        </form>
                                    </div>
                                </div>
                            </td>
                        </tr>
                    </c:forEach>

                    </tbody>
                    <tfoot>
                        <tr>
                            <td colspan="2"><div align="center">Total = <span class="badge badge-inverse">${requestScope.totalCount}</span></div></td>
                            <td colspan="2"><div align="center">Todo = <span class="badge">${requestScope.todoCount}</span></div></td>
                            <td colspan="2"><div align="center">Done = <span class="badge badge-success">${requestScope.doneCount}</span></div></td>
                        </tr>
                    </tfoot>
                </table>

                <c:if test="${empty requestScope.todoList}">
                    <div class="alert alert-info">
                        <div align="center">Your todo list is empty !</div>
                    </div>
                </c:if>

                <c:if test="${not empty requestScope.todoList}">
                <div>
                
            <div align="c">  
                <tag:paginate  max="10" offset="${offset}" count="${count}"
			uri="/todo/todos" next="Next" previous="Previous" /> 
			</div>
                    <!-- <button class="btn" onclick="javascript:window.print()">
                        <i class="icon-print"></i>
                        Print my todo list
                    </button> -->
                </div>
                </c:if>

            </div>
        </div>
    </div>
</div>
 <div id='abc'></div>

<script>
console.log("--------------------");
function updateTodo(){
	alert("---")
	//showMessage("hello","success");
	$( "form" ).on( "submit", function( event ) {
		  console.log( $( this ).serialize() );
		event.preventDefault();
		$.ajax({
	        type: 'post',
	        url: "/todo/todos/updateAjax",
	        data: $( this ).serialize(),
	        success: function (data) {
	        	console.log("===update ajax=="+data)
	        	showMessage(data.message,data.type);
	            
	        },
	        failure: function () {
	            console.log(">>>>>>>>Failed to get data pending pmi")
	        }
	    }); 
		
		});
	return false;
}

	function updateTodoGet(todoId){
		console.log("-----jnjn------"+todoId);
		$.ajax({
	        type: 'get',
	        url: "/todo/todos/update",
	        data: {'todoId': todoId},
	        success: function (data) {
	            if (!data.status) {
	                $('#abc').html(data)
	            }
	            else {
	                console.log("No project for pending pmi");
	                //$('#todoId').empty()
	            }
	        },
	        failure: function () {
	            console.log(">>>>>>>>Failed to get data pending pmi")
	        }
	    }); 
	}

	$(document).ready(function(){
	
	});
</script> 
 </jsp:body>
</t:IsHeader>
<%--end content--%>

<t:footer/>