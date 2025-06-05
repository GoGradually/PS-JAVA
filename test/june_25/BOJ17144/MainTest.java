package june_25.BOJ17144;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("특정 위치의 미세먼지 확인")
    void test1() {
        int[][] boardState = new int[3][3];
        boardState[1][1] = 5;
        Board board = new Board(boardState);
        assertEquals(5, board.getDust(1, 1));
    }

    @Test
    @DisplayName("미세먼지 전파 테스트 - 정상 전파")
    void test2() {
        int[][] boardState = new int[3][3];
        boardState[1][1] = 5;
        Board board = new Board(boardState);

        board.spread();

        assertEquals(0, board.getDust(0, 0));
        assertEquals(1, board.getDust(0, 1));
        assertEquals(0, board.getDust(0, 2));
        assertEquals(1, board.getDust(1, 0));
        assertEquals(1, board.getDust(1, 1));
        assertEquals(1, board.getDust(1, 2));
        assertEquals(0, board.getDust(2, 0));
        assertEquals(1, board.getDust(2, 1));
        assertEquals(0, board.getDust(2, 2));
    }
    @Test
    @DisplayName("미세먼지 전파 테스트 - 구석 전파")
    void test3() {
        int[][] boardState = new int[3][3];
        boardState[0][0] = 5;
        Board board = new Board(boardState);

        board.spread();

        assertEquals(3, board.getDust(0, 0));
        assertEquals(1, board.getDust(0, 1));
        assertEquals(0, board.getDust(0, 2));
        assertEquals(1, board.getDust(1, 0));
        assertEquals(0, board.getDust(1, 1));
        assertEquals(0, board.getDust(1, 2));
        assertEquals(0, board.getDust(2, 0));
        assertEquals(0, board.getDust(2, 1));
        assertEquals(0, board.getDust(2, 2));
    }
    @Test
    @DisplayName("미세먼지 전파 테스트 - 전파 X")
    void test4() {
        int[][] boardState = new int[3][3];
        boardState[1][1] = 4;
        Board board = new Board(boardState);

        board.spread();

        assertEquals(0, board.getDust(0, 0));
        assertEquals(0, board.getDust(0, 1));
        assertEquals(0, board.getDust(0, 2));
        assertEquals(0, board.getDust(1, 0));
        assertEquals(4, board.getDust(1, 1));
        assertEquals(0, board.getDust(1, 2));
        assertEquals(0, board.getDust(2, 0));
        assertEquals(0, board.getDust(2, 1));
        assertEquals(0, board.getDust(2, 2));
    }
    @Test
    @DisplayName("미세먼지 전파 테스트 - 공청기 전파 X")
    void test5() {
        int[][] boardState = new int[3][3];
        boardState[1][0] = -1;
        boardState[0][0] = -1;
        boardState[0][1] = 5;
        Board board = new Board(boardState);

        board.spread();

        assertEquals(-1, board.getDust(0, 0));
        assertEquals(3, board.getDust(0, 1));
        assertEquals(1, board.getDust(0, 2));
        assertEquals(-1, board.getDust(1, 0));
        assertEquals(1, board.getDust(1, 1));
        assertEquals(0, board.getDust(1, 2));
        assertEquals(0, board.getDust(2, 0));
        assertEquals(0, board.getDust(2, 1));
        assertEquals(0, board.getDust(2, 2));
    }

    @Test
    @DisplayName("공청기 작동 테스트 - 위쪽(반시계)")
    void test6() {
        int[][] boardState = new int[4][3];
        boardState[1][0] = -1;
        boardState[2][0] = -1;
        boardState[0][0] = 1;
        boardState[0][1] = 2;
        boardState[0][2] = 3;
        boardState[1][2] = 4;
        boardState[1][1] = 5;
        Board board = new Board(boardState);

        board.sweep();

        assertEquals(2, board.getDust(0, 0));
        assertEquals(3, board.getDust(0, 1));
        assertEquals(4, board.getDust(0, 2));
        assertEquals(5, board.getDust(1, 2));
        assertEquals(0, board.getDust(1, 1));
        assertEquals(-1, board.getDust(1, 0));
    }

    @Test
    @DisplayName("공청기 작동 테스트 - 아래쪽(시계)")
    void test7() {
        int[][] boardState = new int[4][3];
        boardState[1][0] = -1;
        boardState[2][0] = -1;
        boardState[2][1] = 5;
        boardState[2][2] = 4;
        boardState[3][2] = 3;
        boardState[3][1] = 2;
        boardState[3][0] = 1;
        Board board = new Board(boardState);

        board.sweep();

        assertEquals(-1, board.getDust(2, 0));
        assertEquals(0, board.getDust(2, 1));
        assertEquals(5, board.getDust(2, 2));
        assertEquals(4, board.getDust(3, 2));
        assertEquals(3, board.getDust(3, 1));
        assertEquals(2, board.getDust(3, 0));
    }
}

/**
 * r*c 격자판
 * 1. 미세먼지 확산 clear
 *
 * 2. 공기청정기 작동
 */

/**
 * 도메인
 * - 격자판
 *   - 4칸 확산
 * - 공기청정기
 *   - 항상 1열에 존재
 *   - 크기는 2행 차지
 *   - 공기청정기는 유일
 * - 미세먼지
 * - 바람
 *
 */

/**
 * 미세먼지가 정상적으로 확산되는가?
 * - 한칸도 없는 경우
 * - 구석에 있는 경우
 * - 옮길 먼지가 모자란 경우
 * 공기청정기가 잘 작동하는가?
 * - 아랫쪽 공청기의 바람이 시계방향으로 이동하는가?
 * - 위쪽 공청기의 바람이 반시계방향으로 이동하는가?
 * - 공청기에 흡수된 먼지는 사라지는가?
 */