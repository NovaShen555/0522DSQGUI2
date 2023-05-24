package top.novashen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import top.novashen.pojo.*;

import java.util.List;

import static top.novashen.pojo.ChessN.Mouse;

public class GameServiceImpl implements GameService{
    private Game game;
    private final ObjectMapper objectMapper = new ObjectMapper();
    public GameServiceImpl() {
        game = new Game();
    }

    @Override
    public String toJson() {
        String json = null;
        try {
            json = objectMapper.writeValueAsString(game);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
        return json;
    }

    @Override
    public void readFromJson(String json) {
        try {
            game = objectMapper.readValue(json, Game.class);
        } catch (JsonProcessingException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int isWin() {
        for (Chess chess : game.board.chessList)
            if (chess.isDen())
                return chess.getPlayer();
        return -1;
    }

    @Override
    public boolean isValuable(Step step) {
        Board board = game.getBoard();
        // 移动必须在棋盘内
        if (step.getX() >= 0 && step.getX() <= 9 && step.getY() >= 0 && step.getY() <= 7) return false;
        // 移动到空位可以
        if (board.getChessBoard()[step.getX()][step.getY()] == null) return true;
        // 除了鼠之外，所有棋子都不能下水
        if (step.getChess().getChessN() != Mouse)
            if (board.getBoard(step.getX(),step.getY()) == BoardN.River) return false;
        // 鼠在水中无法吃象
        if (step.getChess().getChessN() == Mouse && board.getBoard(step.getChess().getX(),step.getChess().getY()) == BoardN.River)
            if (board.getChessBoard()[step.getX()][step.getY()].getChessN() == ChessN.Elephant) return false;
        // 狮子和老虎可以跳湖
        if (step.getChess().getChessN() == ChessN.Lion || step.getChess().getChessN() == ChessN.Tiger)
            if (board.getBoard(step.getChess().getX(),step.getChess().getY()) == BoardN.Road){
                if (step.getX() != step.getChess().getX() && step.getY() != step.getChess().getY())
                    return false;
                if (step.getX() != step.getChess().getX()){
                    int min = Math.min(step.getX(),step.getChess().getX());
                    int max = Math.max(step.getX(),step.getChess().getX());
                    for (int i = min+1; i < max; i++)
                        if (board.getBoard(i,step.getY()) != BoardN.River)
                            return false;
                } else if (step.getY() != step.getChess().getY()){
                    int min = Math.min(step.getY(),step.getChess().getY());
                    int max = Math.max(step.getY(),step.getChess().getY());
                    for (int i = min+1; i < max; i++)
                        if (board.getBoard(step.getX(),i) != BoardN.River)
                            return false;
                }
                return true;
            }
        // 不能吃自己的棋子
        if (board.getChessBoard()[step.getX()][step.getY()].getPlayer() == step.getChess().getPlayer())
            return false;
        // 不能吃比自己大的棋子
        if (board.getChessBoard()[step.getX()][step.getY()].getChessN().compareTo(step.getChess().getChessN()) > 0) {
            // 如果是鼠吃象，则可以
            if (board.getChessBoard()[step.getX()][step.getY()].getChessN() == ChessN.Elephant && step.getChess().getChessN() == Mouse)
                return true;
            // 如果在陷阱，也可以
            else if (board.getBoard()[step.getX()][step.getY()] == BoardN.Trap)
                return true;
            else
                return false;
        }
        // 其他情况 TODO
        return false;
    }

    @Override
    public int move(Step step) {
        if (isValuable(step)){
            List<Step> steps = game.getSteps();
            steps.add(step);
            game.setSteps(steps);
            Board board = game.getBoard();
            Chess[][] curBoard = board.getChessBoard();
            int bX = step.getChess().getX();
            int bY = step.getChess().getY();
            int lX = step.getX();
            int lY = step.getY();
            if (curBoard[lX][lY] != null){
                // 应将ChessBoard中改为对List的引用 TODO
            }
        }
        return -1;
    }

    @Override
    public void withdraw() {

    }
}
