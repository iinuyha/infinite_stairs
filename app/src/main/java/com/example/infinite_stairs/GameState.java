package com.example.infinite_stairs;

import android.util.SparseArray;

class GameState {
    private final int ROWS = 19;
    private final int COLS = 9;

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
        // 시작 지점 2,10
        int a = 0;
        int under_block = 5; //추후에 생성될 블록 개수임 상수처럼 쓰려고 변수 만들었음
        setBlockState(ROWS-under_block, 2, BasicBlockState.ON_BLOCK); //10행 2열이 시작점
        int saveColValue = 2;
        for (int i = ROWS - under_block -1; i >= 0; i--) {
            a = Math.random() > 0.5 ? 1 : -1;
            setBlockState(saveColValue + a, i, BasicBlockState.ON_BLOCK);
            saveColValue += a;
        }
    }

    private void updateGameObjects() {
        // 객체 위치 업데이트, 버튼 누르면 실행
        // 만약 그 칸이 1이면 그 칸의 [x-a][y+1]값을 이미지 처리하고 1넣고 해당칸 이미지랑 1없애기 => 밑에서부터 처리
        // => 만약 15있으면 15는 1로 가게 예외로 로직 따로 만들면 됨
        // a 저장 처리 나중에 해야하나?
        int direction = 1;  // Assuming direction is used in your actual code
        int a = 1;
        for (int i = ROWS; i >= 0; i--) {
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].state == BasicBlockState.ON_EMPTY) {
                    setBlockState(j, i, BasicBlockState.ON_EMPTY);
                    if (i != ROWS - 1) { //마지막 값에는 실행 안되어야함
                        a = Math.random() > 0.5 ? 1 : -1;
                        setBlockState(j + 1, i - a, BasicBlockState.ON_BLOCK);
                    }
                }
            }
        }
    }

    public void setBlockState(int col, int row, BasicBlockState state) {
        if (isValidCoordinate(row, col)) {
            board[row][col].state = state;
        }
    }

    public void removeBlock(int col, int row) {
        if (isValidCoordinate(row, col)) {
            board[row][col].state = BasicBlockState.ON_EMPTY;
        }
    }

    private boolean isValidCoordinate(int row, int col) {
        // 좌표의 유효성을 확인하는 로직 추가 (예: 행과 열이 유효한 범위 내에 있는지 확인)
        // 행은 0부터 14, 열은 0부터 6까지인지 확인
        return (row >= 0 && row < ROWS && col >= 0 && col < COLS);
    }





}
