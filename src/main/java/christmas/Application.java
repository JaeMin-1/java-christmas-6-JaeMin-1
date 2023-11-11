package christmas;

import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        AllMenu allMenu = new AllMenu();
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();

        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            System.out.println(orderMenu.getKey() + " " + orderMenu.getValue() + "개");
        }
        System.out.println();

        System.out.println("<할인 전 총주문 금액>");
        int totalOrderAmount = 0;
        for (Map.Entry<String, Map<String, Integer>> category : allMenu.getAllMenu().entrySet()) {
            Map<String, Integer> menuInCategory = category.getValue();
            for (String orderMenu : order.keySet()) {
                if (menuInCategory.containsKey(orderMenu)) {
                    totalOrderAmount += order.get(orderMenu) * menuInCategory.get(orderMenu);
                }
            }
        }
        System.out.println(String.format("%,d", totalOrderAmount) + "원");
        System.out.println();

        System.out.println("<증정 메뉴>");
        if (totalOrderAmount >= 120000) {
            System.out.println("삼페인 1개");
        }
        System.out.println();

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

        System.out.println("<혜택 내역>");
        if (dDayDiscount != 0) {
            System.out.println("크리스마스 디데이 할인: -" + String.format("%,d", dDayDiscount) + "원");
        }
        if (weekdayDiscount != 0) {
            System.out.println("평일 할인: -" + String.format("%,d", weekdayDiscount) + "원");
        }
        if (weekendDiscount != 0) {
            System.out.println("주말 할인: -" + String.format("%,d", weekendDiscount) + "원");
        }
        if (specialDiscount != 0) {
            System.out.println("특별 할인: -" + String.format("%,d", specialDiscount) + "원");
        }
        if (giveawayEvent != 0) {
            System.out.println("증정 이벤트: -" + String.format("%,d", giveawayEvent) + "원");
        }
        System.out.println();

        int totalBenefits = 0;
        totalBenefits = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayEvent;
        System.out.println("<총혜택 금액>");
        System.out.println("-" + String.format("%,d", totalBenefits) + "원");
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
        }
    }
}
