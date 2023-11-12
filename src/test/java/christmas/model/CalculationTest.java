package christmas.model;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CalculationTest {
    private AllMenu allMenu;

    @BeforeEach
    void setUp() {
        allMenu = new AllMenu();
    }

    @DisplayName("할인 전 총주문 금액 계산")
    @Test
    void calculateTotalOrderAmount() {
        Map<String, Integer> order = new HashMap<>();
        order.put("양송이수프", 2);
        order.put("티본스테이크", 1);

        int totalOrderAmount = Calculation.calculateTotalOrderAmount(order);

        assertThat(totalOrderAmount).isEqualTo(2 * 6000 + 1 * 55000);
    }

    @DisplayName("크리스마스 디데이 할인 계산")
    @Test
    void calculateDDayDiscount() {
        int day = 15;

        int discount = Calculation.calculateDDayDiscount(day);

        assertThat(discount).isEqualTo(1000 + 100 * (day - 1));
    }

    @DisplayName("평일 할인 계산")
    @Test
    void calculateWeekdayDiscount() {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        order.put("초코케이크", 2);
        int day = 6;

        int discount = Calculation.calculateWeekdayDiscount(order, day);

        assertThat(discount).isEqualTo(2 * 2023);
    }

    @DisplayName("주말 할인 계산")
    @Test
    void calculateWeekendDiscount() {
        Map<String, Integer> order = new HashMap<>();
        order.put("티본스테이크", 1);
        order.put("초코케이크", 2);
        int day = 15;

        int discount = Calculation.calculateWeekendDiscount(order, day);

        assertThat(discount).isEqualTo(1 * 2023);
    }

    @DisplayName("특별 할인 계산")
    @Test
    void calculateSpecialDiscount() {
        int day = 25;

        int result = Calculation.calculateSpecialDiscount(day);

        assertThat(result).isEqualTo(1000);
    }

    @DisplayName("증정 이벤트 계산")
    @Test
    void calculateGiveawayEvent() {
        int totalOrderAmount = 130000;

        int result = Calculation.calculateGiveawayEvent(totalOrderAmount);

        assertThat(result).isEqualTo(25000);
    }
}
