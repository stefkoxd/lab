package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Manufacturer;

import java.util.List;
import java.util.Optional;

public interface ManufacturerService {
    List<Manufacturer> findAll();
    Optional<Manufacturer> findById(Long id);
    Manufacturer save(String name, String country, String address);
}
