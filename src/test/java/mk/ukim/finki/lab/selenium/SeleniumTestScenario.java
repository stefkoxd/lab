package mk.ukim.finki.lab.selenium;

import mk.ukim.finki.lab.model.Balloon;
import mk.ukim.finki.lab.model.Manufacturer;
import mk.ukim.finki.lab.service.BalloonService;
import mk.ukim.finki.lab.service.ManufacturerService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumTestScenario {

    @Autowired
    BalloonService balloonService;

    @Autowired
    ManufacturerService manufacturerService;

    private HtmlUnitDriver driver;

    private static Balloon balloon1;
    private static Balloon balloon2;
    private static Balloon balloon3;
    private static Balloon balloon4;
    private static Manufacturer manufacturer;
    private static boolean dataInit = false;

    @BeforeEach
    private void setup(){
        this.driver = new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if (this.driver!=null)
            this.driver.close();
    }

    private void initData(){
        if ( !dataInit ){
            manufacturer = manufacturerService.save("m1", "c1", "a1");
            balloon1 = balloonService.save("name","description",manufacturer.getId());
            balloon2 = balloonService.save("test1","test1description",manufacturer.getId());
            balloon3 = balloonService.save("test2","test2description",manufacturer.getId());
            balloon4 = balloonService.save("test3","test3description",manufacturer.getId());
            dataInit = true;
        }
    }

    @Test
    public void testScenario()throws Exception{
        BalloonsPage balloonsPage = BalloonsPage.to(this.driver);
        balloonsPage.assertElements(0);
        LoginPage loginPage = LoginPage.openLoginPage(this.driver);
        balloonsPage = LoginPage.doLogin(this.driver,loginPage,"admin","admin");
        balloonsPage.assertElements(9);
    }
}

