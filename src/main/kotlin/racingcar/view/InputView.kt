package racingcar.view

import camp.nextstep.edu.missionutils.Console
import racingcar.view.Constants.COMMA_DELIMITERS

class InputView {
    internal fun splitCarNamesByComma(carNames: String): List<String> {
        require(carNames.contains(COMMA_DELIMITERS)) {
            MINIMUM_CAR_COUNT_REQUIRED
        }
        return carNames.split(COMMA_DELIMITERS)
    }

    internal fun promptCarNames(): String {
        println(INPUT_CAR_NAMES)
        return Console.readLine()
    }

    internal fun promptRepetition(): String {
        println(INPUT_REPETITION)
        return Console.readLine()
    }

    companion object {
        internal const val INPUT_CAR_NAMES = "경주할 자동차 이름을 입력하세요.(이름은 쉼표(,) 기준으로 구분)"
        internal const val INPUT_REPETITION = "시도할 횟수는 몇 회인가요?"
        internal const val MINIMUM_CAR_COUNT_REQUIRED = "경주할 자동차는 2개 이상 이어야 합니다."
    }
}