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

    public static int readOrder() {
        while (true) {
            try {
                System.out.println("주문하실 메뉴를 메뉴와 개수를 알려 주세요. (e.g. 해산물파스타-2,레드와인-1,초코케이크-1)");
                Map<String, Integer> order = new HashMap<>();
                String input = Console.readLine();
                String[] tempOrder = input.split(",");
                for (String part : tempOrder) {
                    String[] pair = part.split("-");
                    String tempName = pair[0];
                    String tempNum = pair[1];
                    int count = InputValidation.validateNumber(tempNum);
                    order.put(tempName, count);
                }
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
}
