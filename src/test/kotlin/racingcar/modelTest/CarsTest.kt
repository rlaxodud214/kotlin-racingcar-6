package racingcar.modelTest

import org.assertj.core.api.Assertions.assertThat
import org.assertj.core.api.Assertions.assertThatThrownBy
import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.DisplayName
import org.junit.jupiter.api.TestInstance
import org.junit.jupiter.api.assertDoesNotThrow
import org.junit.jupiter.api.assertThrows
import org.junit.jupiter.params.ParameterizedTest
import org.junit.jupiter.params.provider.EmptySource
import org.junit.jupiter.params.provider.MethodSource
import org.junit.jupiter.params.provider.ValueSource
import racingcar.model.Car
import racingcar.model.Cars
import racingcar.view.InputView

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class CarsTest {
    private val inputView = InputView()

    @ParameterizedTest
    @ValueSource(strings = ["pobi,asd,123"])
    @DisplayName("InputView: splitCarNamesByComma() - Multi / Success Case")
    fun `리스트 반환 검증 (','가 있는 경우)`(inputNames: String) {
        assertThat(
            inputView.splitCarNamesByComma(inputNames)
        ).containsExactly("pobi", "asd", "123")
    }

    @ParameterizedTest
    @EmptySource
    @ValueSource(strings = ["as", "pobi", "taeng"])
    @DisplayName("InputView: splitCarNamesByComma() - Single / Fail Case")
    fun `오류 발생 검증 (','가 없는 경우) `(inputNames: String) {
        val exception = assertThrows<IllegalArgumentException> {
            inputView.splitCarNamesByComma(inputNames)
        }
        assertEquals(InputView.MINIMUM_CAR_COUNT_REQUIRED, exception.message)
    }

    @ParameterizedTest
    @ValueSource(strings = ["", "1234  ", "  1234", "Pobi12", "123456"])
    @DisplayName("Car(init): checkNameLength()")
    fun `Car 객체 생성, 차 이름이 1~5 이내 인지 검증`(inputName: String) {
        val exception = assertThrows<IllegalArgumentException> {
            Car(inputName)
        }
        assertEquals(Car.CAR_NAME_LENGTH_ERROR, exception.message)
    }

    @ParameterizedTest
    @ValueSource(strings = ["Car!", "Car@2", "My!!!", "!! !!", "! !", "()_+"])
    @DisplayName("Car(init): checkNameLetter()")
    fun `Car 객체 생성, 차 이름이 한글-영문-숫자로 구성되어 있는지 검증`(inputName: String) {
        // [Same Code]
        // when & then (AssertJ)
        assertThatThrownBy { Car(inputName) }                    // when
            .isInstanceOf(IllegalArgumentException::class.java)  // then - 오류 타입 검증
            .`as`(FAIL_REASON_DIFFRENT_ERROR_MESSAGE)            // then - 메시지 검증 (optional, 테스트 실패 이유 정의)
            .hasMessage(Car.CAR_NAME_FORMAT_ERROR)               // then - 메시지 검증

        // [Same Code]
        // when : assertThrows (JUnit5)
        val exception = assertThrows<IllegalArgumentException> {
            Car(inputName)
        }
        // then : assertThat (AssertJ)
        assertThat(exception)
            .`as`(FAIL_REASON_DIFFRENT_ERROR_MESSAGE)
            .hasMessage(Car.CAR_NAME_FORMAT_ERROR)
    }

    @ParameterizedTest
    @MethodSource("generateDuplicationTestdata")
    @DisplayName("Cars(init): fromNames() / Fail Case")
    fun `Cars 객체 생성, 차 이름이 중복되지 않았는지 검증`(inputName: List<String>) {
        // when (JUnit5)
        val exception = assertThrows<IllegalArgumentException> {
            Cars.fromNames(inputName)
        }

        // then (JUnit5) - 오류 메시지 검증
        assertEquals(
            Cars.CAR_NAME_DUPLICATE_ERROR,              // expected
            exception.message,                          // actual
            FAIL_REASON_DIFFRENT_ERROR_MESSAGE          // message (optional)
        )
    }


    @ParameterizedTest
    @MethodSource("generateCarNameData")
    @DisplayName("Cars(init): fromNames() size Valid / Success Case")
    fun `Cars 객체 생성, size 검증`(inputName: List<String>) {
        val cars = assertDoesNotThrow {
            Cars.fromNames(inputName)
        }

        assertThat(cars.carObjects.size).isEqualTo(inputName.size) // 사이즈 검증
    }

    @ParameterizedTest
    @MethodSource("generateCarNameData")
    @DisplayName("Cars(init): fromNames() carName Valid / Success Case")
    fun `Cars 객체 생성, carName 검증`(inputName: List<String>) {
        val cars = assertDoesNotThrow {
            Cars.fromNames(inputName)
        }

        for ((index, carName) in inputName.withIndex()) {
            val carsInstanceCarName = cars.carObjects[index].name
            assertThat(carsInstanceCarName).isEqualTo(carName) // carName 검증
        }
    }

    companion object {
        @JvmStatic
        private fun generateDuplicationTestdata() = listOf(
            listOf("1", "01", "1", "2"),
            listOf("010", "020", "010"),
            listOf("101", "102", "101"),
        )

        @JvmStatic
        private fun generateCarNameData() = listOf(
            listOf("1", "2", "1232", "2123"),
            listOf("asd", "125s", "sd21"),
        )

        // assertDoesNotThrow, assertThrows를 구분하기 애매함.
//        private fun createCarsFromNames(names: List<String>): Cars {
//            return assertDoesNotThrow {
//                Cars.fromNames(names)
//            }
//        }

        private const val FAIL_REASON_DIFFRENT_ERROR_MESSAGE = "fail reason: 오류 메시지가 다릅니다."
    }
}