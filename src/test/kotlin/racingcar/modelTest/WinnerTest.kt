package racingcar.modelTest

import org.assertj.core.api.Assertions.assertThat
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.MethodSource
import racingcar.model.Car
import racingcar.model.Winner

class WinnerTest {

    @ParameterizedTest
    @MethodSource("carsForZeroScore")
    @DisplayName("Winner(init): Zero Score Winner Case")
    fun `Winner 객체 생성, bestScore 오류 발생 검증 (bestScore가 0일 때)`(cars: List<Car>) {
        val exception = assertThrows<IllegalArgumentException> {
            Winner(cars).getWinners()
        }

        assertEquals(Winner.ZERO_SCORE_IS_NOT_WINNER, exception.message)
    }

    @ParameterizedTest
    @MethodSource("carsForSingleWinner")
    @DisplayName("Winner(init): Single Winner Case")
    fun `Winner 객체 생성, 우승자 Name List 검증 (우승자가 한 명인 경우)`(cars: List<Car>) {
        val winnerNames = Winner(cars).getWinners()

        assertThat(winnerNames).isEqualTo(listOf("ToBBi"))
    }

    @ParameterizedTest
    @MethodSource("carsForMultiWinner")
    @DisplayName("Winner(init): Multi Winner Case")
    fun `Winner 객체 생성, 우승자 Name List 검증 (우승자가 여러 명인 경우)`(cars: List<Car>) {
        val winnerNames = Winner(cars).getWinners()

        assertThat(winnerNames).isEqualTo(listOf("Pobi", "JS", "Per"))
    }

    companion object {
        @JvmStatic
        private fun carsForZeroScore(): List<List<Car>> {
            return listOf(
                listOf(
                    Car("Pobi", 0),
                    Car("T", 0),
                    Car("JS", 0),
                )
            )
        }

        @JvmStatic
        private fun carsForSingleWinner(): List<List<Car>> {
            return listOf(
                listOf(
                    Car("Pobi", 5),
                    Car("ToBBi", 7),
                    Car("JS", 6),
                )
            )
        }

        @JvmStatic
        private fun carsForMultiWinner(): List<List<Car>> {
            return listOf(
                listOf(
                    Car("Pobi", 8),
                    Car("T", 7),
                    Car("JS", 8),
                    Car("Jun", 6),
                    Car("Per", 8),
                )
            )
        }
    }
}