package christmas.view;

import christmas.constants.OutputMessage;
import java.util.Map;

public class OutputView {
    public static void printOrderMenu(int day, Map<String, Integer> order) {
        System.out.printf(OutputMessage.ORDER_MENU_PREVIEW.getMessage(), day);
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            System.out.println(orderMenu.getKey() + " " + orderMenu.getValue() + "개");
        }
        System.out.println();
    }

    public static void printTotalOrderAmount(int totalOrderAmount) {
        System.out.printf(OutputMessage.TOTAL_ORDER_AMOUNT.getMessage(), totalOrderAmount);
    }

    public static void printGiveaway(int totalOrderAmount) {
        System.out.println(OutputMessage.GIVEAWAY_MENU.getMessage());
        if (totalOrderAmount >= 120000) {
            System.out.println(OutputMessage.ONE_CHAMPAGNE.getMessage());
        }
        if (totalOrderAmount < 10000) {
            System.out.println(OutputMessage.NOTHING.getMessage());
        }
        System.out.println();
    }

    public static void printBenefitDetails(int dDayDiscount, int weekdayDiscount, int weekendDiscount,
                                           int specialDiscount, int giveawayEvent) {
        System.out.println(OutputMessage.BENEFIT_DETAILS.getMessage());
        printNothing(dDayDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayEvent);
        printDiscount(dDayDiscount, OutputMessage.D_DAY);
        printDiscount(weekdayDiscount, OutputMessage.WEEKDAY);
        printDiscount(weekendDiscount, OutputMessage.WEEKEND);
        printDiscount(specialDiscount, OutputMessage.SPECIAL);
        printDiscount(giveawayEvent, OutputMessage.GIVEAWAY);
        System.out.println();
    }

    public static void printTotalBenefits(int totalBenefits) {
        System.out.println(OutputMessage.TOTAL_BENEFITS.getMessage());
        if (totalBenefits == 0) {
            System.out.println(OutputMessage.ZERO.getMessage());
        }
        if (totalBenefits != 0) {
            System.out.println("-" + String.format("%,d", totalBenefits) + "원");
        }
        System.out.println();
    }

    public static void printExpectedPaymentAmount(int expectedPaymentAmount) {
        System.out.printf(OutputMessage.EXPECTED_PAYMENT_AMOUNT.getMessage(), expectedPaymentAmount);
    }

    public static void printDecemberEventBadge(int totalBenefits) {
        System.out.println(OutputMessage.DECEMBER_EVENT_BADGE.getMessage());
        if (totalBenefits >= 20000) {
            System.out.println(OutputMessage.SANTA.getMessage());
        } else if (totalBenefits >= 10000) {
            System.out.println(OutputMessage.TREE.getMessage());
        } else if (totalBenefits >= 5000) {
            System.out.println(OutputMessage.STAR.getMessage());
        } else if (totalBenefits < 5000) {
            System.out.println(OutputMessage.NOTHING.getMessage());
        }
    }

    private static void printNothing(int dDayDiscount, int weekdayDiscount, int weekendDiscount,
                                     int specialDiscount, int giveawayEvent) {
        if (dDayDiscount == 0 && weekdayDiscount == 0 && weekendDiscount == 0 && specialDiscount == 0
                && giveawayEvent == 0) {
            System.out.println(OutputMessage.NOTHING.getMessage());
        }
    }

    private static void printDiscount(int discount, OutputMessage outputMessage) {
        if (discount != 0) {
            System.out.println(outputMessage.getMessage() + ": -" + String.format("%,d", discount) + "원");
        }
    }
}
