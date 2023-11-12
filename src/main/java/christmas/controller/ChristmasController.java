package christmas.controller;

import christmas.model.AllMenu;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {
    private AllMenu allMenu = new AllMenu();

    public void startOrder() {
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();

        OutputView.printOrderMenu(day, order);
        int totalOrderAmount = calculateTotalOrderAmount(order);
        OutputView.printTotalOrderAmount(totalOrderAmount);
        OutputView.printGiveaway(totalOrderAmount);

        int dDayDiscount = 0;
        int weekdayDiscount = 0;
        int weekendDiscount = 0;
        int specialDiscount = 0;
        int giveawayEvent = 0;
        if (day <= 25) {
            dDayDiscount = 1000 + 100 * (day - 1);
        }
        if (day % 7 != 1 && day % 7 != 2) {
            for (String menuName : order.keySet()) {
                if (allMenu.getAllMenu().get("디저트").containsKey(menuName)) {
                    weekdayDiscount += 2023 * order.get(menuName);
                }
            }
        }
        if (day % 7 == 1 || day % 7 == 2) {
            for (String menuName : order.keySet()) {
                if (allMenu.getAllMenu().get("메인").containsKey(menuName)) {
                    weekendDiscount += 2023 * order.get(menuName);
                }
            }
        }
        if (day % 7 == 3 || day == 25) {
            specialDiscount += 1000;
        }
        if (totalOrderAmount >= 120000) {
            giveawayEvent += 25000;
        }

        OutputView.printBenefitDetails(dDayDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayEvent);

        int totalBenefits = 0;
        totalBenefits = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayEvent;
        System.out.println("<총혜택 금액>");
        if (totalBenefits == 0) {
            System.out.println("0원");
        }
        if (totalBenefits != 0) {
            System.out.println("-" + String.format("%,d", totalBenefits) + "원");
        }
        System.out.println();

        int expectedPaymentAmount = totalOrderAmount - totalBenefits + giveawayEvent;
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(String.format("%,d", expectedPaymentAmount) + "원");
        System.out.println();

        System.out.println("<12월 이벤트 배지>");
        if (totalBenefits >= 20000) {
            System.out.println("산타");
        } else if (totalBenefits >= 10000) {
            System.out.println("트리");
        } else if (totalBenefits >= 5000) {
            System.out.println("별");
        } else if (totalBenefits < 5000) {
            System.out.println("없음");
        }

    }

    private int calculateTotalOrderAmount(Map<String, Integer> order) {
        int totalOrderAmount = 0;
        for (Map.Entry<String, Map<String, Integer>> category : allMenu.getAllMenu().entrySet()) {
            totalOrderAmount += calculateCategoryTotal(category.getValue(), order);
        }
        return totalOrderAmount;
    }

    private int calculateCategoryTotal(Map<String, Integer> menuInCategory, Map<String, Integer> order) {
        int categoryTotal = 0;
        for (String orderMenu : order.keySet()) {
            if (menuInCategory.containsKey(orderMenu)) {
                categoryTotal += order.get(orderMenu) * menuInCategory.get(orderMenu);
            }
        }
        return categoryTotal;
    }
}
