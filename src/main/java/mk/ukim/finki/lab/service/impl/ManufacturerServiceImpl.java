package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.Manufacturer;
import mk.ukim.finki.lab.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.lab.service.ManufacturerService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ManufacturerServiceImpl implements ManufacturerService {
    private final ManufacturerRepository manufacturerRepository;

    public ManufacturerServiceImpl(ManufacturerRepository manufacturerRepository) {
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Manufacturer> findAll() {
        return this.manufacturerRepository.findAll();
    }

    @Override
    public Optional<Manufacturer> findById(Long id) {
        if ( id != null )
            return manufacturerRepository.findById(id);
        else throw new InvalidArgumentsException();
    }

    @Override
    public Manufacturer save(String name, String country, String address) {
        return manufacturerRepository.save(new Manufacturer(name,country,address));
    }

}
