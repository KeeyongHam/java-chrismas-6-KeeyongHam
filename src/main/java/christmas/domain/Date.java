package christmas.domain;

import static christmas.validation.DateValidator.validateDate;

public class Date {
    private Date() {
    }

    public static int getDate(String readDate) {
        return validateDate(readDate);
    }
}
