package christmas.controller;

import christmas.model.AllMenu;
import christmas.util.Calculation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {
    public void startOrder() {
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();
        OutputView.printOrderMenu(day, order);
        int totalOrderAmount = calculateTotalOrderAmount(order);
        int dDayDiscount = Calculation.calculateDDayDiscount(day);
        int weekdayDiscount = Calculation.calculateWeekdayDiscount(order, day);
        int weekendDiscount = Calculation.calculateWeekendDiscount(order, day);
        int specialDiscount = Calculation.calculateSpecialDiscount(day);
        int giveawayEvent = Calculation.calculateGiveawayEvent(totalOrderAmount);
        int totalBenefits = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayEvent;
        showBenefits(dDayDiscount, weekdayDiscount, weekendDiscount, specialDiscount, giveawayEvent, totalOrderAmount,
                totalBenefits);
        showFinalResult(totalOrderAmount, giveawayEvent, totalBenefits);
    }

    private int calculateTotalOrderAmount(Map<String, Integer> order) {
        AllMenu allMenu = new AllMenu();
        int totalOrderAmount = 0;
        Map<String, Map<String, Integer>> allMenuMap = allMenu.getAllMenu();
        for (Map.Entry<String, Map<String, Integer>> category : allMenuMap.entrySet()) {
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

    private void showBenefits(int dDayDiscount, int weekdayDiscount, int weekendDiscount,
                              int specialDiscount, int giveawayEvent, int totalOrderAmount, int totalBenefits) {
        OutputView.printTotalOrderAmount(totalOrderAmount);
        OutputView.printGiveaway(totalOrderAmount);

        OutputView.printBenefitDetails(dDayDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayEvent);
        OutputView.printTotalBenefits(totalBenefits);
    }

    private void showFinalResult(int totalOrderAmount, int giveawayEvent, int totalBenefits) {
        int expectedPaymentAmount = totalOrderAmount - totalBenefits + giveawayEvent;
        OutputView.printExpectedPaymentAmount(expectedPaymentAmount);
        OutputView.printDecemberEventBadge(totalBenefits);
    }
}
