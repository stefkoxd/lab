package mk.ukim.finki.lab.repository.impl;

import mk.ukim.finki.lab.model.Balloon;
import mk.ukim.finki.lab.model.Manufacturer;
import mk.ukim.finki.lab.service.ManufacturerService;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Repository
public class InMemoryBalloonRepository {
    private final List<Balloon> balloonList;
    private final ManufacturerService manufacturerService;

    InMemoryBalloonRepository(ManufacturerService manufacturerService){
        this.manufacturerService = manufacturerService;
        balloonList = new ArrayList<>();
        for( int i = 0; i < 10 ; i++ ){
            int num = i+1;
            String name = "Balloon " + num;
            String desc = "Description for balloon " + num;
            balloonList.add(new Balloon(name, desc));
        }
    }

    public List<Balloon> findAllBalloons(){
        return this.balloonList;
    }

    public List<Balloon> findAllByNameOrDescription(String text){
        return this.balloonList.stream().filter(b -> b.getName().contains(text) || b.getDescription().contains(text)).collect(Collectors.toList());
    }

    public Optional<Balloon> save(String name, String description, Long manufacturerId){
        Manufacturer m = manufacturerService.findById(manufacturerId).orElseThrow();
        Balloon b = new Balloon(name,description, m);
        balloonList.removeIf(r -> r.getName().equals(name));
        balloonList.add(b);
        return Optional.of(b);
    }

    public Optional<Balloon> getById(Long id){
        return balloonList.stream().filter(b -> b.getId().equals(id)).findFirst();
    }
    public boolean removeById(Long id){
        return balloonList.removeIf(b -> b.getId().equals(id));
    }
}
