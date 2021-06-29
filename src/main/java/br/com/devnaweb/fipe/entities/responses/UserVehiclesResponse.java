package br.com.devnaweb.fipe.entities.responses;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(toBuilder = true)
public class UserVehiclesResponse {

    private String name;
    private String email;
    private String document;
    private String birthDate;
    private List<VehicleResponse> vehicles;
}
