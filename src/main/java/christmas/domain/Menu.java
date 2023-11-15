package christmas.domain;

import java.util.HashMap;
import java.util.Map;

import static christmas.constant.ExceptionMessage.IN_VALID_EXCEPTION;
import static christmas.constant.MenuTypeConst.*;

public enum Menu {
    MUSHROOM_SOUP(APPETIZER, "양송이수프", 6_000),
    TAPAS(APPETIZER, "타파스", 5_500),
    CAESAR_SALAD(APPETIZER, "시저새러드", 8_000),
    T_BONE_STEAK(MAIN, "티본스테이크", 55_000),
    BARBECUE_RIPS(MAIN, "바비큐립", 54_000),
    SEAFOOD_PASTA(MAIN, "해산물파스타", 35_000),
    CHRISTMAS_PASTA(MAIN, "크리스마스파스타", 25_000),
    CHOCOLATE_CAKE(DESSERT, "초코케이크", 15_000),
    ICE_CREAM(DESSERT, "아이스크림", 5_000),
    ZERO_COKE(DRINKS, "제로콜라", 3_000),
    RED_WINE(DRINKS, "레드와인", 60_000),
    CHAMPAGNE(DRINKS, "샴페인", 25_000);

    private final String type;
    private final String MenuName;
    private final int price;

    Menu(String type, String MenuName, int price) {
        this.type = type;
        this.MenuName = MenuName;
        this.price = price;
    }

    public String getMenuName() {
        return MenuName;
    }

    public int getPrice() {
        return price;
    }

    public static Menu findByMenuName(String menuName) {
        for (Menu value : values()) {
            if (value.MenuName.equals(menuName)) {
                return value;
            }
        }
        throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
    }

    public static int getEachAmount(Menu menu) {
        return menu.price;
    }

    public static String getMenuType(Menu menu) {
        return menu.type;
    }

    public static Map<String, Integer> getDessertAndMainCount(OrderMenu orderMenu) {
        Map<String, Integer> menuTypes = new HashMap<>();
        int dessertCount = getDessertCount(orderMenu);
        int mainCount = getMainCount(orderMenu);

        if (dessertCount > 0) {
            menuTypes.put(DESSERT, dessertCount);
        }
        if (mainCount > 0) {
            menuTypes.put(MAIN, mainCount);
        }
        return menuTypes;
    }

    private static int getDessertCount(OrderMenu orderMenu) {
        int count = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenu.getOrderMenu().entrySet()) {
            if (entry.getKey().type.contains(DESSERT)) {
                count += entry.getValue();
            }
        }
        return count;
    }

    private static int getMainCount(OrderMenu orderMenu) {
        int count = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenu.getOrderMenu().entrySet()) {
            if (entry.getKey().type.contains(MAIN)) {
                count += entry.getValue();
            }
        }
        return count;
    }
}