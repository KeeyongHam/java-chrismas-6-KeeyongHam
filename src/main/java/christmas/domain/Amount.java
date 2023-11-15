package christmas.domain;

import java.util.Map;

import static christmas.domain.Discount.GIVEAWAY_EVENT;
import static christmas.domain.Menu.CHAMPAGNE;
import static christmas.domain.Menu.getEachAmount;

public class Amount {
    private static final String DISCOUNT_NAME = GIVEAWAY_EVENT.getDiscountName();

    private final OrderMenu orderMenu;

    public Amount(OrderMenu orderMenu) {
        this.orderMenu = orderMenu;
    }

    public int getTotalAmountBeforeDiscount() {
        int totalAmount = 0;

        for (Map.Entry<Menu, Integer> entry : orderMenu.getOrderMenu().entrySet()) {
            int amount = getEachAmount(entry.getKey());
            int quantity = entry.getValue();
            totalAmount += amount * quantity;
        }
        return totalAmount;
    }

    public int getTotalDiscountAmount(Map<String, Integer> discountDetails) {
        return discountDetails.values().stream()
                .mapToInt(value -> value)
                .sum();
    }

    public int getAmountOfPayment(int amountBeforeDiscount, Map<String, Integer> discountDetails) {
        int totalDiscountAmount = getTotalDiscountAmount(discountDetails);

        if (discountDetails.containsKey(DISCOUNT_NAME)) {
            return amountBeforeDiscount - totalDiscountAmount + CHAMPAGNE.getPrice();
        }
        return amountBeforeDiscount - totalDiscountAmount;
    }
}
