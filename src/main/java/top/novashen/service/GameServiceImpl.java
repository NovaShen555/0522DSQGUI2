package top.novashen.service;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.Data;
import top.novashen.pojo.*;

import java.util.List;

import static top.novashen.pojo.ChessN.Mouse;

public class GameServiceImpl implements GameService{
    public Game game;
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
        for (Chess[] chessLine : game.board.getChessBoard())
            for (Chess chess : chessLine)
                if (chess != null)
                    if (chess.inDen())
                        return chess.getPlayer();
        return -1;
    }

    @Override
    public boolean isValuable(Step step) {
        Board board = game.getBoard();
        int FX = step.getFX();
        int FY = step.getFY();
        int TX = step.getTX();
        int TY = step.getTY();
//        System.out.println(FX + " " + FY + " " + TX + " " + TY);
        int currentPlayer = step.getCurrentPlayer();
        Chess[][] chessBoard = board.getChessBoard();
        ChessN chessN = null;
        if (board.getChessBoard()[FX][FY] != null)
            chessN = board.getChessBoard()[FX][FY].getChessN();

        // 移动必须在棋盘内
        // if (TX >= 0 && TX <= 9 && TY >= 0 && TY <= 7) return false;

        // 除了鼠之外，所有棋子都不能下水
        if (chessN != Mouse)
            if (board.getBoard(TX,TY) == BoardN.River) return false;
        // 鼠在水中无法吃象
        if (chessN == Mouse && board.getBoard(FX,FY) == BoardN.River)
            if (chessBoard[TX][TY] != null && chessBoard[TX][TY].getChessN() == ChessN.Elephant) return false;
        // 狮子和老虎可以跳湖
        if (chessN == ChessN.Lion || chessN == ChessN.Tiger)
            if (board.getBoard(FX,FY) == BoardN.Road){
                if (TX != FX && TY != FY)
                    return false;
                if (TX != FX){
                    int min = Math.min(TX,FX);
                    int max = Math.max(TX,FX);
                    for (int i = min+1; i < max; i++)
                        if (board.getBoard(i,TY) != BoardN.River)
                            return false;
                } else if (TY != FY){
                    int min = Math.min(TY,FY);
                    int max = Math.max(TY,FY);
                    for (int i = min+1; i < max; i++)
                        if (board.getBoard(TX,i) != BoardN.River)
                            return false;
                }
                return true;
            }
        // 不能吃自己的棋子
        if (chessBoard[TX][TY] != null && chessBoard[TX][TY].getPlayer() == currentPlayer)
            return false;
        // // 移动到空位可以
        // if (chessBoard[TX][TY] == null && board.getBoard(TX,TY) != BoardN.River) return true;
        // 不能吃比自己大的棋子
        if (chessBoard[TX][TY] != null && chessBoard[TX][TY].getChessN().compareTo(chessBoard[FX][FY].getChessN()) < 0) {
            // 如果是鼠吃象，则可以
            if (chessBoard[TX][TY].getChessN() == ChessN.Elephant && chessBoard[FX][FY].getChessN() == Mouse)
                return true;
            // 如果在陷阱，也可以
            else if (board.getBoard(TX,TY) == BoardN.Trap)
                return true;
            else
                return false;
        } else if (Math.abs(TX-FX)+Math.abs(TY-FY) == 1)
            return true;
        else
            return false;
    }

    @Override
    public int move(Step step) {
        if (isValuable(step)){
            game.getSteps().add(step);

            Board board = game.getBoard();
            Chess[][] curBoard = board.getChessBoard();
            int FX = step.getFX();
            int FY = step.getFY();
            int FPlayer = curBoard[FX][FY].getPlayer();
            ChessN FChessN = curBoard[FX][FY].getChessN();
            int TX = step.getTX();
            int TY = step.getTY();
            if (curBoard[TX][TY] != null) {
                board.getDeadChessList().add(new DeadChess(FPlayer,FChessN,game.getSteps().size()-1,FX,FY));
            }
            curBoard[TX][TY] = curBoard[FX][FY];
            curBoard[FX][FY] = null;
            curBoard[TX][TY].setX(TX);
            curBoard[TX][TY].setY(TY);
            board.setChessBoard(curBoard);
            game.setBoard(board);

            return 1;
        }
        return -1;
    }

    @Override
    public void withdraw() {
        if (game.getSteps().size() == 0) return;
        // 撤销最后一步
        Step step = game.getSteps().get(game.getSteps().size() - 1);
        int FX = step.getFX();
        int FY = step.getFY();
        int TX = step.getTX();
        int TY = step.getTY();
        Board board = game.getBoard();
        Chess[][] curBoard = board.getChessBoard();
        // 反向走
        curBoard[TX][TY] = curBoard[FX][FY];
        if (board.getDeadChessList().size() > 0){
            DeadChess deadChess = board.getDeadChessList().get(board.getDeadChessList().size() - 1);
            if (deadChess.getTurn() == game.getSteps().size() - 1){
                curBoard[deadChess.getX()][deadChess.getY()] = new Chess(deadChess.getX(),deadChess.getY(),deadChess.getChessN(),deadChess.getPlayer(),true);
                board.getDeadChessList().remove(board.getDeadChessList().size() - 1);
            }
        } else {
            curBoard[FX][FY] = null;
        }
        board.setChessBoard(curBoard);
        game.setBoard(board);
        game.getSteps().remove(game.getSteps().size() - 1);
    }
}
