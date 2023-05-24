package top.novashen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
public class Chess {
    // Current position
    private int x,y;
    // Chess type
    private ChessN chessN;
    // Chess player 0/1 (blue/red) default 0
    private int player = 0;
    // Chess is alive
    private boolean alive = true;

    public boolean isDen() {
        if (x == 0 && y == 3 && player == 1) return true;
        if (x == 8 && y == 3 && player == 0) return true;
        return false;
    }
}
