<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="X-UA-Compatible" content="IE=edge" />
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
<script src="${pageContext.servletContext.contextPath}/assets/js/jquery-1.4.2.min.js" type="text/javascript"></script>

<script type="text/javascript">
$(function(){
});
</script>
</head>
<body>
<div>
	<form action="list" method="get">
	<fieldset>
		<legend>리스트</legend>
		<input type="submit" value="리스트" />
	</fieldset>
	</form>
	<form action="create" method="post">
		<fieldset>
			<legend>추가</legend>
	      	src: <input type="text" name="src" value="http://saleapp.cafe24.com/commons.js" /><br />
			display_location : <input type="text" name="displayLocation" value="ORDER_BASKET" /><br />
			<input type="submit" value="추가" />
		</fieldset>
	</form>
	<form action="delete">
		<fieldset>
			<legend>제거</legend>
	      	script_no: <input type="text" name="scriptNo" /><br />
			<input type="submit" value="제거" />
		</fieldset>
	</form>
</div>
<div>
	<c:forEach items="${list}" var="ldata">
		------------------------------------------------------------------------<br />
		shop_no : ${ldata.shop_no}<br />
		script_no : ${ldata.script_no}<br />
		스크립트 주소 : ${ldata.src}<br />
		Client_id : ${ldata.client_id}<br />
		display_location : ${ldata.display_location}<br />
		created_date : ${ldata.created_date}<br />
		updated_date : ${ldata.updated_date}<br />
		------------------------------------------------------------------------<br />
	</c:forEach>
</div>
</body>
</html>