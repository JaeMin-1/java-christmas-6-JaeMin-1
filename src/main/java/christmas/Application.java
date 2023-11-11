package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class Application {
    public static void main(String[] args) {
        // TODO: 프로그램 구현
        Map<String, Map<String, Integer>> allMenu = new HashMap<>();

        Map<String, Integer> appetizer = new HashMap<>();
        appetizer.put("양송이수프", 6000);
        appetizer.put("타파스", 5500);
        appetizer.put("시저샐러드", 8000);
        allMenu.put("애피타이저", appetizer);

        Map<String, Integer> main = new HashMap<>();
        main.put("티본스테이크", 55000);
        main.put("바비큐립", 54000);
        main.put("해산물파스타", 35000);
        main.put("크리스마스파스타", 25000);
        allMenu.put("메인", main);

        Map<String, Integer> dessert = new HashMap<>();
        dessert.put("초코케이크", 15000);
        dessert.put("아이스크림", 5000);
        allMenu.put("디저트", dessert);

        Map<String, Integer> beverage = new HashMap<>();
        beverage.put("제로콜라", 3000);
        beverage.put("레드와인", 60000);
        beverage.put("샴페인", 25000);
        allMenu.put("음료", beverage);

        int day = 0;
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                day = Integer.parseInt(input);
                if (day < 1 || day > 31) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

        Map<String, Integer> order = new HashMap<>();
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String input = Console.readLine();
                String[] tempOrder = input.split(",");
                for (String part : tempOrder) {
                    String[] pair = part.split("-");
                    String tempName = pair[0];
                    boolean checkMenu = false;
                    for (Map.Entry<String, Map<String, Integer>> category : allMenu.entrySet()) {
                        Map<String, Integer> menuInCategory = category.getValue();
                        if (menuInCategory.containsKey(tempName)) {
                            checkMenu = true;
                        }
                    }
                    if (!checkMenu) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }

                    String tempNum = pair[1];
                    int tempCount = Integer.parseInt(tempNum);
                    if (tempCount < 1) {
                        throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
                    }
                    order.put(tempName, tempCount);
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
        System.out.println("12월 " + day + "일에 우테코 식당에서 받을 이벤트 혜택 미리 보기!");
        System.out.println();
        System.out.println("<주문 메뉴>");
        for (Map.Entry<String, Integer> orderMenu : order.entrySet()) {
            System.out.println(orderMenu.getKey() + " " + orderMenu.getValue() + "개");
        }
        System.out.println();

        System.out.println("<할인 전 총주문 금액>");
        int totalOrderAmount = 0;
        for (Map.Entry<String, Map<String, Integer>> category : allMenu.entrySet()) {
            Map<String, Integer> menuInCategory = category.getValue();
            for (String orderMenu : order.keySet()) {
                if (menuInCategory.containsKey(orderMenu)) {
                    totalOrderAmount += order.get(orderMenu) * menuInCategory.get(orderMenu);
                }
            }
        }
        System.out.println(String.format("%,d", totalOrderAmount));
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
                if (dessert.containsKey(menuName)) {
                    weekdayDiscount += 2023 * order.get(menuName);
                }
            }
        }
        if (day % 7 == 1 || day % 7 == 2) {
            for (String menuName : order.keySet()) {
                if (main.containsKey(menuName)) {
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
        System.out.println("크리스마스 디데이 할인: -" + String.format("%,d", dDayDiscount));
        System.out.println("평일 할인: -" + String.format("%,d", weekdayDiscount));
        System.out.println("주말 할인: -" + String.format("%,d", weekendDiscount));
        System.out.println("특별 할인: -" + String.format("%,d", specialDiscount));
        System.out.println("증정 이벤트: -" + String.format("%,d", giveawayEvent));
        System.out.println();

        int totalBenefits = 0;
        totalBenefits = dDayDiscount + weekdayDiscount + weekendDiscount + specialDiscount + giveawayEvent;
        System.out.println("<총혜택 금액>");
        System.out.println("-" + String.format("%,d", totalBenefits));
        System.out.println();

        int expectedPaymentAmount = totalOrderAmount - totalBenefits + giveawayEvent;
        System.out.println("<할인 후 예상 결제 금액>");
        System.out.println(expectedPaymentAmount);
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
