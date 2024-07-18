package Mantis.test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;

import static org.junit.Assert.assertTrue;

public class Test {

    @org.junit.Test
    public void criarTarefa() {
        Acesso acesso = new Acesso();
        WebDriver driver = acesso.acessarMantis(); // Utiliza o método de login

        WebDriverWait wait = new WebDriverWait(driver, 30);

        WebElement criarTarefaMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/bug_report_page.php']")));
        criarTarefaMenu.click();

        Select categoryField = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category_id"))));
        categoryField.selectByIndex(2);

        Select frequencyField = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("reproducibility"))));
        frequencyField.selectByIndex(2);

        Select severityField = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("severity"))));
        severityField.selectByIndex(1);

        Select priorityField = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("priority"))));
        priorityField.selectByIndex(2);

        WebElement summaryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        summaryField.sendKeys("Resumo do Bug");

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        descriptionField.sendKeys("Descrição teste.");

        WebElement stepsField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("steps_to_reproduce")));
        stepsField.sendKeys("Passos testes para reproduzir o bug.");

        WebElement additionalInfoField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("additional_info")));
        additionalInfoField.sendKeys("Informações testes adicionais sobre o bug.");

        // Interação com o botão "Criar Nova Tarefa" no rodapé do formulário
        WebElement submitButtonFooter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input")));
        submitButtonFooter.click();

        //driver.quit();
    }
    @org.junit.Test
    public void testePreencherCamposObrigatorios() {
        Acesso acesso = new Acesso();
        WebDriver driver = acesso.acessarMantis(); // Utiliza o método de login

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Navegar para a página de criação de tarefa
        WebElement criarTarefaMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/bug_report_page.php']")));
        criarTarefaMenu.click();

        // Preenchimento dos campos obrigatórios
        Select categoryField = new Select(wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("category_id"))));
        categoryField.selectByIndex(2);

        WebElement summaryField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("summary")));
        summaryField.sendKeys("Resumo do Bug");

        WebElement descriptionField = wait.until(ExpectedConditions.visibilityOfElementLocated(By.id("description")));
        descriptionField.sendKeys("Descrição teste.");

        WebElement submitButtonFooter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input")));
        submitButtonFooter.click();

        // Verificar se a tarefa foi criada com sucesso
        assertTrue(driver.getPageSource().contains("Operation successful"));

        //driver.quit();
    }

    @org.junit.Test
    public void testeValidarCamposObrigatorios() {
        Acesso acesso = new Acesso();
        WebDriver driver = acesso.acessarMantis(); // Utiliza o método de login

        WebDriverWait wait = new WebDriverWait(driver, 30);

        // Navegar para a página de criação de tarefa
        WebElement criarTarefaMenu = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//a[@href='/bug_report_page.php']")));
        criarTarefaMenu.click();

        // Tentar enviar o formulário sem preencher os campos obrigatórios
        WebElement submitButtonFooter = wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='report_bug_form']/div/div[2]/div[2]/input")));
        submitButtonFooter.click();

        // Verificar se as mensagens de erro são exibidas
        assertTrue(driver.getPageSource().contains("É necessário preenchimento."));

        driver.quit();
    }




}


