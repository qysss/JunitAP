package test;

import com.codeborne.selenide.Selenide;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import java.util.List;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selectors.byText;
import static com.codeborne.selenide.Selenide.$;


public class GoogleWebTest {

    @BeforeEach
    void precondition() {
        Selenide.open("https://www.google.ru/");
    }

    @CsvSource({
            "selenide, https://selenide.org",
            "junit 5, https://junit.org"
    })
    /* @DisplayName и @Test не используем потому что эти 2 параметра выполняет - @ParameterizedTest */
    @ParameterizedTest(name = "checking for the presence of {1} after performing a search on {0}")
    @Tag("Blocker")
    void test(String searchQuery, String expectedUrl) {
        $("[name=q]").setValue(searchQuery).pressEnter();
        $("[id=search]").shouldHave(text(expectedUrl));
    }


    @Disabled
    @DisplayName("Проверка попапа загрузки фото")
    @Test
    @Tag("BLOCKER")
    void googlePhotoPopupTest() {
        $("img[alt='Camera search']").click();
        $(byText("Search any image with Google Lens")).shouldBe(visible);
    }

}
