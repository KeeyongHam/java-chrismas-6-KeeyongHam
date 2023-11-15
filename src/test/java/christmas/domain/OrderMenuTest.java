package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Map;

import static christmas.constant.ExceptionMessage.IN_VALID_EXCEPTION;
import static christmas.constant.MenuTypeConst.DESSERT;
import static christmas.constant.MenuTypeConst.MAIN;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class OrderMenuTest {

    @ParameterizedTest
    @DisplayName("옳바른 값을 입력, 정상 처리")
    @ValueSource(strings = {"해산물파스타-2,레드와인-1,초코케이크-1", "티본스테이크-1,바비큐립-1,초코케이크-2,제로콜라-1", "티본스테이크-1"})
    void success(String readMenu) {
        assertThat(new OrderMenu(readMenu)).isInstanceOf(OrderMenu.class);
    }

    @Test
    @DisplayName("디저트메뉴, 메인 메뉴 개수 확인")
    void DessertMainCountTest() {
        OrderMenu orderMenu = new OrderMenu("해산물파스타-2,바비큐립-1,초코케이크-1,아이스크림-2,제로콜라-2");
        Map<String, Integer> dessertAndMainCount = Menu.getDessertAndMainCount(orderMenu);

        assertThat(dessertAndMainCount.size()).isEqualTo(2);
        assertThat(dessertAndMainCount.get(MAIN)).isEqualTo(3);
        assertThat(dessertAndMainCount.get(DESSERT)).isEqualTo(3);
    }

    @ParameterizedTest
    @DisplayName("주문 포멧 예외(1.쉼표를 입력안함, 2.수량만 입력함, 3.메뉴만 입력함, 4.주문 수량이 1미만)")
    @ValueSource(strings = {"해산물파스타-2레드와인-1", "-1,바비큐립-1", "티본스테이크,바비큐립-1", "티본스테이크-0,바비큐립-1"})
    void formatTest(String readMenu) {
        assertThatThrownBy(() -> new OrderMenu(readMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IN_VALID_EXCEPTION.getMessage());
    }

    @ParameterizedTest
    @DisplayName("음료만 주문한 경우, 같은 주문을 반복한 경우")
    @ValueSource(strings = {"제로콜라-1", "해산물파스타-1,해산물파스타-2,제로콜라-1"})
    void OnlyOrderDrinksTest(String readMenu) {
        assertThatThrownBy(() -> new OrderMenu(readMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IN_VALID_EXCEPTION.getMessage());
    }

    @ParameterizedTest
    @DisplayName("총 주문 수량이 20을 초과한 경우, 없는 메뉴를 주문한 경우")
    @ValueSource(strings = {"해산물파스타-10,레드와인-10,초코케이크-1", "해물파스타-1,레드와인-1"})
    void TotalOrderQuantityTest(String readMenu) {
        assertThatThrownBy(() -> new OrderMenu(readMenu))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(IN_VALID_EXCEPTION.getMessage());
    }
}