package christmas.validation;

public class DiscountValidator {
    public static boolean isChristmasDiscount(int date) {
        return date <= 25;
    }

    public static boolean isWeekdayDiscount(int date) {
        return date % 7 == 0 || date % 7 == 3 || date % 7 == 4 || date % 7 == 5 || date % 7 == 6;
    }

    public static boolean isWeekendDiscount(int date) {
        return date % 7 == 1 || date % 7 == 2;
    }

    public static boolean isSpecialDiscount(int date) {
        return date == 25 || date % 7 == 3;
    }

    public static boolean isGiveawayEvent(int totalAmount) {
        return totalAmount >= 120_000;
    }
}
