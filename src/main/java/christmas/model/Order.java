package christmas.model;

import christmas.constants.ErrorMessage;
import java.util.Map;

public class Order {
    private final Map<String, Integer> order;

    public Order(Map<String, Integer> order) {
        validateMenu(order);
        validateOrderCount(order);
        this.order = order;
    }

    private void validateMenu(Map<String, Integer> order) {
        for (String menuName : order.keySet()) {
            boolean checkMenu = containMenu(menuName);
            if (!checkMenu) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        }
    }

    private void validateOrderCount(Map<String, Integer> order) {
        for (String menuName : order.keySet()) {
            if (order.get(menuName) < 1) {
                throw new IllegalArgumentException(ErrorMessage.INVALID_ORDER.getMessage());
            }
        }
    }

    private boolean containMenu(String tempName) {
        AllMenu allMenu = new AllMenu();
        Map<String, Map<String, Integer>> allMenuMap = allMenu.getAllMenu();
        boolean duplicateMenu = false;
        for (Map.Entry<String, Map<String, Integer>> category : allMenuMap.entrySet()) {
            Map<String, Integer> menuInCategory = category.getValue();
            if (menuInCategory.containsKey(tempName)) {
                duplicateMenu = true;
            }
        }
        return duplicateMenu;
    }

    public Map<String, Integer> getOrder() {
        return order;
    }
}
