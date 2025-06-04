package org.tukorea.free.domain;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.Builder;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@ToString
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class OrderItemDTO {
    private String id;
    private String orderId;
    private String productId;
    private String quantity;
    private String price;

    public static OrderItemDTO toDTO(OrderItemEntity entity) {
        return OrderItemDTO.builder()
                .id(entity.getId())
                .orderId(entity.getOrderId())
                .productId(entity.getProductId())
                .quantity(entity.getQuantity())
                .price(entity.getPrice())
                .build();
    }

    public static OrderItemEntity toEntity(OrderItemDTO dto) {
        return OrderItemEntity.builder()
                .id(dto.getId())
                .orderId(dto.getOrderId())
                .productId(dto.getProductId())
                .quantity(dto.getQuantity())
                .price(dto.getPrice())
                .build();
    }
}
