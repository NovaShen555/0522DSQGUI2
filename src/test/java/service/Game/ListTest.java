package service.Game;

import lombok.Data;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListTest {
    @Test
    public void t() {
       A b = new A();
        int[] ccc = b.getCcc();
        ccc[1] = 114514;
        System.out.println(Arrays.toString(b.getCcc()));
    }
}
@Data
class A {
    List<Integer> a = new ArrayList<>();
    int[] ccc;
}
