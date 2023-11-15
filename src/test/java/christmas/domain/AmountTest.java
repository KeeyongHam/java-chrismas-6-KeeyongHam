package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static christmas.domain.Menu.T_BONE_STEAK;
import static christmas.domain.Menu.getDessertAndMainCount;
import static org.assertj.core.api.Assertions.assertThat;

class AmountTest {

    @ParameterizedTest
    @DisplayName("할인 전 금액이 주문수량 * 메뉴가격이랑 같은지 확인")
    @ValueSource(strings = {"타파스-1,제로콜라-1", "해산물파스타-2,레드와인-1,초코케이크-1"})
    void 할인_전_총액_테스트(String readMenu) {
        OrderMenu orderMenu = new OrderMenu(readMenu);
        Amount amount = new Amount(orderMenu);
        int amountBeforeDiscount = amount.getTotalAmountBeforeDiscount();

        int sum = orderMenu.getOrderMenu().entrySet().stream()
                .mapToInt(menu -> menu.getKey().getPrice() * menu.getValue())
                .sum();

        assertThat(amountBeforeDiscount).isEqualTo(sum);
    }

    @ParameterizedTest
    @DisplayName("날짜별 할인 테스트")
    @ValueSource(ints = {3, 25, 30})
    void 날짜별_총_할인_금액_테스트(int date) {
        OrderMenu orderMenu = new OrderMenu("티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1");
        Map<String, Integer> dessertAndMainCount = getDessertAndMainCount(orderMenu);

        Amount amount = new Amount(orderMenu);
        int amountBeforeDiscount = amount.getTotalAmountBeforeDiscount();
        Map<String, Integer> discountDetails = Discount.getDiscountDetails(dessertAndMainCount, amountBeforeDiscount, date);
        int totalDiscountAmount = amount.getTotalDiscountAmount(discountDetails);

        if (date == 3) {
            int verificationAmount = 1200 + (2023 * 2) + 1000 + 25000;
            assertThat(totalDiscountAmount).isEqualTo(verificationAmount);
        }
        if (date == 25) {
            int verificationAmount = 3400 + (2023 * 2) + 1000 + 25000;
            assertThat(totalDiscountAmount).isEqualTo(verificationAmount);
        }
        if (date == 31) {
            int verificationAmount = (2023 * 2) + 25000;
            assertThat(totalDiscountAmount).isEqualTo(verificationAmount);
        }
    }

    @ParameterizedTest
    @DisplayName("결제 예상금액 테스트")
    @ValueSource(strings = {"티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", "타파스-1,제로콜라-1"})
    void 결제_예상금액_테스트(String readMenu) {
        int date = 3;

        OrderMenu orderMenu = new OrderMenu(readMenu);
        Map<String, Integer> dessertAndMainCount = getDessertAndMainCount(orderMenu);

        Amount amount = new Amount(orderMenu);
        int amountBeforeDiscount = amount.getTotalAmountBeforeDiscount();
        Map<String, Integer> discountDetails = Discount.getDiscountDetails(dessertAndMainCount, amountBeforeDiscount, date);
        int amountOfPayment = amount.getAmountOfPayment(amountBeforeDiscount, discountDetails);

        if (orderMenu.getOrderMenu().containsKey(T_BONE_STEAK)) {
            int discount = 1200 + (2023 * 2) + 1000;
            int verificationAmount = amountBeforeDiscount - discount;
            assertThat(amountOfPayment).isEqualTo(verificationAmount);
        }
        if (!orderMenu.getOrderMenu().containsKey(T_BONE_STEAK)) {
            assertThat(amountBeforeDiscount).isEqualTo(amountOfPayment);
        }
    }
}