package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model;

import br.com.teksystem.tekveiculos.shared.infrastructure.adapters.outbound.persistense.model.BaseJpaEntity;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.brand.BrandJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "model", uniqueConstraints = @UniqueConstraint(name = "uk_model_name_brand", columnNames = {"name", "brand_id"}))
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ModelJpaEntity extends BaseJpaEntity<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "model_sequence")
    @SequenceGenerator(name = "model_sequence", sequenceName = "model_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "brand_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_model_brand"))
    private BrandJpaEntity brand;
}
