package com.example.infinite_stairs;

class GameState {
    private final int ROWS = 19;
    private final int COLS = 40;

    boolean status; //죽었는지 살았는지
    int score;  //점수
    boolean pause; //일시정지
    BasicBlock[][] board;
    boolean difficultMode; //어려운 모드
    private int rows; //열
    private int columns; //행
    private Integer ctr;
    private int direction = 1;

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

    void initBlock() {
        // 이미지 객체 초기화 및 배열에 저장, 버튼 누르기 전에 불러와야 함
        // 위 보드에서 BasicBlock[rows][colums]을 생성했잖아 이제 여기서 초기화할 좌표의 상태를 ON_BLOCK으로 만들어주면 됨
        // 시작 지점 2,10
        int a = 0;
        int under_block = 5; //추후에 생성될 블록 개수임 상수처럼 쓰려고 변수 만들었음
        setBlockState(ROWS-under_block, 3, BasicBlockState.ON_BLOCK); //10행 2열이 시작점
        setBlockState(ROWS-under_block-1, 2, BasicBlockState.ON_BLOCK); //시작점과 그 다음점은 고정해야함(rotate할 때 부호 때매)
        int saveColValue = 2;
        for (int i = ROWS - under_block -2; i >= 0; i--) {
            a = Math.random() > 0.5 ? 1 : -1;
            if(!isValidCoordinate(saveColValue + a)){  //유효하지 않으면 1 추가
                a *= -1;
            }
            setBlockState(saveColValue + a, i, BasicBlockState.ON_BLOCK);
            saveColValue += a;
        }
    }

    void updateBlock(int dir) { //dir은 캐릭터가 올라가는 방향 캐릭터가 왼쪽으로 올라가면 블록이 오른쪽으로 움직이며 +1, 오른쪽으로 올라가면 블록이 왼쪽으로 움직이며 -1
        //up 누르면 계속 +1되고 rotate누르면 -1곱해주기 근데 이 과정이 저장이 되어야 하는데 음
        //꼭 맨 밑부터 실행되어야함 위에서
        // 객체 위치 업데이트, 버튼 누르면 실행
        // => 만약 ROWS값 있으면 ROWS는 1로 가게 예외로 로직 따로 만들면 됨
        if(dir == 0){ //rotate 클릭시 dir에 0받아오기
            direction *= -1; //내부 함수 변경 => 불러올 때 이전 값을 기억하도록
        }

        for (int i = ROWS-1; i >= 0; i--) { //꼭 맨 밑부터 실행 되어야해서 ROW 값부터 실행
            for (int j = 0; j < COLS; j++) {
                if (board[i][j].state == BasicBlockState.ON_BLOCK) { //꼭 삭제부터 해야함
                    setBlockState(j, i, BasicBlockState.ON_EMPTY);
                    if (i != ROWS - 1) { //맨 밑에 있는 블록은 그 밑에 블록을 더 생성하면 안됨
                        setBlockState(j + 1, i + direction, BasicBlockState.ON_BLOCK);
                    }
                }
            }
        }
        int a = 0;
        for(int i = 0; i < ROWS; i++){ //ROWS가 아니라 COLS일수도.,.,,.
            if(board[i][1].state == BasicBlockState.ON_BLOCK){ //두번째 열 불륵 찾고 그 대각선에 블록 생성
                a = Math.random() > 0.5 ? 1 : -1;
                setBlockState(i+a, 0, BasicBlockState.ON_BLOCK);
            }
        } //첫번째줄에 랜덤으로 블록 생성


    }
    //아 근데 만약에 이렇게 계단 내려오면서 배열을 초과하는 경우는 어떡하지 => 열 개수 그냥 40으로 설정

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
        return (row >= 0 && row < ROWS && col >= 0 && col < COLS);
    }

    private boolean isValidCoordinate(int row) {
        return (row >= 0 && row < ROWS );
    } //일단 배열 따라 오면서 계단 내려오며 배열 초과하는 경우 방지하려 만들어놨는데 어떻게 방지해야할지 모르곘음 일단 만듦





}
