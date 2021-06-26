package br.com.devnaweb.fipe.entities;

import br.com.devnaweb.fipe.entities.enums.RestrictionDay;
import br.com.devnaweb.fipe.entities.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity(name = "vehicle")
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class Vehicle {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "brand", nullable = false)
    private String brand;

    @Column(name = "model", nullable = false)
    private String model;

    @Column(name = "year", nullable = false)
    private String year;

    @Column(name = "type", nullable = false)
    @Enumerated(EnumType.STRING)
    private VehicleType type;

    @Column(name = "value", nullable = false)
    private String value;

    @Column(name = "document", nullable = false)
    private String document;

    @Column(name = "restriction_day", nullable = false)
    @Enumerated(EnumType.STRING)
    private RestrictionDay restrictionDay;

}
