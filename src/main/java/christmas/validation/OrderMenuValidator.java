package christmas.validation;

import christmas.domain.Menu;

import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static christmas.constant.ExceptionMessage.IN_VALID_EXCEPTION;
import static christmas.constant.ExceptionMessage.TYPE_MISS_MATCHING_EXCEPTION;
import static christmas.constant.MenuTypeConst.DRINKS;

public class OrderMenuValidator {

    public static final String FULL_PATTERN = "([가-힣]+)-([0-9]+)";
    public static final String LANGUAGE_PATTERN = "([가-힣]+)";

    public static void validateBeforeMatching(String splitedString) {
        Pattern regex = Pattern.compile(FULL_PATTERN);
        Matcher matcher = regex.matcher(splitedString);

        if (!matcher.find()) {
            throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
        }
    }

    public static void validateDuplicateMenu(Map<Menu, Integer> orderMenu, Menu menu) {
        if (orderMenu.containsKey(menu)) {
            throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
        }
    }

    public static void validateOrderMenu(Map<Menu, Integer> orderMenu, List<String> splitComma) {
        validateOrderFormat(orderMenu, splitComma);
        validateQuantityLessThan(orderMenu);
        validateDrinksOrder(orderMenu);
    }

    private static void validateOrderFormat(Map<Menu, Integer> orderMenu, List<String> splitComma) {
        Pattern regex = Pattern.compile(LANGUAGE_PATTERN);
        Matcher matcher = regex.matcher(splitComma.toString());

        while (matcher.find()) {
            String findMenuName = matcher.group();
            List<String> menuNames = orderMenu.keySet().stream().map(Menu::getMenuName).toList();
            if (!menuNames.contains(findMenuName)) {
                throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
            }
        }
        if (splitComma.size() == 1 && orderMenu.size() > 1) {
            throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
        }
    }

    private static void validateQuantityLessThan(Map<Menu, Integer> orderMenu) {
        int count = orderMenu.values().stream().mapToInt(value -> value).sum();
        if (count > 20) {
            throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
        }
    }

    private static void validateDrinksOrder(Map<Menu, Integer> orderMenu) {
        orderMenu.keySet().stream()
                .filter(menu -> Menu.getMenuType(menu).equals(DRINKS) && orderMenu.size() == 1)
                .forEach(menu -> {
                    throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
                });
    }

    public static void validateEachQuantity(int count) {
        if (count < 1) {
            throw new IllegalArgumentException(IN_VALID_EXCEPTION.getMessage());
        }
    }

    public static void validateConvertToInt(String num) {
        try {
            Integer.parseInt(num);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(TYPE_MISS_MATCHING_EXCEPTION.getMessage());
        }
    }
}