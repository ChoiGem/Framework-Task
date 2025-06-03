package org.tukorea.free.persistence;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.tukorea.free.domain.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, String> {

    List<ProductEntity> findAll();
    Optional<ProductEntity> findById(String id);
    List<ProductEntity> findByNameContaining(String keyword); // optional
    ProductEntity save(ProductEntity entity);
}
