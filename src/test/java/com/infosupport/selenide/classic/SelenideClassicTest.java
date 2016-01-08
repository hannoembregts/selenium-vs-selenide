package com.infosupport.selenide.classic;

import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.Condition.*;

public class SelenideClassicTest {
    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "C:\\development\\tools\\selenium-webdrivers\\chromedriver.exe");
        System.setProperty("selenide.browser", "chrome");
    }

    @Test
    public void googleSuggestionsForInfoSupport() {
        open("https://www.google.nl/?gfe_rd=cr&ei=wGmPVqThC8b8cdTGv9gG&gws_rd=ssl");
        $(By.name("q")).setValue("Info Su");
        $(".sbsb_b").shouldHave(text("Info Support"));
    }
}
