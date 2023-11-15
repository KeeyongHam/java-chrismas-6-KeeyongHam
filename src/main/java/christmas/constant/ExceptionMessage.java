package christmas.constant;

public enum ExceptionMessage {
    TYPE_MISS_MATCHING_EXCEPTION("[ERROR] 일치하지 않는 타입입니다. 다시 입력해 주세요."),
    DATE_INPUT_EXCEPTION("[ERROR] 유효하지 않은 날짜입니다. 다시 입력해 주세요."),
    IN_VALID_EXCEPTION("[ERROR] 유효하지 않은 주문입니다. 다시 입력해 주세요."),
    QUANTITY_RANGE_EXCEPTION("[ERROR] 주문수량이 20을 초과할 수 없습니다. 다시 입력해 주세요.");

    private final String message;

    ExceptionMessage(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
