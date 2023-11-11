package christmas;

public class InputValidation {
    public static int validateNumber(String input) {
        try {
            int number = Integer.parseInt(input);
            return number;
        } catch (NumberFormatException e) {
            throw new IllegalArgumentException();
        }
    }
}
