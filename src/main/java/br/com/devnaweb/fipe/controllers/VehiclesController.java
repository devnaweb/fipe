package br.com.devnaweb.fipe.controllers;

import br.com.devnaweb.fipe.entities.converters.VehiclesConverter;
import br.com.devnaweb.fipe.entities.requests.VehicleRequest;
import br.com.devnaweb.fipe.entities.responses.UserVehiclesResponse;
import br.com.devnaweb.fipe.entities.responses.VehicleResponse;
import br.com.devnaweb.fipe.usecases.CreateVehicle;
import br.com.devnaweb.fipe.usecases.ListUserVehicles;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/devnaweb/fipe")
public class VehiclesController {

    @Autowired
    private CreateVehicle createVehicle;

    @Autowired
    private ListUserVehicles listUserVehicles;

    @PostMapping("/vehicle/{document}")
    public ResponseEntity<VehicleResponse> createVehicle(@RequestBody final VehicleRequest vehicleRequest,
                                                         @PathVariable final String document) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
                VehiclesConverter.toResponse(createVehicle.execute(vehicleRequest, document)));
    }

    @GetMapping("/vehicle/{document}")
    public ResponseEntity<UserVehiclesResponse> getVehiclesFromUser(@PathVariable final String document) {
        return ResponseEntity.status(HttpStatus.OK).body(
                VehiclesConverter.toUserVehiclesResponse(listUserVehicles.execute(document)));
    }
}
