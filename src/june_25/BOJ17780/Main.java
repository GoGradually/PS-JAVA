package june_25.BOJ17780;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

    // Mal 클래스: 각 말의 정보를 저장
    static class Mal {
        int id;
        int r, c;
        int dir;

        public Mal(int id, int r, int c, int dir) {
            this.id = id;
            this.r = r;
            this.c = c;
            this.dir = dir;
        }
    }

    static int N, K;
    static int[][] board;
    static Deque<Mal>[][] map;
    static Mal[] mals;
    static int[] dr = {0, 0, -1, 1};
    static int[] dc = {1, -1, 0, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());

        board = new int[N][N];
        map = new ArrayDeque[N][N];
        mals = new Mal[K + 1];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                board[i][j] = Integer.parseInt(st.nextToken());
                map[i][j] = new ArrayDeque<>();
            }
        }

        for (int i = 1; i <= K; i++) {
            st = new StringTokenizer(br.readLine());
            int r = Integer.parseInt(st.nextToken()) - 1;
            int c = Integer.parseInt(st.nextToken()) - 1;
            int dir = Integer.parseInt(st.nextToken()) - 1;
            mals[i] = new Mal(i, r, c, dir);
            map[r][c].add(mals[i]);
        }

        int turn = 1;
        while (turn <= 1000) {
            if (moveAll()) {
                System.out.println(turn);
                return;
            }
            turn++;
        }

        System.out.println(-1);
    }

    static boolean moveAll() {
        for (int i = 1; i <= K; i++) {
            Mal currentMal = mals[i];
            int r = currentMal.r;
            int c = currentMal.c;

            if (map[r][c].peekFirst().id != i) {
                continue;
            }

            int nr = r + dr[currentMal.dir];
            int nc = c + dc[currentMal.dir];

            if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 2) {
                currentMal.dir = reverseDir(currentMal.dir);
                nr = r + dr[currentMal.dir];
                nc = c + dc[currentMal.dir];
                if (nr < 0 || nr >= N || nc < 0 || nc >= N || board[nr][nc] == 2) {
                    continue;
                }
            }

            if (moveStack(r, c, nr, nc)) {
                return true;
            }
        }
        return false;
    }

    static boolean moveStack(int r, int c, int nr, int nc) {
        Deque<Mal> movingStack = map[r][c];
        map[r][c] = new ArrayDeque<>(); // 기존 칸 비우기

        if (board[nr][nc] == 1) {
            Deque<Mal> tempStack = new ArrayDeque<>();
            while (!movingStack.isEmpty()) {
                tempStack.add(movingStack.pollLast());
            }
            movingStack = tempStack;
        }

        for (Mal mal : movingStack) {
            mal.r = nr;
            mal.c = nc;
            map[nr][nc].addLast(mal);
        }

        return map[nr][nc].size() >= 4;
    }

    static int reverseDir(int dir) {
        switch (dir) {
            case 0: return 1;
            case 1: return 0;
            case 2: return 3;
            case 3: return 2;
        }
        return -1;
    }
}