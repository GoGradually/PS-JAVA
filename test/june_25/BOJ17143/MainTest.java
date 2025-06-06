package june_25.BOJ17143;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static june_25.BOJ17143.Shark.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    @Test
    @DisplayName("상어가 양수 방향으로 돌진해서 음수로 방향 전환")
    void test1() {
        Shark shark = new Shark(100, RIGHT, 100);
        Position position = new Position(1, 5);
        int x = 1;
        int y = 10;
        Position newPosition = shark.move(position, x, y);
        assertEquals(new Position(1, 5), newPosition);
        assertEquals(LEFT, shark.getDirection());
    }
    @Test
    @DisplayName("상어가 양수 방향으로 돌진해서 양수로 방향 전환")
    void test2() {
        Shark shark = new Shark(91, RIGHT, 100);
        Position position = new Position(1, 5);
        int x = 1;
        int y = 10;
        Position newPosition = shark.move(position, x, y);
        assertEquals(new Position(1, 6), newPosition);
        assertEquals(RIGHT, shark.getDirection());
    }


    @Test
    @DisplayName("상어가 음수 방향으로 돌진해서 음수로 방향 전환")
    void test3() {
        Shark shark = new Shark(91, LEFT, 100);
        Position position = new Position(1, 5);
        int x = 1;
        int y = 10;
        Position newPosition = shark.move(position, x, y);
        assertEquals(new Position(1, 4), newPosition);
        assertEquals(LEFT, shark.getDirection());
    }

    @Test
    @DisplayName("상어가 음수 방향으로 돌진해서 양수로 방향 전환")
    void test4() {
        Shark shark = new Shark(100, LEFT, 100);
        Position position = new Position(1, 5);
        int x = 1;
        int y = 10;
        Position newPosition = shark.move(position, x, y);
        assertEquals(new Position(1, 7), newPosition);
        assertEquals(RIGHT, shark.getDirection());
    }

    @Test
    @DisplayName("상어가 음수 방향으로 돌진해서 양수로 방향 전환 2")
    void test4_2() {
        Shark shark = new Shark(8, LEFT, 100);
        Position position = new Position(1, 5);
        int x = 1;
        int y = 6;
        Position newPosition = shark.move(position, x, y);
        assertEquals(new Position(1, 5), newPosition);
        assertEquals(RIGHT, shark.getDirection());
    }

    @Test
    @DisplayName("경곗값 검증 1 - 좌측 돌진")
    void test5() {
        Shark shark = new Shark(1, LEFT, 100);
        Position position = new Position(1, 1);
        int x = 1;
        int y = 2;
        Position newPosition = shark.move(position, x, y);
        assertEquals(1, newPosition.getX());
        assertEquals(2, newPosition.getY());
        assertEquals(RIGHT, shark.getDirection());
    }

    @Test
    @DisplayName("경곗값 검증 2 - 우측 돌진")
    void test6() {
        Shark shark = new Shark(1, RIGHT, 100);
        Position position = new Position(1, 2);
        int x = 1;
        int y = 2;
        Position newPosition = shark.move(position, x, y);
        assertEquals(1, newPosition.getX());
        assertEquals(1, newPosition.getY());
        assertEquals(LEFT, shark.getDirection());
    }
    @Test
    @DisplayName("경곗값 검증 3 - 상부 돌진")
    void test7() {
        Shark shark = new Shark(1, UP, 100);
        Position position = new Position(1, 1);
        int x = 2;
        int y = 1;
        Position newPosition = shark.move(position, x, y);
        assertEquals(2, newPosition.getX());
        assertEquals(1, newPosition.getY());
        assertEquals(DOWN, shark.getDirection());
    }

    @Test
    @DisplayName("경곗값 검증 4 - 하부 돌진")
    void test8() {
        Shark shark = new Shark(1, DOWN, 100);
        Position position = new Position(2, 1);
        int x = 2;
        int y = 1;
        Position newPosition = shark.move(position, x, y);
        assertEquals(1, newPosition.getX());
        assertEquals(1, newPosition.getY());
        assertEquals(UP, shark.getDirection());
    }

    @Test
    @DisplayName("상어 합치기")
    void test9() {
        Shark[][] boardState = new Shark[11][11];
        boardState[1][1] = new Shark(1, RIGHT, 50);
        boardState[1][3] = new Shark(1, LEFT, 10);
        Board board = new Board(boardState);

        board.moveShark();

        assertEquals(50, board.getShark(1, 2).getSize());
    }

    @Test
    @DisplayName("상어 잡기")
    void test10() {
        Shark[][] boardState = new Shark[11][11];
        boardState[1][1] = new Shark(1, RIGHT, 50);
        boardState[1][3] = new Shark(1, LEFT, 10);
        boardState[2][1] = new Shark(1, UP, 100);
        Board board = new Board(boardState);

        board.catchShark();
        assertEquals(50, board.getFishedSize());

    }

    @Test
    @DisplayName("통합 테스트 1")
    void test11() {
        Shark[][] boardState = new Shark[5][7];
        boardState[4][1] = new Shark(3, RIGHT, 8);
        boardState[2][2] = new Shark(2, RIGHT, 5);
        boardState[1][3] = new Shark(5, DOWN, 9);
        boardState[3][3] = new Shark(1, DOWN, 7);
        boardState[2][4] = new Shark(8, LEFT, 1);
        boardState[1][5] = new Shark(8, LEFT, 3);
        boardState[4][5] = new Shark(0, UP, 4);
        boardState[3][6] = new Shark(2, UP, 2);
        Board board = new Board(boardState);
        board.catchShark();
        board.moveShark();

        for (int i = 1; i < boardState.length; i++) {
            for (int j = 1; j < boardState[0].length; j++) {
                if (board.getShark(i, j) == null) {
                    System.out.print("0 ");
                    continue;
                }
                System.out.print(board.getShark(i, j).getSize()+" ");
            }
            System.out.println();
        }

        assertNotNull(board.getShark(2, 3));
        assertNotNull(board.getShark(4, 3));
        assertNotNull(board.getShark(2, 4));
        assertNotNull(board.getShark(2, 6));
        assertNotNull(board.getShark(1, 5));
        assertNotNull(board.getShark(1, 6));
        assertNotNull(board.getShark(4, 5));
    }

}
/**
 * R*C 격자판
 * 1. 낚시왕이 오른쪽으로 한 칸 이동한다.
 * 2. 낚시왕이 있는 열에 있는 상어 중에서 땅과 제일 가까운 상어를 잡는다. 상어를 잡으면 격자판에서 잡은 상어가 사라진다.
 * 3. 상어가 이동한다.
 * <p>
 * 도메인
 * 낚시왕
 * 상어
 * 게임판
 */

/**
 * 상어
 * - 이동 로직이 복잡함
 * - 상어 객체가 따로 책임을 갖는게 좋은가?
 * - 음수, 양수일 때 모두
 */