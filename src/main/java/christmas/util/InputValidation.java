package christmas.util;

import christmas.constants.ErrorMessage;

public class InputValidation {
    public static int validateDate(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public static int validateCount(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
        }
    }
}
