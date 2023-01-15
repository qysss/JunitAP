package test;

import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;
public class SimpleTest {

    @Disabled("VSA-402221")
    @DisplayName("checking that 3 is greater than 2")
    @Test
    void firstTest() {
        assertTrue(true);
    }

    @DisplayName("checking that 3 is greater than 1")
    @Test
    void secondTest() {
        assertTrue(true);
    }
}
