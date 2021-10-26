package mk.ukim.finki.lab.service.impl;

import mk.ukim.finki.lab.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.Balloon;
import mk.ukim.finki.lab.repository.jpa.BalloonRepository;
import mk.ukim.finki.lab.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.lab.service.BalloonService;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
public class BalloonServiceImpl implements BalloonService {

    private final BalloonRepository balloonRepository;
    private final ManufacturerRepository manufacturerRepository;
    public BalloonServiceImpl(BalloonRepository balloonRepository, ManufacturerRepository manufacturerRepository) {
        this.balloonRepository = balloonRepository;
        this.manufacturerRepository = manufacturerRepository;
    }

    @Override
    public List<Balloon> listAll() {
        return balloonRepository.findAll();
    }

    @Override
    public List<Balloon> searchByNameOrDescription(String name, String description) {
        if ( name.isEmpty() || description.isEmpty() ){
            throw new InvalidArgumentsException();
        }
        return balloonRepository.findAllByNameOrDescription(name,description);
    }

    @Override
    @Transactional
    public Balloon save(String name, String description, Long manufacturerId) {
        if ( name.isEmpty() || description.isEmpty() || manufacturerId == null )
            throw new InvalidArgumentsException();

        this.balloonRepository.removeByName(name);
        return balloonRepository.save(new Balloon(name,description,manufacturerRepository.findById(manufacturerId).orElseThrow()));
    }

    @Override
    @Transactional
    public void removeById(Long id) {
        if ( id == null )
            throw new InvalidArgumentsException();
        balloonRepository.removeById(id);
    }

    @Override
    public Optional<Balloon> getById(Long id) {
        return balloonRepository.findById(id);
    }
}
