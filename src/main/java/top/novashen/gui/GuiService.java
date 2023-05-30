package top.novashen.gui;

import top.novashen.pojo.BoardN;
import top.novashen.pojo.Chess;
import top.novashen.pojo.Step;
import top.novashen.service.GameServiceImpl;

import java.util.ArrayList;
import java.util.List;

public class GuiService {
    static GameServiceImpl gameService;
    GuiService() {
        gameService = new GameServiceImpl();
    }
    static List<int[]> generateStep(int curP, int x,int y) {
        // convert
        x --;
        y = 9 - y;
        int tmp = x;
        x = y;
        y = tmp;

        Chess[][] chessBoard = gameService.game.getBoard().getChessBoard();

        Chess chess = chessBoard[x][y];
        List<int[]> result = new ArrayList<>();
        int[] dx = {0,1,0,-1},dy = {1,0,-1,0};
        for (int i = 0; i <= 3; i++) {
            if (x + dx[i] < 0 || x + dx[i] >8 || y + dy[i] < 0 || y + dy[i] > 6)
                continue;
            System.out.println((x+dx[i])+ " " + (y+dy[i]));
            if (gameService.isValuable(new Step(curP, x, y, x + dx[i], y + dy[i])))
                result.add(new int[]{y + dy[i] + 1 ,9- x - dx[i]});
            if (gameService.game.getBoard().getBoard(x+dx[i],y+dy[i]).equals(BoardN.River)) {
                int tx = x+dx[i],ty = y+dy[i];
                while (gameService.game.getBoard().getBoard(tx,ty).equals(BoardN.River)) {
                    tx += dx[i];
                    ty += dy[i];
                }
                if (gameService.isValuable(new Step(curP, x, y, tx,ty)))
                    result.add(new int[]{tx, ty});
            }
        }
        return result;
    }
}
