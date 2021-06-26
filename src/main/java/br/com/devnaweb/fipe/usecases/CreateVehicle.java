package br.com.devnaweb.fipe.usecases;

import br.com.devnaweb.fipe.entities.Vehicle;
import br.com.devnaweb.fipe.entities.requests.VehicleRequest;
import br.com.devnaweb.fipe.exceptions.UserNotFoundException;
import br.com.devnaweb.fipe.repositories.UserRepository;
import br.com.devnaweb.fipe.repositories.VehicleRepository;
import br.com.devnaweb.fipe.utils.RestrictionDayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CreateVehicle {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle execute(final VehicleRequest vehicleRequest, final String document) {
        if (userRepository.findUserByDocument(document).isPresent()) {
            return vehicleRepository.save(Vehicle.builder()
                    .brand(vehicleRequest.getBrand())
                    .model(vehicleRequest.getModel())
                    .type(vehicleRequest.getType())
                    .year(vehicleRequest.getYear())
                    .value("10000")
                    .document(document)
                    .restrictionDay(RestrictionDayUtil.getRestrictionDay(vehicleRequest.getYear()))
                    .build());
        }
        throw new UserNotFoundException();
    }
}
