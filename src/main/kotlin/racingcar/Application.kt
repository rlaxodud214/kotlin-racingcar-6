package racingcar

import racingcar.controller.GameController
import racingcar.view.InputView
import racingcar.view.OutputView

/*  명령어 학습(C : Ctrl, A: Alt, S:Shift)
    1. CA + l : 자동 정렬
    2. CA + o : import 정리
    3. S + F6 : 이름 한 번에 수정
    4. C + F12 : 파일 구조 확인
    5. C + E : 최근 작업 파일 목록(CA L or R도 가능)
    6. CS + BackSpace : 최근 수정 위치
    7. S 2번 : 모든 프로젝트 파일에서 찾기
    8. // TODO키워드 : 오류 처리나 마무리 해야하는 부분 체크
    9. A + insert : Generate
*/

fun main() {
    val gameController = GameController(InputView(), OutputView())
    gameController.start()
}
