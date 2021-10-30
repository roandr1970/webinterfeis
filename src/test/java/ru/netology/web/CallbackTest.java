package ru.netology.web;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

import static org.openqa.selenium.By.cssSelector;

class CallbackTest {
    private WebDriver driver;

    @BeforeAll
    static void setUpAll(){
        WebDriverManager.chromedriver().setup();
        //System.setProperty("webdriver.chrome.driver", "./driver/chromedriver.exe");
    }

    @ BeforeEach
    void setUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        options.addArguments("--headless");
        driver = new ChromeDriver(options);
    }

    @AfterEach
    void tearDown() {
        driver.quit();
        driver = null;
    }

    @Test
    void shouldTestSomething() {
        driver.get("http://localhost:9999");
        driver.findElement(cssSelector("[type='text']")).sendKeys("Иван Тапочкин");
        driver.findElement(cssSelector("[type='tel']")).sendKeys("+79991112222");
        driver.findElement(cssSelector("[class='checkbox__box']")).click();
        driver.findElement(cssSelector("[type='button']")).click();
        String actual = driver.findElement(cssSelector("[class='paragraph paragraph_theme_alfa-on-white']")).getText().strip();
        String expected  = "Ваша заявка успешно отправлена! Наш менеджер свяжется с вами в ближайшее время.";
        Assertions.assertEquals(expected,actual);
    }
}
