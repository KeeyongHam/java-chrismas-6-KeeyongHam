package christmas.domain;

import static christmas.constant.MessagesConst.NONE;

public enum EventRank {
    STAR("별", 5_000),
    TREE("트리", 10_000),
    SANTA("산타", 20_000);

    private final String badgeName;
    private final int price;

    EventRank(String badgeName, int price) {
        this.badgeName = badgeName;
        this.price = price;
    }

    public static String getEventRank(int totalPrice) {
        if (totalPrice >= STAR.price && totalPrice < TREE.price) {
            return STAR.badgeName;
        }
        if (totalPrice > STAR.price && totalPrice <= TREE.price) {
            return TREE.badgeName;
        }
        if (totalPrice >= SANTA.price) {
            return SANTA.badgeName;
        }
        return NONE;
    }
}
