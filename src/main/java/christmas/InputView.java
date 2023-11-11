package christmas;

import camp.nextstep.edu.missionutils.Console;

public class InputView {
    public static int readDate() {
        int day = 0;
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

    private static int validateAllDate(String input) {
        int day = InputValidation.validateNumber(input);
        Date date = new Date(day);
        return date.getDate();
    }
}
