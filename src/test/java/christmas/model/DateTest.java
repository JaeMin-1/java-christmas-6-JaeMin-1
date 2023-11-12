package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatCode;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.constants.ErrorMessage;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class DateTest {
    @DisplayName("성공하는 케이스")
    @Test
    void validDate() {
        int validDay = 15;

        assertThatCode(() -> new Date(validDay))
                .doesNotThrowAnyException();
    }

    @DisplayName("범위를 벗어난 경우 예외 처리")
    @Test
    void invalidDate() {
        int invalidDay = 32;

        assertThatThrownBy(() -> new Date(invalidDay))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_DATE.getMessage());
    }
}
