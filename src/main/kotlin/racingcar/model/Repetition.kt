package racingcar.model

data class Repetition(
    private val inputRepetition: String,
) {
    internal val count: Int get() = inputRepetition.toInt()

    init {
        checkDigitRange(inputRepetition)
    }

    private fun checkDigitRange(inputRepetition: String) {
        val inputRepetitionInt = requireNotNull(inputRepetition.toIntOrNull()) {
            ONLY_DIGIT
        }

        require(inputRepetitionInt >= MIN_REPETITION_COUNT) { DIGIT_THAN_ONE }
    }

    companion object {
        private const val MIN_REPETITION_COUNT = 1

        internal const val ONLY_DIGIT = "숫자만 입력해 주세요"
        internal const val DIGIT_THAN_ONE = "숫자는 1 이상이어야 합니다."
    }
}