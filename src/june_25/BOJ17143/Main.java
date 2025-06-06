package june_25.BOJ17143;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int r = Integer.parseInt(s[0]);
        int c = Integer.parseInt(s[1]);
        int m = Integer.parseInt(s[2]);
        Shark[][] boardState = new Shark[r + 1][c + 1];
        for (int i = 0; i < m; i++) {
            String[] sharkLine = br.readLine().split(" ");
            int row = Integer.parseInt(sharkLine[0]);
            int col = Integer.parseInt(sharkLine[1]);
            int speed = Integer.parseInt(sharkLine[2]);
            int direction = Integer.parseInt(sharkLine[3]);
            int size = Integer.parseInt(sharkLine[4]);
            Shark shark = new Shark(speed, direction, size);
            boardState[row][col] = shark;
        }
        Board board = new Board(boardState);
        for (int i = 0; i < c; i++) {
            board.catchShark();
            board.moveShark();
        }
        System.out.println(board.getFishedSize());
    }
}

class Board {
    private Shark[][] boardState;
    private int rows;
    private int cols;
    private int fisher;
    private int fishedSize;

    public Board(Shark[][] boardState) {
        this.boardState = boardState;
        this.rows = boardState.length - 1;
        this.cols = boardState[0].length - 1;
        fisher = 1;
        fishedSize = 0;
    }

    public void catchShark() {
        if (fisher > cols) {
            return;
        }
        for (int i = 1; i <= rows; i++) {
            if (boardState[i][fisher] != null) {
                fishedSize += boardState[i][fisher].getSize();
                boardState[i][fisher] = null;
                break;
            }
        }
        fisher++;
    }

    public int getFishedSize() {
        return fishedSize;
    }

    public void moveShark() {
        Shark[][] newBoardState = new Shark[rows + 1][cols + 1];
        for (int i = 1; i <= rows; i++) {
            for (int j = 1; j <= cols; j++) {
                Shark shark = boardState[i][j];
                if (shark == null) continue;
                Position move = shark.move(new Position(i, j), rows, cols);
                if (newBoardState[move.getX()][move.getY()] != null && shark.getSize() < newBoardState[move.getX()][move.getY()].getSize()) {
                    continue;
                }
                newBoardState[move.getX()][move.getY()] = shark;
            }
        }
        boardState = newBoardState;
    }

    public Shark getShark(int x, int y) {
        return boardState[x][y];
    }
}

class Position {
    private int x;
    private int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    @Override
    public boolean equals(Object o) {
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x && y == position.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}

class Shark {
    public static final int UP = 1;
    public static final int DOWN = 2;
    public static final int LEFT = 4;
    public static final int RIGHT = 3;

    private int speed;
    private int direction;
    private int size;

    public Shark(int speed, int direction, int size) {
        this.speed = speed;
        this.direction = direction;
        this.size = size;
    }

    public Position move(Position position, int height, int width) {
        if (speed == 0) {
            return position;
        }
        if (direction == UP || direction == DOWN) {
            return heightMove(position, height);
        } else if (direction == LEFT || direction == RIGHT) {
            return wideMove(position, width);
        }
        throw new RuntimeException("포지션을 찾을 수 없습니다.");
    }

    public int getSize() {
        return size;
    }

    private Position wideMove(Position position, int width) {
        int movingSize = speed % ((width - 1) * 2);

        while (movingSize > 0) {
            if (direction == RIGHT) {
                if (position.getY() + movingSize <= width) {
                    position = new Position(position.getX(), position.getY() + movingSize);
                    movingSize = 0;
                } else {
                    movingSize -= width - position.getY();
                    position = new Position(position.getX(), width);
                    this.direction = LEFT;
                }
            } else if (direction == LEFT) {
                if(position.getY() - movingSize >= 1) {
                    position = new Position(position.getX(), position.getY() - movingSize);
                    movingSize = 0;
                }else{
                    movingSize -= position.getY() - 1;
                    position = new Position(position.getX(), 1);
                    this.direction = RIGHT;
                }
            }
        }
        return position;
    }

    private Position heightMove(Position position, int height) {
        int movingSize = speed % ((height - 1) * 2);

        while (movingSize > 0) {
            if (direction == DOWN) {
                if (position.getX() + movingSize <= height) {
                    position = new Position(position.getX() + movingSize, position.getY());
                    movingSize = 0;
                } else {
                    movingSize -= height - position.getX();
                    position = new Position(height, position.getY());
                    this.direction = UP;
                }
            } else if (direction == UP) {
                if(position.getX() - movingSize >= 1) {
                    position = new Position(position.getX() - movingSize, position.getY());
                    movingSize = 0;
                }else{
                    movingSize -= position.getX() - 1;
                    position = new Position(1, position.getY());
                    this.direction = DOWN;
                }
            }
        }
        return position;
    }

    public int getDirection() {
        return direction;
    }
}
