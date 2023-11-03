package racingcar.model

data class Winner(
    private val cars: List<Car>
) {
    // 굳이 우승자 이름들을 List로 가지고 있을 필요가 없다!
    // 한 번만 수행되므로 요청 했을 때, 바로 반환하도록 수정
    internal fun getWinners(): List<String> {
        val bestScore = cars.maxOf { it.score }
        require(bestScore >= MIN_BEST_SCORE) { ZERO_SCORE_IS_NOT_WINNER }
        return cars.filter { it.score == bestScore }.map { it.name }
    }

    companion object {
        private const val MIN_BEST_SCORE = 1
        internal const val ZERO_SCORE_IS_NOT_WINNER = "0점으로 우승할 수 없습니다."
    }
}