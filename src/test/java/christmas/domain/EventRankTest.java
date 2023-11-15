package christmas.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static christmas.constant.MessagesConst.NONE;
import static christmas.domain.EventRank.getEventRank;
import static org.assertj.core.api.Assertions.assertThat;

class EventRankTest {

    @ParameterizedTest
    @DisplayName("금액별 이벤트 배지 테스트")
    @ValueSource(ints = {1_000, 5_000, 1_0000, 2_0000})
    void test(int totalAmount) {
        if (totalAmount == 1000) {
            assertThat(getEventRank(totalAmount)).isEqualTo(NONE);
        }
        if (totalAmount == 5000) {
            assertThat(getEventRank(totalAmount)).isEqualTo("별");
        }
        if (totalAmount == 10_000) {
            assertThat(getEventRank(totalAmount)).isEqualTo("트리");
        }
        if (totalAmount == 20_000) {
            assertThat(getEventRank(totalAmount)).isEqualTo("산타");
        }
    }
}