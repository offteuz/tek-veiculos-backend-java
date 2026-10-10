package br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.vehicle;

import br.com.teksystem.tekveiculos.vehicle.domain.vehicle.Color;
import br.com.teksystem.tekveiculos.vehicle.infrastructure.adapter.outbound.persistence.model.ModelJpaEntity;
import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "vehicle")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class VehicleJpaEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vehicle_sequence")
    @SequenceGenerator(name = "vehicle_sequence", sequenceName = "vehicle_sequence", allocationSize = 1)
    private Long id;

    @Column(nullable = false, unique = true)
    private Long numberVehicle;

    @Column(nullable = false)
    private Color color;

    @Column(nullable = false, unique = true)
    private String plate;

    @Column(nullable = false)
    private Long currentMileage;

    @Column(nullable = false)
    private Boolean active;

    @JsonIgnore
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "model_id", referencedColumnName = "id", foreignKey = @ForeignKey(name = "fk_vehicle_model"))
    private ModelJpaEntity model;
}
