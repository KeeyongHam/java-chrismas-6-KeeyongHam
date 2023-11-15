package christmas.domain;

import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static christmas.domain.Menu.findByMenuName;
import static christmas.validation.OrderMenuValidator.*;
import static java.util.regex.Pattern.compile;

public class OrderMenu {

    public static final String PATTERN = "([가-힣]+)-([0-9]+)";

    private final Map<Menu, Integer> orderMenu;

    public OrderMenu(String readMenu) {
        this.orderMenu = getOrderMenus(readMenu);
    }

    public Map<Menu, Integer> getOrderMenu() {
        return Collections.unmodifiableMap(orderMenu);
    }

    private Map<Menu, Integer> getOrderMenus(String readMenu) {
        Map<Menu, Integer> orderMenus = new HashMap<>();

        List<String> splitComma = Arrays.stream(readMenu.split(",")).toList();
        createOrderMenu(orderMenus, splitComma);

        return orderMenus;
    }

    private static void createOrderMenu(Map<Menu, Integer> orderMenus, List<String> splitComma) {
        for (String splitedString : splitComma) {
            Pattern regex = compile(PATTERN);
            Matcher matcher = regex.matcher(splitedString);

            validateBeforeMatching(splitedString);

            while (matcher.find()) {
                Menu menu = findByMenuName(matcher.group(1));
                String count = matcher.group(2);

                validateDuplicateMenu(orderMenus, menu);
                validateConvertToInt(count);
                orderMenus.put(menu, convertToInt(count));
            }
        }
        validateOrderMenu(orderMenus, splitComma);
    }

    private static int convertToInt(String count) {
        int convertInt = Integer.parseInt(count);
        validateEachQuantity(convertInt);
        return convertInt;
    }
}