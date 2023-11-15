package christmas.domain;

import java.util.HashMap;
import java.util.Map;
import java.util.stream.IntStream;

import static christmas.constant.MenuTypeConst.DESSERT;
import static christmas.constant.MenuTypeConst.MAIN;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.validation.DiscountValidator.*;

public enum Discount {
    CHRISTMAS_DISCOUNT("크리스마스 디데이 할인", 1_000),
    WEEKDAY_DISCOUNT("평일 할인", 2_023),
    WEEKEND_DISCOUNT("주말 할인", 2_023),
    SPECIAL_DISCOUNT("특별할인", 1_000),
    GIVEAWAY_EVENT("증정 이벤트", CHAMPAGNE.getPrice());

    private final String discountName;
    private final int discountPrice;

    Discount(String discountName, int discountPrice) {
        this.discountName = discountName;
        this.discountPrice = discountPrice;
    }

    public String getDiscountName() {
        return discountName;
    }

    public static Map<String, Integer> getDiscountDetails(Map<String, Integer> dessertAndMainCount, int totalAmount, int date) {
        Map<String, Integer> discountDetails = new HashMap<>();

        if (totalAmount >= 10_000) {
            putChristmasDiscount(date, discountDetails);
            putSpecialDiscount(date, discountDetails);
            putWeekdayDiscount(dessertAndMainCount, date, discountDetails);
            putWeekendDiscount(dessertAndMainCount, date, discountDetails);
            putGiveawayEvent(totalAmount, discountDetails);
        }

        return discountDetails;
    }

    private static void putChristmasDiscount(int date, Map<String, Integer> discountDetails) {
        if (isChristmasDiscount(date)) {
            int discount = CHRISTMAS_DISCOUNT.discountPrice;
            discount += IntStream.range(0, date - 1).map(i -> 100).sum();
            discountDetails.put(CHRISTMAS_DISCOUNT.discountName, discount);
        }
    }

    private static void putSpecialDiscount(int date, Map<String, Integer> discountDetails) {
        if (isSpecialDiscount(date)) {
            discountDetails.put(SPECIAL_DISCOUNT.discountName, SPECIAL_DISCOUNT.discountPrice);
        }
    }

    private static void putWeekdayDiscount(Map<String, Integer> menuTypes, int date, Map<String, Integer> discountDetails) {
        if (isWeekdayDiscount(date) && menuTypes.containsKey(DESSERT)) {
            Integer count = menuTypes.get(DESSERT);
            discountDetails.put(WEEKDAY_DISCOUNT.discountName, WEEKDAY_DISCOUNT.discountPrice * count);
        }
    }

    private static void putWeekendDiscount(Map<String, Integer> menuTypes, int date, Map<String, Integer> discountDetails) {
        if (isWeekendDiscount(date) && menuTypes.containsKey(MAIN)) {
            Integer count = menuTypes.get(MAIN);
            discountDetails.put(WEEKEND_DISCOUNT.discountName, WEEKEND_DISCOUNT.discountPrice * count);
        }
    }

    private static void putGiveawayEvent(int totalAmount, Map<String, Integer> discountDetails) {
        if (isGiveawayEvent(totalAmount)) {
            discountDetails.put(GIVEAWAY_EVENT.discountName, GIVEAWAY_EVENT.discountPrice);
        }
    }
}