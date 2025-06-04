<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page contentType="text/html;charset=UTF-8"%>
<html>
<head>
<title>#Shop - 홈</title>
<link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
</head>
<body>
	<div class="header">
		<div class="logo">
			#<i>Shop</i>
		</div>
		<div>
			<a href="<c:url value='/order/search'/>">내 주문목록</a>
		</div>
	</div>

	<div class="product-list">
		<c:forEach var="product" items="${products}">
			<div class="product-card" data-id="${product.id}"
				data-price="${product.price}" data-stock="${product.stock}">
				<div class="product-image">
					<img src="<c:url value='/resources/images/${product.imagePath}' />"
						alt="상품 이미지" style="width: 100%; height: 100%; object-fit: cover;">
				</div>
				<div class="product-name">${product.name}</div>
				<div class="product-price">${product.price}원</div>
				<div class="product-stock">남은수량: ${product.stock}</div>
				<button class="add-to-cart-btn">장바구니 담기</button>
			</div>
		</c:forEach>
	</div>

	<a href="<c:url value='/cart'/>" class="cart-button">🛒 장바구니</a>

	<script>
    document.querySelectorAll('.add-to-cart-btn').forEach(btn => {
        btn.addEventListener('click', function () {
            const card = this.closest('.product-card');
            const id = card.getAttribute('data-id');
            const price = card.getAttribute('data-price');
            const stock = card.getAttribute('data-stock');
            const name = card.querySelector('.product-name')?.innerText || "이름없음";

            let cart = JSON.parse(localStorage.getItem("cart") || "[]");

            const existing = cart.find(item => item.id === id);
            if (existing) {
                alert("이미 장바구니에 담긴 상품입니다.");
            } else {
                cart.push({ id, name, price, quantity: 1, stock });
                localStorage.setItem("cart", JSON.stringify(cart));
                alert("장바구니에 추가되었습니다.");
            }
        });
    });
</script>

</body>
</html>
