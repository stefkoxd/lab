package mk.ukim.finki.lab.repository;

import mk.ukim.finki.lab.model.Balloon;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class BalloonRepository {
    private final List<Balloon> balloonList;

    BalloonRepository(){
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
}
