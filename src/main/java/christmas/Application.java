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

        while (true) {
            try {
                System.out.println("12월 중 식당 예상 방문 날짜는 언제인가요? (숫자만 입력해 주세요!)");
                String input = Console.readLine();
                int day = Integer.parseInt(input);
                if (day < 1 || day > 31) {
                    throw new IllegalArgumentException("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요.");
                }
                break;
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
            }
        }

    }
}
