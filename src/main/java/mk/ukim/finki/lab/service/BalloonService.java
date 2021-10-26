package mk.ukim.finki.lab.service;

import mk.ukim.finki.lab.model.Balloon;

import java.util.List;
import java.util.Optional;

public interface BalloonService {
    List<Balloon> listAll();
    List<Balloon> searchByNameOrDescription(String name, String description);
    Balloon save(String name, String description, Long manufacturerId);
    void removeById(Long id);
    Optional<Balloon> getById(Long id);
}
