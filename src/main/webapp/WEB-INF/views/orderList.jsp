<%@ page contentType="text/html;charset=UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
<title>#Shop - 주문 목록</title>
<link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
</head>
<body>
	<div class="header">
		<div class="logo">
			#<i>Shop</i>
		</div>
	</div>

	<div class="order-list-container">
		<h2>'${param.userId}'님의 주문 목록</h2>

		<c:choose>
			<c:when test="${empty orderList}">
				<p class="no-order">해당 ID로 주문한 내역이 없습니다.</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="order" items="${orderList}">
					<div class="order-card">
						<div class="order-image">상품 사진</div>
						<div class="order-info">
							<div>
								<strong>${order.userName}</strong>, ${order.email}
							</div>
							<div>${order.address}</div>
							<div>요청사항: ${order.memo}</div>
							<div>날짜: ${order.orderDate}</div>
						</div>
						<div class="order-price">${order.totalPrice}원</div>
					</div>
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
