package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.constant.ExceptionMessage.DATE_INPUT_EXCEPTION;
import static christmas.domain.Date.getDate;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class DateTest {

    @ParameterizedTest
    @DisplayName("int로 변환할 수 있고, 1 ~31 사이의 숫자를 입력 해 정상처리")
    @ValueSource(strings = {"1", "2", "10", "31"})
    void successTest(String readDate) {
        assertThat(getDate(readDate)).isInstanceOf(Integer.class);
    }

    @Test
    @DisplayName("문자를 입력해 int로 변환 할수 없는 예외")
    void inputStringTest() {
        assertThatThrownBy(() -> Date.getDate("안녕하세요~ 꼭 합격하고 싶어용!!!"))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DATE_INPUT_EXCEPTION.getMessage());
    }

    @ParameterizedTest
    @DisplayName("1 ~ 31 사이의 숫자가 아닌 예외")
    @ValueSource(strings = {"-1", "0", "32", "999"})
    void dateRangeTest(String readDate) {
        assertThatThrownBy(() -> Date.getDate(readDate))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage(DATE_INPUT_EXCEPTION.getMessage());
    }
}