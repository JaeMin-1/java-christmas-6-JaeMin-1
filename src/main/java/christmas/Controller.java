package christmas;

import java.util.Map;

public class Controller {
    private AllMenu allMenu = new AllMenu();

    public void startOrder() {
        int day = InputView.readDate();
        Map<String, Integer> order = InputView.readOrder();

        OutputView.printOrderMenu(day, order);
        int totalOrderAmount = calculateTotalOrderAmount(order);
        OutputView.printTotalOrderAmount(totalOrderAmount);

    }

    private int calculateTotalOrderAmount(Map<String, Integer> order) {
        int totalOrderAmount = 0;
        for (Map.Entry<String, Map<String, Integer>> category : allMenu.getAllMenu().entrySet()) {
            totalOrderAmount += calculateCategoryTotal(category.getValue(), order);
        }
        return totalOrderAmount;
    }

    private int calculateCategoryTotal(Map<String, Integer> menuInCategory, Map<String, Integer> order) {
        int categoryTotal = 0;
        for (String orderMenu : order.keySet()) {
            if (menuInCategory.containsKey(orderMenu)) {
                categoryTotal += order.get(orderMenu) * menuInCategory.get(orderMenu);
            }
        }
        return categoryTotal;
    }
}
