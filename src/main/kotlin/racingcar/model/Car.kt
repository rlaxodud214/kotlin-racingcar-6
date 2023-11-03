package racingcar.model

data class Car(
    internal val name: String,
    private var _score: Int = CAR_SCORE_DEFAULT,
) {
    internal val score: Int get() = _score

    init {
        vaildation(name)
        require(_score >= CAR_SCORE_DEFAULT) { CAR_SCORE_IS_NEGATIVE }
    }

    private fun vaildation(inputName: String) {
        checkNameLength(inputName)
        checkNameLetter(inputName)
    }

    private fun checkNameLength(inputName: String) =
        require(inputName.length in CAR_NAME_MIN..CAR_NAME_MAX) { CAR_NAME_LENGTH_ERROR }

    private fun checkNameLetter(inputName: String) =
        require(inputName.all { it.isDigit() || it.isLetter() }) { CAR_NAME_FORMAT_ERROR }


    internal fun moveForward() = _score++

    companion object {
        internal const val CAR_SCORE_DEFAULT = 0
        internal const val CAR_SCORE_IS_NEGATIVE = "테스트를 위해 입력된 숫자가 음수입니다."

        internal const val CAR_NAME_MIN = 1
        internal const val CAR_NAME_MAX = 5

        internal const val CAR_NAME_LENGTH_ERROR =
            "자동차 이름은 ${CAR_NAME_MIN}자 이상 ${CAR_NAME_MAX}자 이하로 입력해주세요."
        internal const val CAR_NAME_FORMAT_ERROR = "자동차 이름은 한글, 영문, 숫자만 사용할 수 있습니다."
    }
}