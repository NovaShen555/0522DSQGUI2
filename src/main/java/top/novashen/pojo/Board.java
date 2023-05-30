package top.novashen.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Board {
    // Default board
    private BoardN[][] board = new BoardN[9][7];
    // Current board
    private Chess[][] chessBoard = new Chess[9][7];
    // Dead chess
    private List<DeadChess> deadChessList;

    public Board() {
        deadChessList = new ArrayList<>();
        // Initialize board
        this.board[0][2] = BoardN.Trap;
        this.board[0][4] = BoardN.Trap;
        this.board[1][3] = BoardN.Trap;
        this.board[8][2] = BoardN.Trap;
        this.board[8][4] = BoardN.Trap;
        this.board[7][3] = BoardN.Trap;
        this.board[3][1] = BoardN.River;
        this.board[3][2] = BoardN.River;
        this.board[3][4] = BoardN.River;
        this.board[3][5] = BoardN.River;
        this.board[4][1] = BoardN.River;
        this.board[4][2] = BoardN.River;
        this.board[4][4] = BoardN.River;
        this.board[4][5] = BoardN.River;
        this.board[5][1] = BoardN.River;
        this.board[5][2] = BoardN.River;
        this.board[5][4] = BoardN.River;
        this.board[5][5] = BoardN.River;
        this.board[0][3] = BoardN.Den;
        this.board[8][3] = BoardN.Den;
        for (int i = 0; i < 9; i++)
            for (int j = 0; j < 7; j++)
                if (this.board[i][j] == null)
                    this.board[i][j] = BoardN.Road;
        // Initialize chess
        this.chessBoard[0][0] = new Chess(0, 0, ChessN.Tiger, 0, true);
        this.chessBoard[2][0] = new Chess(2, 0, ChessN.Elephant, 0, true);
        this.chessBoard[1][1] = new Chess(1, 1, ChessN.Cat, 0, true);
        this.chessBoard[2][2] = new Chess(2, 2, ChessN.Wolf, 0, true);
        this.chessBoard[2][4] = new Chess(2, 4, ChessN.Leopard, 0, true);
        this.chessBoard[2][6] = new Chess(2, 6, ChessN.Mouse, 0, true);
        this.chessBoard[0][6] = new Chess(0, 6, ChessN.Lion, 0, true);
        this.chessBoard[1][5] = new Chess(1, 5, ChessN.Dog, 0, true);
        this.chessBoard[6][0] = new Chess(6, 0, ChessN.Mouse, 1, true);
        this.chessBoard[6][2] = new Chess(6, 2, ChessN.Leopard, 1, true);
        this.chessBoard[6][4] = new Chess(6, 4, ChessN.Wolf, 1, true);
        this.chessBoard[6][6] = new Chess(6, 6, ChessN.Elephant, 1, true);
        this.chessBoard[8][6] = new Chess(8, 6, ChessN.Tiger, 1, true);
        this.chessBoard[7][5] = new Chess(7, 5, ChessN.Cat, 1, true);
        this.chessBoard[8][0] = new Chess(8, 0, ChessN.Lion, 1, true);
        this.chessBoard[7][1] = new Chess(7, 1, ChessN.Dog, 1, true);

    }

    public BoardN getBoard(int x, int y) {
        return board[x][y];
    }

}
