package top.novashen.pojo;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data
public class Game {
    // Current player 0/1 (blue/red) default 0
    private int currentPlayer = 0;
    // Every step history
    private List<Step> steps = new ArrayList<>();
    // Current board
    public Board board = new Board();

}
