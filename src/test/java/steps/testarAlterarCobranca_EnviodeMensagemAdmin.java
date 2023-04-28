package steps;

import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import java.time.Duration;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class testarAlterarCobranca_EnviodeMensagemAdmin {
    WebDriver driver;
    @Before
    public void setup() {
        // WebDriverManager.chromedriver().setup();
        // obrigatória a partir do Junit 4.8.1
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        System.setProperty("webdriver.chrome.driver", "drivers/chrome/chrome112.exe");
        //ChromeDriver driver = new ChromeDriver();
        driver = new ChromeDriver(options);

        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(60000));
    }
    @After
        public void tearDown(){
            driver.quit();


        }


    @Given("que acesso o site Eveclass")
    public void que_acesso_o_site_eveclass() {
        driver.get("https://testando.eveclass.com/pt/auth/entrar");
    }

    @When("clico no campo email")
    public void clico_no_campo_email() {
        driver.findElement(By.xpath("//input[@data-vv-as='Email']")).click();
    }

    @And("e digito um email valido")
    public void e_digito_um_email_valido() {
        driver.findElement(By.xpath("//input[@data-vv-as='Email']")).sendKeys("uelton.gomes@uol.com.br");

    }

    @And("clico no campo senha")
    public void clico_no_campo_senha() {
        driver.findElement(By.xpath("//input[@type='password']")).click();

    }

    @And("digito uma senha valida e clico no botão entrar")
    public void digito_uma_senha_valida() {
        driver.findElement(By.xpath("//input[@type='password']")).sendKeys("Senha@123");
        driver.findElement(By.xpath("//span[normalize-space()='Entrar']")).click();
    }

    @And("clico em suporte")
    public void clico_em_suporte() {
        driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
            dropdown.click();
            WebElement pElement = driver.findElement(By.xpath("//p[normalize-space()='Suporte']"));
            pElement.click();
        }


    }

    @And("digito uma mensagem no campo Mensagem")
    public void digito_uma_mensagem_no_campo_mensagem() {
        driver.findElement(By.xpath("//textarea[@placeholder='Escreva sua mensagem para nós']")).sendKeys("Preciso de ajuda.... ");

    }

    @And("clico no botão Enviar")
    public void clico_no_botão_enviar() {
        driver.findElement(By.cssSelector("span[data-v-651599fe].button-text > span[data-v-651599fe]")).click();



    }

    @Then("um pop up com a  frase  Mensagem enviada com sucesso é exibida")
    public void um_pop_up_com_a_frase_mensagem_enviada_com_sucesso_é_exibida() {
        assertThat(driver.findElement(By.id("swal2-title")).getText(), is("Mensagem enviada com sucesso!"));

    }

    @And("clico em transações")
    public void clico_em_transações() {
        driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down")).click();
        {
            WebElement dropdown = driver.findElement(By.cssSelector("i[data-v-f674d134][data-v-e270a120].dropdown-icon.fas.fa-chevron-down"));
            dropdown.click();
            WebElement pElement = driver.findElement(By.xpath("//p[@class='infos-help']"));
            pElement.click();
        }

    }
    @And("e clico em alterar cobrança")
    public void e_clico_em_alterar_cobrança() {
        driver.findElement(By.xpath("//p[normalize-space()='Alterar Cobrança']")).click();
    }
    @And("a tela de alterar cobrança é exibida")
    public void a_tela_de_alterar_cobrança_é_exibida() {
        assertEquals("Alterar Cobrança", driver.findElement(By.xpath("//p[normalize-space()='Alterar Cobrança']")).getText());

    }

}
