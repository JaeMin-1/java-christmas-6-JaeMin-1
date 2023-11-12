package christmas.model;

import java.util.HashMap;
import java.util.Map;

public class AllMenu {
    private final Map<String, Map<String, Integer>> allMenu;

    public AllMenu() {
        this.allMenu = new HashMap<>();
        initializeMenu();
    }

    private void initializeMenu() {
        initializeAppetizer();
        initializeMain();
        initializeDessert();
        initializeBeverage();
    }

    private void initializeAppetizer() {
        Map<String, Integer> appetizer = new HashMap<>();
        appetizer.put("양송이수프", 6000);
        appetizer.put("타파스", 5500);
        appetizer.put("시저샐러드", 8000);
        allMenu.put("애피타이저", appetizer);
    }

    private void initializeMain() {
        Map<String, Integer> main = new HashMap<>();
        main.put("티본스테이크", 55000);
        main.put("바비큐립", 54000);
        main.put("해산물파스타", 35000);
        main.put("크리스마스파스타", 25000);
        allMenu.put("메인", main);
    }

    private void initializeDessert() {
        Map<String, Integer> dessert = new HashMap<>();
        dessert.put("초코케이크", 15000);
        dessert.put("아이스크림", 5000);
        allMenu.put("디저트", dessert);
    }

    private void initializeBeverage() {
        Map<String, Integer> beverage = new HashMap<>();
        beverage.put("제로콜라", 3000);
        beverage.put("레드와인", 60000);
        beverage.put("샴페인", 25000);
        allMenu.put("음료", beverage);
    }

    public Map<String, Map<String, Integer>> getAllMenu() {
        return allMenu;
    }
}
