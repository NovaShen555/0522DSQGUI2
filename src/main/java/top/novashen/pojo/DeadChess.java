package top.novashen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeadChess {
    // Chess player 0/1 (blue/red) default 0
    private int player = 0;
    // Chess type
    private ChessN chessN;
    // Dead turn
    private int turn;
    // Position
    private int x,y;
}
