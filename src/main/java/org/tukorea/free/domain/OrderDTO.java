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
public class OrderDTO {
	private Integer id;
    private String userId;
    private String userName;
    private String email;
    private String address;
    private String memo;
    private String orderDate;
    private String totalPrice;

    public static OrderDTO toDTO(OrderEntity entity) {
        return OrderDTO.builder()
                .id(entity.getId())
                .userId(entity.getUserId())
                .userName(entity.getUserName())
                .email(entity.getEmail())
                .address(entity.getAddress())
                .memo(entity.getMemo())
                .orderDate(entity.getOrderDate())
                .totalPrice(entity.getTotalPrice())
                .build();
    }

    public static OrderEntity toEntity(OrderDTO dto) {
        return OrderEntity.builder()
                .id(dto.getId())
                .userId(dto.getUserId())
                .userName(dto.getUserName())
                .email(dto.getEmail())
                .address(dto.getAddress())
                .memo(dto.getMemo())
                .orderDate(dto.getOrderDate())
                .totalPrice(dto.getTotalPrice())
                .build();
    }
}
