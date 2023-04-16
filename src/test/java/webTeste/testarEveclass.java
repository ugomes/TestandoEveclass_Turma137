package webTeste;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.io.FileUtils;
import org.junit.After;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;

public class testarEveclass {

   private WebDriver driver;

   @BeforeEach()
   public void setup() {

      WebDriverManager.chromedriver().setup();
      ChromeOptions options = new ChromeOptions();
      options.addArguments("--remote-allow-origins=*");
      driver = new ChromeDriver(options);
      driver.manage().window().maximize();

   }

   @AfterEach
   public void teardown() {
      driver.quit();
   }


   @Test // Logar antes de Iniciar os Testes
   public void loginPaginaEveclass() throws IOException {

      driver.get("https://testando.eveclass.com/pt/auth/entrar");
      driver.findElement(By.xpath("//input[@data-vv-as='Email']")).click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(By.xpath("//input[@data-vv-as='Email']")).sendKeys("uelton.gomes@uol.com.br");
      driver.findElement(By.xpath("//input[@type='password']")).click();
      driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Senha@123");
      driver.findElement(By.xpath("//span[normalize-space()='Entrar']")).click();
      WebElement elemento = (driver.findElement(By.xpath("//span[@data-v-10e00821='']")));
      String textoDoElemento = elemento.getText();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      assertThat(textoDoElemento, is("Admin")); // Confirma que na tela de Admin
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidencia.png"));


   }

   @Test
   public void testarEnvioDeMensagemSuporte() throws IOException {
      driver.get("https://testando.eveclass.com/pt/auth/entrar");
      driver.findElement(By.xpath("//input[@data-vv-as='Email']")).click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(By.xpath("//input[@data-vv-as='Email']")).sendKeys("");
      driver.findElement(By.xpath("//input[@type='password']")).click();
      driver.findElement(By.xpath("//input[@type='password']")).sendKeys("");
      driver.findElement(By.xpath("//span[normalize-space()='Entrar']")).click();
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);

      driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
      {
         WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
         dropdown.click();
         WebElement pElement = driver.findElement(By.xpath("//p[normalize-space()='Suporte']"));
         pElement.click();
      }
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      assertThat(driver.findElement(By.cssSelector("a[data-v-41d7c25e].nuxt-link-exact-active.nuxt-link-active")).getText(), is("Contatar Suporte"));
      driver.findElement(By.xpath("//textarea[@placeholder='Escreva sua mensagem para nós']")).sendKeys("Preciso de ajuda.... ");
      driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
      driver.findElement(By.cssSelector("span[data-v-651599fe].button-text > span[data-v-651599fe]")).click();
      driver.manage().timeouts().implicitlyWait(15, TimeUnit.SECONDS);
      assertThat(driver.findElement(By.id("swal2-title")).getText(), is("Mensagem enviada com sucesso!"));
      File screenshot = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
      FileUtils.copyFile(screenshot, new File("src/test/resources/utils/print/evidencia1.png"));
   }
}
