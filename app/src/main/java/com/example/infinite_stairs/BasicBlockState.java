package com.example.infinite_stairs;

enum BasicBlockState {
    ON_EMPTY,
    ON_BLOCK
}

class BasicBlock {
    int colour;
    int tetraId;
    Coordinate coordinate; //열과 행 받아옴
    BasicBlockState state;

    //basicBlock이랑 emptyBlock 두가지를 정의하고 있음
    //기본 게임판
    //기본 게임판
    BasicBlock(int row, int column) {
        this.colour = -1;   //색
        this.tetraId = -1;   //식별자
        this.coordinate = new Coordinate(row, column); //열이랑 행 받음
        this.state = BasicBlockState.ON_EMPTY; //모두 빈 상태로 유지
    }

    //블록
    // tetraId 필요할지 모르겠는데 일단 보류
    BasicBlock(int tetraId, Coordinate coordinate, BasicBlockState state) {
        this.tetraId = tetraId;
        this.coordinate = coordinate;
        this.state = BasicBlockState.ON_BLOCK; //해당 좌표가 블록 있는걸로 표시

    }

    BasicBlock copy() {

        return new BasicBlock(tetraId, coordinate, state);
    }

    //tetraId 보류(필요할지 모름)
    void set(BasicBlock B) {
        this.tetraId = B.tetraId;
        this.coordinate.y = B.coordinate.y;
        this.coordinate.x = B.coordinate.x;
        this.state = BasicBlockState.ON_BLOCK;

    }


    void setEmptyBlock(Coordinate coordinate) {
        this.colour = -1;
        this.tetraId = -1;
        this.coordinate.x = coordinate.x;
        this.coordinate.y = coordinate.y;
        this.state = BasicBlockState.ON_EMPTY;

    }
}


