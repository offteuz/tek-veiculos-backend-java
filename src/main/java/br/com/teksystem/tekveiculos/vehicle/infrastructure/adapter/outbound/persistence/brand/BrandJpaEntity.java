package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand;

import br.com.teksystem.tekveiculos.shared.infrastructure.adapters.outbound.persistense.model.BaseJpaEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Table(name = "brand")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BrandJpaEntity extends BaseJpaEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "brand_sequence")
    @SequenceGenerator(name = "brand_sequence", sequenceName = "brand_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private Boolean active = true;

    public BrandJpaEntity(String name, Boolean active) {
        this.name = name;
        this.active = active == null || active;
    }
}
