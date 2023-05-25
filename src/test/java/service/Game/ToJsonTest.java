package service.Game;

import org.junit.Test;
import top.novashen.service.GameService;
import top.novashen.service.GameServiceImpl;

public class ToJsonTest {
    @Test
    public void t() {
        GameService gameService = new GameServiceImpl();
        System.out.println(gameService.toJson());
        gameService.readFromJson(gameService.toJson());
    }
}
