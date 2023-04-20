package webTeste;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testarEveclassChrome {

    private WebDriver driver;

    Properties prop = new Properties();



@BeforeEach

        public void setup() throws IOException {



    WebDriverManager.chromedriver().setup();
    ChromeOptions options = new ChromeOptions();
    options.addArguments("--remote-allow-origins=*");

    driver = new ChromeDriver(options);
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

    FileInputStream file = new FileInputStream("src/test/resources/config.properties");
    prop.load(file);
    file.close();

    String email = prop.getProperty("email");
    // Lê os valores de senha do arquivo de propriedades
    String senha = prop.getProperty("senha");


    driver.get("https://testando.eveclass.com/pt");
    driver.findElement(By.xpath("//span[contains(text(),'Entrar')]")).click();
    driver.navigate().refresh();
    driver.findElement(By.xpath("//input[@data-vv-as='Email']")).click();
    driver.findElement(By.xpath("//input[@data-vv-as='Email']")).sendKeys(email);
    driver.findElement(By.xpath("//input[@type='password']")).click();
    driver.findElement(By.xpath("//input[@type='password']")).sendKeys(senha);
    driver.findElement(By.xpath("//span[normalize-space()='Entrar']")).click();


}

    @AfterEach
    public void teardown() {
        driver.manage().deleteAllCookies();
        driver.quit();


    }

    @Test // Logar antes de Iniciar os Testes
    public void telaAdmins() throws IOException, InterruptedException {




        File screenshot1 = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot1, new File("src/test/resources/utils/print/erroderequisicao.png"));
        WebElement elemento = (driver.findElement(By.xpath("//span[@data-v-10e00821='']")));
        String textoDoElemento = elemento.getText();
        // driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertThat(textoDoElemento, is("Admin")); // Confirma que na tela de Admin
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidencia.png"));


    }

    @Test
    public void testarEnvioDeMensagemSuporte() throws IOException {

    driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
            dropdown.click();
            WebElement pElement = driver.findElement(By.xpath("//p[normalize-space()='Suporte']"));
            pElement.click();
        }
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertThat(driver.findElement(By.cssSelector("a[data-v-41d7c25e].nuxt-link-exact-active.nuxt-link-active")).getText(), is("Contatar Suporte"));
        driver.findElement(By.xpath("//textarea[@placeholder='Escreva sua mensagem para nós']")).sendKeys("Preciso de ajuda.... ");
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.cssSelector("span[data-v-651599fe].button-text > span[data-v-651599fe]")).click();
        driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
        assertThat(driver.findElement(By.id("swal2-title")).getText(), is("Mensagem enviada com sucesso!"));
        File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidencia1.png"));
    }
    @Test
    public void testarAlterarCobrança() {

    driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
            dropdown.click();
            WebElement pElement = driver.findElement(By.xpath("//p[@class='infos-help']"));
            pElement.click();
        }
        //driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.findElement(By.xpath("//p[normalize-space()='Alterar Cobrança']")).click();
        assertEquals("Alterar Cobrança", driver.findElement(By.xpath("//p[normalize-space()='Alterar Cobrança']")).getText());

    }

}


