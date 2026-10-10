package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataModelRepository extends JpaRepository<ModelJpaEntity, Long> {
    boolean existsByName(String name);

    boolean existsByNameAndBrandId(String name, Long brandId);
}
