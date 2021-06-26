package br.com.devnaweb.fipe.repositories;

import br.com.devnaweb.fipe.entities.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    List<Vehicle> findVehiclesByDocument(final String document);
}
