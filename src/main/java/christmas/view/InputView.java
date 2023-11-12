package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.model.Date;
import christmas.model.InputValidation;
import christmas.model.Order;
import java.util.HashMap;
import java.util.Map;

public class InputView {
    public static int readDate() {
        while (true) {
            try {
                String input = inputDate();
                return validateAllDate(input);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static Map<String, Integer> readOrder() {
        while (true) {
            try {
                String[] tempOrder = inputOrder();
                return validateAllOrder(tempOrder);
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }
    }

    private static String inputDate() {
        System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
        return Console.readLine();
    }

    private static int validateAllDate(String input) {
        int day = InputValidation.validateDate(input);
        Date date = new Date(day);
        return date.getDate();
    }

    private static String[] inputOrder() {
        System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2, 레드와인-1, 초코케이크-1)");
        String input = Console.readLine();
        String[] order = input.split(",");
        return order;
    }

    private static Map<String, Integer> validateAllOrder(String[] input) {
        Map<String, Integer> tempOrder = new HashMap<>();
        processInputOrder(input, tempOrder);
        validateDuplication(input, tempOrder);
        Order order = new Order(tempOrder);
        return order.getOrder();
    }

    private static void processInputOrder(String[] input, Map<String, Integer> tempOrder) {
        for (String part : input) {
            String[] pair = part.split("-");
            String tempName = pair[0];
            String tempNum = pair[1];
            int count = InputValidation.validateCount(tempNum);
            tempOrder.put(tempName, count);
        }
    }

    private static void validateDuplication(String[] input, Map<String, Integer> tempOrder) {
        if (input.length != tempOrder.size()) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
        }
    }
}
