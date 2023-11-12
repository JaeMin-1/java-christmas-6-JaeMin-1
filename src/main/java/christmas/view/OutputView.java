package christmas.view;

import java.util.Map;

public class OutputView {
    public static void printOrderMenu(int day, Map<String, Integer> order) {
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            System.out.println(orderMenu.getKey() + " " + orderMenu.getValue() + "개");
        }
        System.out.println();
    }

    public static void printTotalOrderAmount(int totalOrderAmount) {
        System.out.println("<할인 전 총주문 금액>");
        System.out.println(String.format("%,d", totalOrderAmount) + "원");
        System.out.println();
    }

    public static void printGiveaway(int totalOrderAmount) {
        System.out.println("<증정 메뉴>");
        if (totalOrderAmount >= 120000) {
            System.out.println("삼페인 1개");
        }
        if (totalOrderAmount < 10000) {
            System.out.println("없음");
        }
        System.out.println();
    }

    public static void printBenefitDetails(int dDayDiscount, int weekdayDiscount, int weekendDiscount,
                                           int specialDiscount, int giveawayEvent) {
        System.out.println("<혜택 내역>");
        printNothing(dDayDiscount, weekdayDiscount, weekendDiscount,
                specialDiscount, giveawayEvent);
        printDDayDiscount(dDayDiscount);
        printWeekdayDiscount(weekdayDiscount);
        printWeekendDiscount(weekendDiscount);
        printSpecialDiscount(specialDiscount);
        printGiveawayEvent(giveawayEvent);
        System.out.println();
    }

    private static void printNothing(int dDayDiscount, int weekdayDiscount, int weekendDiscount,
                                     int specialDiscount, int giveawayEvent) {
        if (dDayDiscount == 0 && weekdayDiscount == 0 && weekendDiscount == 0 && specialDiscount == 0
                && giveawayEvent == 0) {
            System.out.println("없음");
        }
    }

    private static void printDDayDiscount(int dDayDiscount) {
        if (dDayDiscount != 0) {
            System.out.println("크리스마스 디데이 할인: -" + String.format("%,d", dDayDiscount) + "원");
        }
    }

    private static void printWeekdayDiscount(int weekdayDiscount) {
        if (weekdayDiscount != 0) {
            System.out.println("평일 할인: -" + String.format("%,d", weekdayDiscount) + "원");
        }
    }

    private static void printWeekendDiscount(int weekendDiscount) {
        if (weekendDiscount != 0) {
            System.out.println("주말 할인: -" + String.format("%,d", weekendDiscount) + "원");
        }
    }

    private static void printSpecialDiscount(int specialDiscount) {
        if (specialDiscount != 0) {
            System.out.println("특별 할인: -" + String.format("%,d", specialDiscount) + "원");
        }
    }

    private static void printGiveawayEvent(int giveawayEvent) {
        if (giveawayEvent != 0) {
            System.out.println("증정 이벤트: -" + String.format("%,d", giveawayEvent) + "원");
        }
    }
}
