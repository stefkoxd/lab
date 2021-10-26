package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.model.Manufacturer;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository
public class InMemoryManufacturerRepository {

    private List<Manufacturer> manufacturers;

    public InMemoryManufacturerRepository() {
        this.manufacturers = new ArrayList<>();
        for ( int i = 0; i < 5; i++){
            String name = "Manufacturer " + (i+1);
            String country = "Country " + (i+1);
            String address = "Address " + (i+1);
            manufacturers.add(new Manufacturer(name, country, address));
        }
    }

    public List<Manufacturer> findAll(){
        return this.manufacturers;
    }

    public Optional<Manufacturer> findById(Long id){
        return this.manufacturers.stream().filter(m -> m.getId().equals(id)).findFirst();
    }
}
