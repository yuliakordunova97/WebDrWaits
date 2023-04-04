import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.*;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.BeforeSuite;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class WebDriverWaits {
    private final String BASE_URL = "https://devexpress.github.io/devextreme-reactive/react/grid/docs/guides/filtering/";
    private final Long IMPLICITLY_WAIT_SECONDS = 10L;
    private final Long ONE_SECOND_DELAY = 1000L;
    private WebDriver driver;


    private void presentationSleep() {
        presentationSleep(1);
    }


    private void presentationSleep(int seconds) {
        try {
            Thread.sleep(seconds * ONE_SECOND_DELAY); // For Presentation ONLY
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @BeforeSuite
    public void beforeSuite() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeClass
    public void beforeClass() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
        driver.manage().window().maximize();
    }

    @AfterClass(alwaysRun = true)
    public void afterClass() {
        presentationSleep(); // For Presentation ONLY
        if (driver != null) {
            driver.quit();
        }
    }

    @BeforeMethod
    public void beforeMethod() {
        driver.get(BASE_URL);
        presentationSleep(); // For Presentation ONLY
        //
        closePopup1();
    }

    @AfterMethod
    public void afterMethod() {
        presentationSleep(); // For Presentation ONLY
        // logout; clear cache; delete cookie; delete session;
        // Save Screen;
    }

    private void closePopup1() {
        presentationSleep(); // For Presentation ONLY
        driver.manage().timeouts().implicitlyWait(5, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
        long timeStart = System.currentTimeMillis();
        List<WebElement> footerButton = driver.findElements(By.xpath("//footer[contains(@class,'cookie')]//button"));
        System.out.println("***footerButton.size() = " + footerButton.size());
        System.out.println("***time = " + (System.currentTimeMillis() - timeStart));
        if (footerButton.size() > 0) {
            footerButton.get(0).click();
            presentationSleep(); // For Presentation ONLY
        }
        driver.manage().timeouts().implicitlyWait(IMPLICITLY_WAIT_SECONDS, TimeUnit.SECONDS);
        //driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(IMPLICITLY_WAIT_SECONDS));
    }

    @Test
    public void findCity() {
        WebElement position = driver.findElement(By.xpath("//div[contains(@data-options,'filter-row')]//iframe"));


        Actions action = new Actions(driver);
        action.moveToElement(position).perform();
        presentationSleep(2);
        driver.switchTo() .frame(driver.findElement(By.xpath("//div[contains(@data-options,'filter-row')]//iframe")));

        presentationSleep(2);

        driver.findElement(By.xpath("//th[3]//input")).click();
        driver.findElement(By.xpath("//th[3]//input")).sendKeys("L");
        presentationSleep(2); // For Presentation ONLY

        driver.findElement(By.xpath("//td[text()='Las Vegas']")).click();
        driver.findElement(By.xpath("//td[text()='London']")).click();

        presentationSleep();
    }

}
