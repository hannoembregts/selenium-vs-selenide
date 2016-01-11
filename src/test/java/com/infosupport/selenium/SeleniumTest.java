package com.infosupport.selenium;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.hamcrest.CoreMatchers.hasItem;
import static org.junit.Assert.assertThat;

public class SeleniumTest {
    WebDriver driver;

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\development\\tools\\selenium-webdrivers\\chromedriver.exe");
        driver = new ChromeDriver();
    }

    @Test
    public void googleSuggestionsForInfoSupport() {
        driver.get("https://www.google.nl/?gfe_rd=cr&ei=wGmPVqThC8b8cdTGv9gG&gws_rd=ssl");

        WebElement query = driver.findElement(By.name("q"));
        query.sendKeys("Info Su");

        long end = System.currentTimeMillis() + 4000;
        while (System.currentTimeMillis() < end) {
            WebElement resultsDiv = driver.findElement(By.className("sbsb_a"));

            if (resultsDiv.isDisplayed()) {
                break;
            }
        }

        List<WebElement> allSuggestions = driver.findElements(By.className("sbsb_b"));
        List<String> allSuggestionTexts = allSuggestions.stream().map(WebElement::getText)
                .flatMap(suggestions -> Stream.of(suggestions.split("\n"))).collect(Collectors.toList());

        assertThat(allSuggestionTexts, hasItem("info support"));
    }

    @After
    public void tearDown() {
        driver.quit();
    }
}