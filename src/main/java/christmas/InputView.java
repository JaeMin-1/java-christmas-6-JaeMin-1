package christmas;

import camp.nextstep.edu.missionutils.Console;
import java.util.HashMap;
import java.util.Map;

public class InputView {
    private static AllMenu allMenu = new AllMenu();

    public static int readDate() {
        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                return validateAllDate(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Map<String, Integer> readOrder() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                String input = Console.readLine();
                String[] tempOrder = input.split(",");
                return validateAllOrder(tempOrder);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static int validateAllDate(String input) {
        int day = InputValidation.validateNumber(input);
        Date date = new Date(day);
        return date.getDate();
    }

    private static Map<String, Integer> validateAllOrder(String[] input) {
        Map<String, Integer> tempOrder = new HashMap<>();
        for (String part : input) {
            String[] pair = part.split("-");
            String tempName = pair[0];
            String tempNum = pair[1];
            int count = InputValidation.validateNumber(tempNum);
            tempOrder.put(tempName, count);
        }
        Order order = new Order(tempOrder);
        return order.getOrder();
    }
}
