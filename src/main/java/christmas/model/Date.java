package christmas.model;

import christmas.constants.ErrorMessage;

public class Date {
    private final int day;

    public Date(int day) {
        validateDate(day);
        this.day = day;
    }

    private void validateDate(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException(ErrorMessage.INVALID_DATE.getMessage());
        }
    }

    public int getDate() {
        return day;
    }
}
