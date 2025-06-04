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
@Table(name = "order_free")
public class OrderEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)   // ★ AUTO_INCREMENT 매핑
    private Integer id;

    @Column(name = "user_id", nullable = false)
    private String userId;

    @Column(name = "user_name", nullable = false)
    private String userName;

    @Column                      // ← DB도 NULL 허용이므로 nullable = true 가 기본
    private String email;

    @Column(nullable = false)
    private String address;

    private String memo;

    @Column(name = "order_date", nullable = false)
    private String orderDate;

    @Column(name = "total_price", nullable = false)
    private String totalPrice;
}
