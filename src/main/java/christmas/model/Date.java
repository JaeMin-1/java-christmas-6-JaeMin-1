package christmas.model;

public class Date {
    private final int day;

    public Date(int day) {
        validateDate(day);
        this.day = day;
    }

    private void validateDate(int day) {
        if (day < 1 || day > 31) {
            throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
        }
    }

    public int getDate() {
        return day;
    }
}
