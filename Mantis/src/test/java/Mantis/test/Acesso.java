package Mantis.test;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class Acesso {

    public WebDriver acessarMantis() {
        String URL_BASE = "https://mantis-prova.base2.com.br/my_view_page.php";
        String USERNAME = "Pablo_Duarte";
        String PASSWORD = "xbox1210";

        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, 30);

        driver.navigate().to(URL_BASE);
        try {
            // Aguarda 5 segundos para garantir que a página de login seja carregada completamente
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        WebElement usernameField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='username']")));
        usernameField.sendKeys(USERNAME);

        WebElement submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='login-form']/fieldset/input[2]")));
        submitButton.click();

        WebElement passwordField = driver.findElement(By.xpath("//*[@id='password']"));
        passwordField.sendKeys(PASSWORD);

        submitButton = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='login-form']/fieldset/input[3]")));
        submitButton.click();

        wait.until(ExpectedConditions.urlContains("my_view_page.php"));

        return driver; // Retorna o WebDriver já autenticado
    }
}