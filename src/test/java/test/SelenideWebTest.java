package test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;


public class SelenideWebTest {
    private static final String BASE_URL = "https://www.jetbrains.com/";
    private static final String LANGUAGE_BUTTON_LOCATOR = "button[data-test=language-picker]";
    private static final String LANGUAGE_GRID_LOCATOR = "div[data-test=language-grid] [data-test=list-item]";
    /*В следующих локаторах опускался по дивам ибо сайт генерит свои классы*/
    private static final String HEADER_LOCATOR = "div#head-section>section>div>div>div>h1";
    private static final String FOOTER_LOCATOR = "div#footer-container>footer>div";
    private static final String INVENTORY_LOCATOR = "div#head-section>section>div>div:nth-of-type(4)>div:nth-of-type(2)>ul";

    @BeforeEach
    void preliminaryOpeningOfSite() {
        Selenide.open(BASE_URL);
    }

    @ValueSource(strings = {
            "JavaScript",
            ".NET",
            "Java & JVM",
            "C++",
            "macOS & iOS",
            "Python & Django",
            "PHP",
            "Ruby & Rails",
            "Go",
            "Kotlin",
            "SQL",
            "See all tools"})
    @ParameterizedTest(name = "Displaying buttons {0} in the navigation menu of the site header")
    @Tag("Critical")
    void testingPresenceButtonsInNavigationMenu(String inventory) {
        $(INVENTORY_LOCATOR).shouldHave(text(inventory));
    }

    @CsvSource(value = {"" +
            "Deutsch, Unentbehrliche Tools für Software-Entwickler*innen und Teams",
            "English, Essential tools for software developers and teams"
    })
    @ParameterizedTest(name = "Checking for the presence of text ({1}) in the head section for the selected language {0}")
    @Tag("Major")
    void checkTextInHeader(String locale, String text) {
        $(LANGUAGE_BUTTON_LOCATOR).click();
        $$(LANGUAGE_GRID_LOCATOR).find(text(locale)).click();
        $(HEADER_LOCATOR).shouldHave(text(text));
    }

    static Stream<Arguments> checkingCorrectNameButtonsInFooterSiteForDifferentLanguages() {
        return Stream.of(
                Arguments.of("English", List.of("Privacy & Security", "Terms of Use", "Trademarks", "Legal", "Genuine Tools")),
                Arguments.of("Deutsch", List.of("Impressum", "Datenschutz und Sicherheit", "Nutzungsbedingungen", "Trademarks", "Rechtliches", "Originalsoftware")),
                Arguments.of("Español", List.of("Privacidad y seguridad", "Condiciones de uso", "Marcas comerciales", "Información legal", "Herramientas genuinas")),
                Arguments.of("Русский", List.of("Защита персональных данных и безопасность", "Правила использования", "Товарные знаки", "Юридическая информация", "Оригинальные инструменты"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Checking the correct name of the buttons from the list {1} on the JetBrains website in the locale {0}")
    @Tag("Minor")
    void checkingCorrectNameButtonsInFooterSiteForDifferentLanguages(String locale, List<String> buttons) {
        $(LANGUAGE_BUTTON_LOCATOR).click();
        $$(LANGUAGE_GRID_LOCATOR).find(text(locale)).click();
        for (String button : buttons) {
            $$(FOOTER_LOCATOR).find(text(button)).shouldBe(visible);
        }
    }
}
