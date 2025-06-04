<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
    <title>#Shop - 주문하기</title>
    <!-- 공통 스타일 -->
    <link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
</head>
<body>

<div class="header">
    <div class="logo">#<i>Shop</i></div>
</div>

<div class="order-wrapper">
    <!-- 합계 표시 -->
    <div class="order-total">합계: <span><fmt:formatNumber value="${totalPrice}" type="number" /></span>원</div>

    <!-- 주문 정보 입력 -->
    <form class="order-form" action="<c:url value='/order/submit'/>" method="post">
        <!-- 서버에 다시 넘겨야 할 데이터 -->
        <input type="hidden" name="productIds"  value="${productIds}">
        <input type="hidden" name="quantities"  value="${param.quantities}">
        <input type="hidden" name="totalPrice"  value="${totalPrice}">

        <div class="form-row">
            <label>ID<span class="required-star">*</span></label>
            <input type="text" name="userId" required>
        </div>

        <div class="form-row">
            <label>성명<span class="required-star">*</span></label>
            <input type="text" name="userName" required>
        </div>

        <div class="form-row">
            <label>Email</label>
            <input type="email" name="email" placeholder="example@domain.com">
        </div>

        <div class="form-row">
            <label>주소<span class="required-star">*</span></label>
            <input type="text" name="address" required>
        </div>

        <div class="form-row">
            <label>요청사항</label>
            <input type="text" name="request">
        </div>

        <div class="submit-box">
            <button type="submit">주문</button>
        </div>
    </form>
</div>

</body>
</html>
