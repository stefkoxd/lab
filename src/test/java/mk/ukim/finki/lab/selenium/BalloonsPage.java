package mk.ukim.finki.lab.selenium;

import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

public class BalloonsPage extends AbstractPage {

    @FindBy(className="adminbtn")
    private List<WebElement> btns;


    public BalloonsPage(WebDriver driver) {
        super(driver);
    }

    public static BalloonsPage to(WebDriver driver){
        get(driver, "/products");
        return PageFactory.initElements(driver,BalloonsPage.class);
    }

    public void assertElements(int numButtons){
        btns.forEach(System.out::println);
        Assert.assertEquals("Buttons do not match", numButtons, btns.size());
    }
}
