package christmas.view;

import camp.nextstep.edu.missionutils.Console;
import christmas.constants.ErrorMessage;
import christmas.constants.InputMessage;
import christmas.model.Date;
import christmas.model.Order;
import christmas.util.InputValidation;
import java.util.HashMap;
import java.util.Map;

public class InputView {
    public static int readDate() {
        System.out.println(InputMessage.START_HEADER.getMessage());
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
        System.out.println(InputMessage.EXPECTED_VISIT_DATE.getMessage());
        return Console.readLine();
    }

    private static int validateAllDate(String input) {
        int day = InputValidation.validateDate(input);
        Date date = new Date(day);
        return date.getDate();
    }

    private static String[] inputOrder() {
        System.out.println(InputMessage.ORDER_MENU.getMessage());
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
            outOfMenuExampleFormat(pair);
            String tempName = pair[0];
            String tempNum = pair[1];
            int count = InputValidation.validateCount(tempNum);
            tempOrder.put(tempName, count);
        }
    }

    private static void validateDuplication(String[] input, Map<String, Integer> tempOrder) {
        if (input.length != tempOrder.size()) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }

    private static void outOfMenuExampleFormat(String[] pair) {
        if (pair.length != 2) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
