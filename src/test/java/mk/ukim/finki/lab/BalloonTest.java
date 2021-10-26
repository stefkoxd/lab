package mk.ukim.finki.lab;

import mk.ukim.finki.lab.exceptions.InvalidArgumentsException;
import mk.ukim.finki.lab.model.Balloon;
import mk.ukim.finki.lab.model.Manufacturer;
import mk.ukim.finki.lab.repository.jpa.BalloonRepository;
import mk.ukim.finki.lab.repository.jpa.ManufacturerRepository;
import mk.ukim.finki.lab.service.impl.BalloonServiceImpl;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Optional;

@RunWith(MockitoJUnitRunner.class)
public class BalloonTest {
    @Mock
    private BalloonRepository balloonRepository;

    @Mock
    ManufacturerRepository manufacturerRepository;

    private BalloonServiceImpl service;

    @Before
    public void init(){
        MockitoAnnotations.initMocks(this);
        Manufacturer manufacturer = new Manufacturer("m1", "c1", "a1");
        Balloon balloon = new Balloon("name", "description", manufacturer);
        Mockito.when(this.balloonRepository.save(Mockito.any(Balloon.class))).thenReturn(balloon);
        Mockito.when(this.manufacturerRepository.findById(Mockito.anyLong())).thenReturn(Optional.of(manufacturer));
        service = Mockito.spy(new BalloonServiceImpl(this.balloonRepository, this.manufacturerRepository));
    }

    @Test
    public void testEmptyName(){
        String name = "";
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidArgumentsException.class, ()->this.service.save(name,"description", (long)1));
        Mockito.verify(this.service).save(name,"description",(long)1);
    }

    @Test
    public void testEmptyDescription(){
        String description = "";
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidArgumentsException.class, ()->this.service.save("name", description, (long)1));
        Mockito.verify(this.service).save("name",description,(long)1) ;
    }

    @Test
    public void testInvalidManufacturer(){
        Assert.assertThrows("InvalidArgumentsException expected",
                InvalidArgumentsException.class, ()->this.service.save("name","description",null));
        Mockito.verify(this.service).save("name","description",null);
    }

    @Test
    public void success(){
        Balloon balloon = this.service.save("name","description",1L);

        Mockito.verify(this.service).save("name","description",1L);
        Assert.assertEquals("Names don't match", "name", balloon.getName());
        Assert.assertEquals("Descriptions don't match", "description", balloon.getDescription());
        Assert.assertEquals("Manufacturers don't match", manufacturerRepository.findById(1L).orElseThrow(), balloon.getManufacturer());
    }
}
