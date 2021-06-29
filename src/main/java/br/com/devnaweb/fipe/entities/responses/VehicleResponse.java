package br.com.devnaweb.fipe.entities.responses;

import br.com.devnaweb.fipe.entities.enums.RestrictionDay;
import br.com.devnaweb.fipe.entities.enums.VehicleType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class VehicleResponse {

    private String brand;
    private String model;
    private String year;
    private VehicleType type;
    private String value;
    private RestrictionDay restrictionDay;
    private boolean isRestrictionDay;
}
