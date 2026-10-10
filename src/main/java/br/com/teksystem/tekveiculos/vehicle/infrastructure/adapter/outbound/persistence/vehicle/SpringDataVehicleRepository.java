package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle;

import io.lettuce.core.dynamic.annotation.Param;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface SpringDataVehicleRepository extends JpaRepository<VehicleJpaEntity, Long> {
    @Query("""
        SELECT DISTINCT v FROM VehicleJpaEntity v
        JOIN FETCH v.model m
        JOIN FETCH m.brand
        """)
    List<VehicleJpaEntity> findAllDetailed();

    @Query("""
        SELECT v FROM VehicleJpaEntity v
        JOIN FETCH v.model m
        JOIN FETCH m.brand
        WHERE v.id = :id
        """)
    Optional<VehicleJpaEntity> findDetailedById(@Param("id") Long id);

    boolean existsByPlate(String plate);
}
