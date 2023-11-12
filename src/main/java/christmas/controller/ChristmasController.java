package christmas.controller;

import christmas.model.Calculation;
import christmas.view.InputView;
import christmas.view.OutputView;
import java.util.Map;

public class ChristmasController {
    public void startOrder() {
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();
        OutputView.printOrderMenu(day, order);
        int totalOrderAmount = Calculation.calculateTotalOrderAmount(order);
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
