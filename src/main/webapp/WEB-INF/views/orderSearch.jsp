<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>#Shop - 주문 검색</title>
<link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
</head>
<body>
	<div class="header">
		<div class="logo">
			#<i>Shop</i>
		</div>
	</div>

	<div class="search-container">
		<h2>내 주문 목록</h2>
		<form action="<c:url value='/order/list' />" method="post" class="search-form">
			<input type="text" name="userId" placeholder="ID를 입력하세요" required>
			<button type="submit">🔍</button>
		</form>
	</div>
</body>
</html>
