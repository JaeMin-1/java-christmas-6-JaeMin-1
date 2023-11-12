package christmas;

import java.util.Map;

public class Order {
    private Map<String, Integer> order;

    public Order(Map<String, Integer> order) {
        validateMenu(order);
        validateOrderCount(order);
        this.order = order;
    }

    private void validateMenu(Map<String, Integer> order) {
        for (String menuName : order.keySet()) {
            boolean checkMenu = containMenu(menuName);
            if (!checkMenu) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private void validateOrderCount(Map<String, Integer> order) {
        for (String menuName : order.keySet()) {
            if (order.get(menuName) < 1) {
                throw new IllegalArgumentException("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요.");
            }
        }
    }

    private boolean containMenu(String tempName) {
        AllMenu allMenu = new AllMenu();
        boolean duplicateMenu = false;
        for (Map.Entry<String, Map<String, Integer>> category : allMenu.getAllMenu().entrySet()) {
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
