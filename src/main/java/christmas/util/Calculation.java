package christmas.util;

import christmas.model.AllMenu;
import java.util.Map;

public class Calculation {
    public static int calculateDDayDiscount(int day) {
        if (day <= 25) {
            return 1000 + 100 * (day - 1);
        }
        return 0;
    }

    public static int calculateWeekdayDiscount(Map<String, Integer> order, int day) {
        int discount = 0;
        if (day % 7 != 1 && day % 7 != 2) {
            discount = calculateDiscountForMenuCategory(order, "디저트", 2023);
        }
        return discount;
    }

    public static int calculateWeekendDiscount(Map<String, Integer> order, int day) {
        int discount = 0;
        if (day % 7 == 1 || day % 7 == 2) {
            discount = calculateDiscountForMenuCategory(order, "메인", 2023);
        }
        return discount;
    }

    public static int calculateSpecialDiscount(int day) {
        if (day % 7 == 3 || day == 25) {
            return 1000;
        }
        return 0;
    }

    public static int calculateGiveawayEvent(int totalOrderAmount) {
        if (totalOrderAmount >= 120000) {
            return 25000;
        }
        return 0;
    }

    private static int calculateDiscountForMenuCategory(Map<String, Integer> order, String categoryName,
                                                        int discountRate) {
        AllMenu allMenu = new AllMenu();
        Map<String, Map<String, Integer>> allMenuMap = allMenu.getAllMenu();
        int discount = 0;
        for (String menuName : order.keySet()) {
            if (allMenuMap.get(categoryName).containsKey(menuName)) {
                discount += discountRate * order.get(menuName);
            }
        }
        return discount;
    }
}
