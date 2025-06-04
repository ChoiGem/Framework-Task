<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html; charset=UTF-8" %>
<html>
<head>
    <title>#Shop - 장바구니</title>
    <link rel="stylesheet" href="<c:url value='/resources/style.css'/>">
</head>
<body>

<div class="header">
    <div class="logo">#<i>Shop</i></div>
</div>

<form action="<c:url value='/order/form' />" method="get" onsubmit="return prepareOrder()">
    <input type="hidden" name="productIds"  id="productIds">
    <input type="hidden" name="quantities"  id="quantities">
    <input type="hidden" name="totalPrice"  id="totalPrice">

    <div id="cart-list"></div>

    <div class="order-total">합계: <span id="sum">0</span>원</div>
    <button type="submit" class="order-button">주문</button>
</form>

<script>
document.addEventListener("DOMContentLoaded", function () {

    /* ---------- 렌더링 함수 ---------- */
    function renderCart() {
        const cart       = JSON.parse(localStorage.getItem("cart") || "[]");
        const cartList   = document.getElementById("cart-list");
        const sumSpan    = document.getElementById("sum");

        cartList.innerHTML = "";                  // 비우고 다시 그리기
        let total = 0;

        cart.forEach(function (item, idx) {
            const name     = item.name    || "";
            const stock    = Number(item.stock    || 0);
            const qty      = Number(item.quantity || 0);
            const price    = Number(String(item.price).replaceAll(",", ""));
            const subTotal = price * qty;
            total += subTotal;

            /* 행(wrapper) */
            const row     = document.createElement("div");
            row.className = "order-item";

            /* 이름 */
            const nameDiv = document.createElement("div");
            nameDiv.className = "product-name";
            nameDiv.innerText = name;

            /* 재고 */
            const stockDiv = document.createElement("div");
            stockDiv.className = "product-stock";
            stockDiv.innerText = "남은 수량: " + stock;

            /* 수량 컨트롤 */
            const qtyDiv  = document.createElement("div");
            qtyDiv.className = "qty-control";

            const minus   = document.createElement("button");
            minus.type    = "button";
            minus.className = "qty-btn";
            minus.innerText = "➖";
            minus.onclick  = function () { updateQuantity(idx, -1); };

            const qtySpan = document.createElement("span");
            qtySpan.id    = "qty-" + idx;
            qtySpan.className = "qty";
            qtySpan.innerText = qty;

            const plus    = document.createElement("button");
            plus.type     = "button";
            plus.className = "qty-btn";
            plus.innerText = "➕";
            plus.onclick   = function () { updateQuantity(idx, +1); };

            qtyDiv.append(minus, qtySpan, plus);

            /* 소계 */
            const subDiv  = document.createElement("div");
            subDiv.id     = "subtotal-" + idx;
            subDiv.className = "subtotal";
            subDiv.innerText = subTotal.toLocaleString() + "원";

            /* 삭제 버튼 */
            const delBtn  = document.createElement("button");
            delBtn.type   = "button";
            delBtn.className = "del-btn";
            delBtn.innerText = "🗑️";
            delBtn.onclick   = function () { removeItem(idx); };

            /* 조립 */
            row.append(nameDiv, stockDiv, qtyDiv, subDiv, delBtn);
            cartList.appendChild(row);
        });

        sumSpan.innerText = total.toLocaleString();
    }

    /* ---------- 수량 수정 ---------- */
    function updateQuantity(index, diff) {
        const cart  = JSON.parse(localStorage.getItem("cart") || "[]");
        const item  = cart[index];
        const max   = Number(item.stock);
        const price = Number(String(item.price).replaceAll(",", ""));
        let   qty   = Number(item.quantity) + diff;

        if (qty < 1) {
            alert("최소 수량은 1개입니다.");
            qty = 1;
        } else if (qty > max) {
            alert("최대 수량은 " + max + "개까지 가능합니다.");
            qty = max;
        }

        item.quantity = qty;
        localStorage.setItem("cart", JSON.stringify(cart));
        renderCart();           // 다시 그려 합계·수량 동시 업데이트
    }

    /* ---------- 항목 삭제 ---------- */
    function removeItem(index) {
        const cart = JSON.parse(localStorage.getItem("cart") || "[]");
        cart.splice(index, 1);                // 배열에서 제거
        localStorage.setItem("cart", JSON.stringify(cart));
        renderCart();                         // 다시 그려서 반영
    }

    /* ---------- 주문 준비 ---------- */
    window.prepareOrder = function () {
        const cart = JSON.parse(localStorage.getItem("cart") || "[]");
        if (cart.length === 0) {
            alert("장바구니가 비어 있습니다.");
            return false;
        }

        document.getElementById("productIds").value =
            cart.map(function (p) { return p.id; }).join(",");
        document.getElementById("quantities").value  =
            cart.map(function (p) { return p.quantity; }).join(",");
        document.getElementById("totalPrice").value  =
            cart.reduce(function (sum, p) {
                return sum +
                       Number(p.quantity) *
                       Number(String(p.price).replaceAll(",", ""));
            }, 0);

        return true;
    };

    /* 첫 렌더링 */
    renderCart();
});
</script>

</body>
</html>
