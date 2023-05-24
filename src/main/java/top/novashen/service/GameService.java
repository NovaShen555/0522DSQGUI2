package top.novashen.service;

import top.novashen.pojo.Game;
import top.novashen.pojo.Step;

public interface GameService {
    // Game to json
    String toJson();
    // Json to Game
    void readFromJson(String json);
    // Check if the game is over and who win
    int isWin();
    // Check step is valuable
    boolean isValuable(Step step);
    // Move chess
    int move(Step step);
    // Withdraw step
    void withdraw();
}
