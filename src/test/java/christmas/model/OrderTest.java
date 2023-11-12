package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

import christmas.constants.ErrorMessage;
import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class OrderTest {
    @DisplayName("메뉴판에 없을 경우 예외 처리")
    @Test
    void notInMenu() {
        Map<String, Integer> order = new HashMap<>();
        order.put("레드와인", 1);
        order.put("안심스테이크", 2);

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("메뉴의 개수가 1개 미만 숫자일 시 예외 처리")
    @Test
    void notNumericOrderCount() {
        Map<String, Integer> order = new HashMap<>();
        order.put("레드와인", 0);
        order.put("안심스테이크", 2);

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("총 개수가 20개 넘을 시 예외 처리")
    @Test
    void overMaxOrderCount() {
        Map<String, Integer> order = new HashMap<>();
        order.put("레드와인", 19);
        order.put("안심스테이크", 2);

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }

    @DisplayName("음료만 주문 시 예외 처리")
    @Test
    void onlyBeverage() {
        Map<String, Integer> order = new HashMap<>();
        order.put("레드와인", 1);
        order.put("제로콜라", 2);

        assertThatThrownBy(() -> new Order(order))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(ErrorMessage.INVALID_ORDER.getMessage());
    }
}
