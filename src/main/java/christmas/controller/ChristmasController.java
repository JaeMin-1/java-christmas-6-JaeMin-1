package christmas.controller;

import christmas.model.AllMenu;
import christmas.util.Calculation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {
    private int dDayDiscount;
    private int weekdayDiscount;
    private int weekendDiscount;
    private int specialDiscount;
    private int giveawayEvent;
    private int totalBenefits;

    public void startOrder() {
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();
        OutputView.printOrderMenu(day, order);
        int totalOrderAmount = calculateTotalOrderAmount(order);

        showBenefits(day, order, totalOrderAmount);
        showFinalResult(totalOrderAmount);
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

    private void showBenefits(int day, Map<String, Integer> order, int totalOrderAmount) {
        OutputView.printTotalOrderAmount(totalOrderAmount);
        OutputView.printGiveaway(totalOrderAmount);
        calculateBenefits(day, order, totalOrderAmount);

        OutputView.printBenefitDetails(dDayDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayEvent);
        totalBenefits = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayEvent;
        OutputView.printTotalBenefits(totalBenefits);
    }

    private void calculateBenefits(int day, Map<String, Integer> order, int totalOrderAmount) {
        dDayDiscount = Calculation.calculateDDayDiscount(day);
        weekdayDiscount = Calculation.calculateWeekdayDiscount(order, day);
        weekendDiscount = Calculation.calculateWeekendDiscount(order, day);
        specialDiscount = Calculation.calculateSpecialDiscount(day);
        giveawayEvent = Calculation.calculateGiveawayEvent(totalOrderAmount);
    }

    private void showFinalResult(int totalOrderAmount) {
        int expectedPaymentAmount = totalOrderAmount - totalBenefits + giveawayEvent;
        OutputView.printExpectedPaymentAmount(expectedPaymentAmount);
        OutputView.printDecemberEventBadge(totalBenefits);
    }
}
