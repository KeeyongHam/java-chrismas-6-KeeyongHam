package christmas.view;

import christmas.domain.Menu;
import christmas.domain.OrderMenu;

import java.text.DecimalFormat;
import java.util.Map;

import static christmas.constant.MessagesConst.*;
import static christmas.domain.Discount.GIVEAWAY_EVENT;

public class OutputView {

    private static final DecimalFormat formatter = new DecimalFormat("###,###");

    private OutputView() {

    }

    public static void printPrecautions() {
        System.out.println("==========================================");
        System.out.println(NOTICE);
        System.out.println(JUST_CAN_NOT_ORDER_DRINKS);
        System.out.println(MAXIMUM_ORDER_QUANTITY);
        System.out.println(APPLY_EVENT);
        System.out.println(THANK_YOU);
        System.out.println("==========================================");
        System.out.println();
    }

    public static void printOrderMenu(OrderMenu orderMenus) {
        System.out.println(ORDER_MENU);
        for (Map.Entry<Menu, Integer> menu : orderMenus.getOrderMenu().entrySet()) {
            System.out.println(menu.getKey().getMenuName() + " " + menu.getValue() + "개");
        }
        System.out.println();
    }

    public static void printPreview(int date) {
        System.out.println(MONTH + date + PREVIEW);
        System.out.println();
    }

    public static void printAmountBeforeDiscount(int amountBeforeDiscount) {
        System.out.println(TOTAL_AMOUNT_BEFORE_DISCOUNT);
        System.out.println(takeAComma(amountBeforeDiscount) + KRW);
        System.out.println();
    }

    public static void printGiveawayMenu(Map<String, Integer> discountDeTail) {
        System.out.println(GIVEAWAY_MENU);

        if (discountDeTail.containsKey(GIVEAWAY_EVENT.getDiscountName())) {
            System.out.println(GIVEAWAY);
        }
        if (!discountDeTail.containsKey(GIVEAWAY_EVENT.getDiscountName())) {
            System.out.println(NONE);
        }
        System.out.println();
    }

    public static void printDiscountDetail(Map<String, Integer> discountDeTail) {
        System.out.println(BENEFIT_DETAIL);

        if (discountDeTail.size() == 0) {
            System.out.println(NONE);
        }

        if (discountDeTail.size() != 0) {
            discountDeTail.forEach((key, value) -> {
                System.out.print(key + ": ");
                System.out.print(takeAComma(value * -1) + KRW);
                System.out.println();
            });
        }
        System.out.println();
    }

    public static void printTotalDiscountAmount(int totalAmount) {
        System.out.println(TOTAL_BENEFIT_AMOUNT);

        if (totalAmount == 0) {
            System.out.println(NONE);
        }
        if (totalAmount != 0) {
            System.out.println(takeAComma(totalAmount * -1) + KRW);
        }
        System.out.println();
    }

    public static void printExpectedPaymentAmount(int paymentAmount) {
        System.out.println(EXPECTED_PAYMENT_AMOUNT);
        System.out.println(takeAComma(paymentAmount) + KRW);
        System.out.println();
    }

    public static void printEventBadge(String eventBadge) {
        System.out.println(MONTH_EVENT_BADGE);
        System.out.println(eventBadge);
    }

    private static String takeAComma(int num) {
        return formatter.format(num);
    }
}
