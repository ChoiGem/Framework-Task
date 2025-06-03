package org.tukorea.free.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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
@Table(name = "order_free")
public class OrderEntity {

    @Id
    @Column(nullable = false)
    private String id;  // 주문 ID

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "total_price", nullable = false)
    private String totalPrice;
}
