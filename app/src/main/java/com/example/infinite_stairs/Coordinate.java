package com.example.infinite_stairs;

public class Coordinate {
    //x, y좌표 설정함 BasicBlock.java에서 얘 불러옴
    //계속 떨어질 때 얘가 소환됨 GameState에서도 얘가 사용됨

    int x, y, dir;


    Coordinate(int r, int c, int d) {
        this.y = r ;
        this.x = c;
        this.dir = d;
    } //x,y좌표랑 방향 받아오기 => 블록에 사용

    Coordinate(int r, int c) {
        this.y = r ;
        this.x = c;
    } //x,y좌표만 받아오기 => 기본 게임판 그릴 때 사용

    static Coordinate up(Coordinate A){
        return new Coordinate(A.y - 1, A.x - (-1)*A.dir , A.dir);
    } //up 버튼 누르면 좌표 위치 변경

    static Coordinate rotate(Coordinate A){
        return new Coordinate(A.y - 1, A.x - A.dir, -A.dir);
    }  //rotate눌렀을 떄 좌표 위치 변경




    static boolean isEqual(Coordinate A, Coordinate B) {
        return A.y == B.y && A.x == B.x;
    } //A랑 B의 좌표로 같은지 판단

}

