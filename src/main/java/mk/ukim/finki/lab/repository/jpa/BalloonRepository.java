package mk.ukim.finki.lab.repository.jpa;

import mk.ukim.finki.lab.model.Balloon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface BalloonRepository extends JpaRepository<Balloon, Long> {
    List<Balloon> findAllByNameOrDescription(String name, String description);
    void removeById(Long id);
    Optional<Balloon> findById(Long id);
    void removeByName(String name);
}
