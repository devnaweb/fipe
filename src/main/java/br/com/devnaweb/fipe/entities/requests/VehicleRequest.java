package br.com.devnaweb.fipe.entities.requests;

import br.com.devnaweb.fipe.entities.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleRequest {

    @NotNull
    private String brand;

    @NotNull
    private String model;

    @NotNull
    private String year;

    @NotNull
    private VehicleType type;
}
