package top.novashen.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Step {
    // Current player 0/1 (blue/red) default 0
    private int currentPlayer = 0;
    // Move to
    private int FX,FY,TX,TY;

}
