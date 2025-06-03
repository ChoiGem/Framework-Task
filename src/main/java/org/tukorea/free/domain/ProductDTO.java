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
public class ProductDTO {
    private String id;
    private String name;
    private String price;
    private String stock;
    private String imagePath;

    public static ProductDTO toDTO(ProductEntity entity) {
        return ProductDTO.builder()
                .id(entity.getId())
                .name(entity.getName())
                .price(entity.getPrice())
                .stock(entity.getStock())
                .imagePath(entity.getImagePath())
                .build();
    }

    public static ProductEntity toEntity(ProductDTO dto) {
        return ProductEntity.builder()
                .id(dto.getId())
                .name(dto.getName())
                .price(dto.getPrice())
                .stock(dto.getStock())
                .imagePath(dto.getImagePath())
                .build();
    }
}
