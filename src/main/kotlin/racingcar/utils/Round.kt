package racingcar.utils

import camp.nextstep.edu.missionutils.Randoms

object Round {
    internal const val RANDOM_RANGE_START = 0
    internal const val RANDOM_RANGE_END = 9

    internal const val FORWARD_THRESHOLD = 4

    internal fun generateRandomNumber() =
        Randoms.pickNumberInRange(RANDOM_RANGE_START, RANDOM_RANGE_END)

    internal fun isForward(randomNumber: Int) = randomNumber >= FORWARD_THRESHOLD
}