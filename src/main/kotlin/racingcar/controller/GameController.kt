package racingcar.controller

import racingcar.model.Car
import racingcar.model.Cars
import racingcar.model.Repetition
import racingcar.model.Winner
import racingcar.utils.Round
import racingcar.view.InputView
import racingcar.view.OutputView

class GameController(
    private val inputView: InputView,
    private val outputView: OutputView,
) {
    fun start() {
        val carNames = inputCarName()
        val cars = Cars.fromNames(carNames)

        val repetitionInput = inputRepetition()
        val repetition = Repetition(repetitionInput)

        play(cars, repetition)
    }

    private fun inputCarName(): List<String> {
        val carNameInput = inputView.promptCarNames()
        return inputView.splitCarNamesByComma(carNameInput)
    }

    private fun inputRepetition() = inputView.promptRepetition()

    private fun play(cars: Cars, repetition: Repetition) {
        playRounds(repetition.count, cars.carObjects)
        playResult(cars.carObjects)
    }

    private fun playRounds(count: Int, cars: List<Car>) {
        outputView.printResultInfo()

        repeat(count) {
            advanceCarsInRound(cars)
            outputView.printRoundResult(cars)
        }
    }

    private fun advanceCarsInRound(cars: List<Car>) {
        for (car in cars) {
            val generatedNumber = Round.generateRandomNumber()
            val canMoveForward = Round.isForward(generatedNumber)
            if (canMoveForward) {
                car.moveForward()
            }
        }
    }

    private fun playResult(cars: List<Car>) {
        val winner = Winner(cars)
        outputView.printWinner(winner.getWinners())
    }
}