package test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Tag;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.List;
import java.util.stream.Stream;

import static com.codeborne.selenide.CollectionCondition.texts;
import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;


public class SelenideWebTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://www.jetbrains.com/");
    }


    static Stream<Arguments> selenideButtonsTest() {
        return Stream.of(
                Arguments.of("English", List.of("Privacy & Security", "Terms of Use", "Trademarks", "Legal", "Genuine Tools")),
                Arguments.of("Deutsch", List.of("Impressum", "Datenschutz und Sicherheit", "Nutzungsbedingungen", "Trademarks", "Rechtliches", "Originalsoftware")),
                Arguments.of("Español", List.of("Privacidad y seguridad", "Condiciones de uso", "Marcas comerciales", "Información legal", "Herramientas genuinas")),
                Arguments.of("Русский", List.of("Защита персональных данных и безопасность", "Правила использования", "Товарные знаки", "Юридическая информация", "Оригинальные инструменты"))
        );
    }

    @MethodSource
    @ParameterizedTest(name = "Проверка наличия кнопок из списка {1} на сайте селенида в локали {0}")
    @Tag("Minor")
    void selenideButtonsTest(String locale, List<String> buttons) {
        $("button[data-test=language-picker]").click();
        $$("div[data-test=language-grid] [data-test=list-item]").find(text(locale)).click();
        $$("footer[data-test=footer]>div>div:nth-of-type(2)>div:nth-of-type(3)>div a").shouldHave(texts(buttons));
    }


}
