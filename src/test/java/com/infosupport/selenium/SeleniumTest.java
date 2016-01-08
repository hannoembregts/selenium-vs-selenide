package com.infosupport.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;

public class SeleniumTest {
    WebDriver driver;

    @Before
    public void setUp() {
        driver = new ChromeDriver();
    }

    @Test
    public void googleSuggestionsForInfoSupport() {
        // googleSuggestPage.navigate();
        driver.get("https://www.google.nl/?gfe_rd=cr&ei=wGmPVqThC8b8cdTGv9gG&gws_rd=ssl");

        // suggestPage.enterQueryString("Info Su");
        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Info Su");

        // suggestPage.waitForSuggestionsDiv();
        long end = System.currentTimeMillis() + 5000;
        while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.className("sbsb_a"));

            if (resultsDiv.isDisplayed()) {
                break;
            }
        }

        List<WebElement> allSuggestions = driver.findElements(By.className("sbsb_b"));
        for (WebElement suggestion: allSuggestions) {
            System.out.println(suggestion.getText());
        }
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}
