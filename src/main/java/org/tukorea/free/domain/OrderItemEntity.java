package org.tukorea.free.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "order_item_free")
public class OrderItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // ★ AUTO_INCREMENT
    private Integer id;

    @Column(name = "order_id", nullable = false)
    private Integer orderId;          // FK만 숫자 값으로 들고 가도 OK

    @Column(name = "product_id", nullable = false)
    private String productId;

    @Column(nullable = false)
    private String quantity;

    @Column(nullable = false)
    private String price;             // 주문 당시 단가
}
