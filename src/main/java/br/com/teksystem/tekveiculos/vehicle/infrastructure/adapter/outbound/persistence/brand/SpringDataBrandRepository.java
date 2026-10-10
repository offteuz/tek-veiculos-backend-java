package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand;

import org.springframework.data.jpa.repository.JpaRepository;

public interface SpringDataBrandRepository extends JpaRepository<BrandJpaEntity, Long> {
    boolean existsByName(String name);
}
