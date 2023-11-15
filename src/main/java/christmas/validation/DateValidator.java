package christmas.validation;

import static christmas.constant.ExceptionMessage.DATE_INPUT_EXCEPTION;

public class DateValidator {

    public static int validateDate(String date) {
        int validated = validateConvertToInt(date);
        if (!validRange(validated)) {
            throw new IllegalArgumentException(DATE_INPUT_EXCEPTION.getMessage());
        }
        return validated;
    }

    private static boolean validRange(int date) {
        return date >= 1 && date <= 31;
    }

    private static int validateConvertToInt(String date) {
        try {
            return Integer.parseInt(date);
        } catch (IllegalArgumentException e) {
            throw new IllegalArgumentException(DATE_INPUT_EXCEPTION.getMessage());
        }
    }
}
