package ru.alfabank.props;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

public class BaseTest {
    /**
     * Инициализация selenide с настройками
     */
    public void setUp() {
        //WebDriverManager.chromedriver().setup();

    }

    @BeforeAll
    public void init(){
        setUp();
    }

    @AfterAll
    public void tearDown(){
       // Selenide.closeWebDriver();
    }
}
