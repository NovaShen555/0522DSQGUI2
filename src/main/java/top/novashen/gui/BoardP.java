package top.novashen.gui;

import lombok.AllArgsConstructor;
import lombok.Data;
import top.novashen.pojo.BoardN;

import java.awt.*;

@Data
@AllArgsConstructor
public class BoardP {
    BoardN boardN;
    int x,y;

    public void draw(Graphics g) {
        if (this.boardN.equals(BoardN.Trap))
            g.setColor(Color.red);
        else if (this.boardN.equals(BoardN.River))
            g.setColor(Color.blue);
        else if (this.boardN.equals(BoardN.Den))
            g.setColor(Color.green);
        g.fillRect(x * 50+1,y * 50+1,50-1,9);
        g.fillRect(x * 50+1,y * 50+1,9,50-1);
        g.fillRect(x * 50+1+40,y * 50+1,9,50-1);
        g.fillRect(x * 50+1,y * 50+1+40,50-1,9);
    }
}
