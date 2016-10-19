<%@tag description="Overall Page template" pageEncoding="UTF-8"%>
<%@attribute name="header" fragment="true" %>
<!DOCTYPE html>
<html >
<head>

<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<link rel="shortcut icon" href="/resources/img/todolist.ico"/>

<title>Todolist</title>

<!-- Le styles -->
<link href="${pageContext.request.contextPath}/resources/css/bootstrap.min.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/datepicker.css" rel="stylesheet">
<link href="${pageContext.request.contextPath}/resources/css/font-awesome.min.css" rel="stylesheet">
<style type="text/css">
    body {
        padding-top: 60px;
        /* 60px to make the container go all the way to the bottom of the topbar */
    }
</style>

<!-- Le javascript -->
<script src="${pageContext.request.contextPath}/resources/js/jquery-1.10.2.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/bootstrap-datepicker.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/jquery.noty.packaged.min.js" type="text/javascript"></script>
<script src="${pageContext.request.contextPath}/resources/js/todo.js" type="text/javascript"></script>


</head>

<body>
	<jsp:invoke fragment="header"/>
	<jsp:doBody/>
</body>
</html>