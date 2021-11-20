<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
	<c:set var="contextPath" value="${pageContext.request.contextPath }" />
	<c:choose>
		<c:when test="${userid == null }">
			<a href="${contextPath }/auth">이메일 인증하기</a>
		</c:when>
		<c:otherwise>
			${userid }님 이메일 인증 되었습니다!!! <br>
			이 화면이 보이면  session이 있는 화면입니다.  <br>
			session : ${userid } <br>
		</c:otherwise>
	</c:choose>
	
	
	
</body>
</html>