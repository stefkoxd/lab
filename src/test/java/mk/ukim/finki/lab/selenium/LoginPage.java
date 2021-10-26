package mk.ukim.finki.lab.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends AbstractPage {

    private WebElement username;
    private WebElement password;

    @FindBy(css=".btn")
    private WebElement btn;

    public static LoginPage openLoginPage(WebDriver webDriver){
        get(webDriver, "/login");
        return PageFactory.initElements(webDriver, LoginPage.class);
    }

    public static BalloonsPage doLogin(WebDriver driver, LoginPage loginPage, String username, String password){
        loginPage.username.sendKeys(username);
        loginPage.password.sendKeys(password);
        loginPage.btn.click();
        return PageFactory.initElements(driver, BalloonsPage.class);
    }

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
