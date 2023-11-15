package christmas.view;

import static camp.nextstep.edu.missionutils.Console.readLine;
import static christmas.constant.MessagesConst.*;

public class InputView {
    private InputView() {
    }

    public static String readDate() {
        System.out.println(GREETING);
        System.out.println(DATE_OF_VISIT);
        return readLine();
    }

    public static String readMenu() {
        System.out.println(MENU_TO_ORDER);
        return readLine();
    }
}