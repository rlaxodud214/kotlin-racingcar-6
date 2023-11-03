package racingcar.view

import racingcar.model.Car
import racingcar.view.Constants.COMMA_DELIMITERS

class OutputView {
    internal fun printResultInfo() = println("\n$EXECUTION_RESULT_TEXT")

    internal fun printRoundResult(cars: List<Car>) {
        for (car in cars) {
            println("${car.name} : ${DASH.repeat(car.score)}")
        }
        println()
    }

    internal fun printWinner(winnerNames: List<String>) =
        print("$FINAL_WINNER ${winnerNames.joinToString("${COMMA_DELIMITERS} ")}")


    companion object {
        private const val DASH = "-"
        private const val EXECUTION_RESULT_TEXT = "실행 결과"
        private const val FINAL_WINNER = "최종 우승자 :"
    }
}