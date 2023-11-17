package com.example.infinite_stairs;

import android.util.SparseArray;

class GameState {
    private final int ROWS = 15;
    private final int COLS = 7;

    boolean status; //죽었는지 살았는지
    int score;  //점수
    boolean pause; //일시정지
    BasicBlock[][] board;
    boolean difficultMode; //어려운 모드
    private int rows; //열
    private int columns; //행
    private Integer ctr;

    GameState(int rows, int columns) {
        this.rows = rows;
        this.columns = columns;
        this.pause = false;
        ctr = 0; //맨 처음 생성되면0, 내려갈때마다 1씩 부여
        score = 0;
        this.status = true;
        difficultMode = false;

        board = new BasicBlock[rows][columns];
        for (int row = 0; row < rows; row++) {
            for (int column = 0; column < columns; column++) {
                board[row][column] = new BasicBlock(row, column);
            }
        }

    }

    void initGameObjects() {
        // 이미지 객체 초기화 및 배열에 저장, 버튼 누르기 전에 불러와야 함
        // 위 보드에서 BasicBlock[rows][colums]을 생성했잖아 이제 여기서 초기화할 좌표의 상태를 ON_BLOCK으로 만들어주면 됨
        // 시작 지점 3,10
        int a = 0;
        setBlockState(3, 10, BasicBlockState.ON_BLOCK);
        int saveColValue = 3;
        for (int i = 9; i >= 0; i--) {
            a = Math.random() > 0.5 ? 1 : -1;
            setBlockState(saveColValue + a, i, BasicBlockState.ON_BLOCK);
            saveColValue += a;
        }
    }

    public void setBlockState(int row, int col, BasicBlockState state) {
        if (isValidCoordinate(row, col)) {
            board[row][col].state = state;
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        // 좌표의 유효성을 확인하는 로직 추가 (예: 행과 열이 유효한 범위 내에 있는지 확인)
        // 행은 0부터 14, 열은 0부터 6까지인지 확인
        return (row >= 0 && row < 15 && col >= 0 && col < 7);
    }




}
