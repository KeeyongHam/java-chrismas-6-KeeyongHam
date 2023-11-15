package christmas.controller;

import christmas.domain.*;

import java.util.Map;

import static christmas.view.InputView.readDate;
import static christmas.view.InputView.readMenu;
import static christmas.view.OutputView.*;

public class ChristmasController {

    public ChristmasController() {
    }

    public void start() {
        printPrecautions();
        int date = getDate();
        OrderMenu orderMenu = getOrderMenu();

        Map<String, Integer> dessertAndMainCount = Menu.getDessertAndMainCount(orderMenu);

        Amount amount = new Amount(orderMenu);

        int amountBeforeDiscount = amount.getTotalAmountBeforeDiscount();
        Map<String, Integer> discountDetails = Discount.getDiscountDetails(dessertAndMainCount, amountBeforeDiscount, date);
        int totalDiscountAmount = amount.getTotalDiscountAmount(discountDetails);
        int amountOfPayment = amount.getAmountOfPayment(amountBeforeDiscount, discountDetails);

        String eventRank = EventRank.getEventRank(amountOfPayment);

        printOutputView(date, orderMenu, amountBeforeDiscount, discountDetails, totalDiscountAmount, amountOfPayment, eventRank);
    }

    private static int getDate() {
        int date = 0;
        while (date == 0) {
            try {
                date = Date.getDate(readDate());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                date = getDate();
            }
        }
        return date;
    }

    private static OrderMenu getOrderMenu() {
        OrderMenu orderMenu = null;
        while (orderMenu == null) {
            try {
                orderMenu = new OrderMenu(readMenu());
            } catch (IllegalArgumentException e) {
                System.out.println(e.getMessage());
                orderMenu = getOrderMenu();
            }
        }
        return orderMenu;
    }

    private static void printOutputView(int date, OrderMenu orderMenu,
                                        int amountBeforeDiscount, Map<String, Integer> discountDetails,
                                        int totalDiscountAmount,
                                        int amountOfPayment,
                                        String eventRank) {
        printPreview(date);
        printOrderMenu(orderMenu);
        printAmountBeforeDiscount(amountBeforeDiscount);
        printGiveawayMenu(discountDetails);
        printDiscountDetail(discountDetails);
        printTotalDiscountAmount(totalDiscountAmount);
        printExpectedPaymentAmount(amountOfPayment);
        printEventBadge(eventRank);
    }
}
