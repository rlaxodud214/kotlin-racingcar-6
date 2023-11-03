package racingcar.model

data class Cars(
    internal val carObjects: List<Car>,
) {
    init {
        checkDuplicateName(carObjects)
    }

    private fun checkDuplicateName(carObjects: List<Car>) =
        require(carObjects.size == carObjects.toSet().size) {
            CAR_NAME_DUPLICATE_ERROR
        }

    companion object {
        internal const val CAR_NAME_DUPLICATE_ERROR = "자동차 이름은 중복될 수 없습니다."

        internal fun fromNames(names: List<String>): Cars {
            return Cars(names.map {
                Car(it)
            })
        }
    }
}