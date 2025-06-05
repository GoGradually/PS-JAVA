package june_25.BOJ17144;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String[] s = br.readLine().split(" ");
        int n = Integer.parseInt(s[0]);
        int m = Integer.parseInt(s[1]);
        int time = Integer.parseInt(s[2]);
        int[][] boardState = new int[n][m];
        for (int i = 0; i < n; i++) {
            String[] line = br.readLine().split(" ");
            for (int j = 0; j < m; j++) {
                boardState[i][j] = Integer.parseInt(line[j]);
            }
        }
        Board board = new Board(boardState);
        for (int i = 0; i < time; i++) {
            board.spread();
            board.sweep();
        }
        System.out.println(board.getAllDust());
    }
}

class Board {
    private int[][] boardState;

    private AirConditioner airConditioner;

    public Board(int[][] boardState) {
        this.boardState = boardState;
        createAirConditioner(boardState);
    }

    public int getDust(int i, int j) {
        return boardState[i][j];
    }

    public int getAllDust(){
        int ret = 0;
        for(int i = 0; i<boardState.length; i++){
            for(int j = 0; j<boardState[0].length; j++){
                if(boardState[i][j] == -1){
                    continue;
                }
                ret+= boardState[i][j];
            }
        }
        return ret;
    }

    public void spread() {
        int[][] newBoardState = new int[boardState.length][boardState[0].length];
        for (int i = 0; i < boardState.length; i++) {
            for (int j = 0; j < boardState[0].length; j++) {
                spread(i, j, newBoardState);
            }
        }
        boardState = newBoardState;
    }

    public void sweep(){
        airConditioner.sweep(boardState);
    }

    private void createAirConditioner(int[][] boardState) {
        for (int i = 0; i < boardState.length; i++) {
            if(boardState[i][0] == -1){
                airConditioner = new AirConditioner(i, i+1);
                break;
            }
        }
    }

    private void spread(int i, int j, int[][] newBoardState) {
        int spreadCount = 0;
        if (boardState[i][j] == -1) {
            newBoardState[i][j] = -1;
            return;
        }
        int nowDust = boardState[i][j];
        int spreadDust = nowDust / 5;

        int[] dx = {-1, 1, 0, 0};
        int[] dy = {0, 0, -1, 1};
        for (int k = 0; k < 4; k++) {
            int p = i + dx[k];
            int q = j + dy[k];
            if (p < 0 || p >= boardState.length || q < 0 || q >= boardState[0].length) continue;
            if (boardState[p][q] == -1) {
                continue;
            }
            newBoardState[p][q] += spreadDust;
            spreadCount++;
        }
        newBoardState[i][j] += nowDust - spreadDust * spreadCount;
    }
}
class AirConditioner {
    private int upside = 0;
    private int downside = 0;

    public AirConditioner(int upside, int downside) {
        this.upside = upside;
        this.downside = downside;
    }

    public void sweep(int[][] grid) {
        sweepUpside(grid);
        sweepDownside(grid);
    }

    private void sweepUpside(int[][] grid) {
        for (int i = upside - 2; i >= 0; i--) {
            grid[i + 1][0] = grid[i][0];
        }
        for (int i = 0; i < grid[0].length - 1; i++) {
            grid[0][i] = grid[0][i + 1];
        }
        for (int i = 0; i < upside; i++) {
            grid[i][grid[0].length - 1] = grid[i+1][grid[0].length - 1];
        }
        for (int i = grid[0].length - 1; i > 1; i--) {
            grid[upside][i] = grid[upside][i - 1];
        }
        grid[upside][1] = 0;
    }

    private void sweepDownside(int[][] grid) {
        for (int i = downside + 1; i < grid.length - 1; i++) {
            grid[i][0] = grid[i+1][0];
        }
        for (int i = 0; i < grid[0].length - 1; i++) {
            grid[grid.length-1][i] = grid[grid.length-1][i+1];
        }
        for (int i = grid.length-1; i > downside; i--) {
            grid[i][grid[0].length-1] = grid[i - 1][grid[0].length-1];
        }
        for (int i = grid[0].length-1; i > 1; i--) {
            grid[downside][i] = grid[downside][i-1];
        }
        grid[downside][1] = 0;
    }
}
