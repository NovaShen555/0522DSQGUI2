package top.novashen.gui;

import top.novashen.pojo.BoardN;
import top.novashen.pojo.Chess;
import top.novashen.pojo.ChessN;
import top.novashen.service.GameService;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.List;

public class guiT extends JFrame {

    List<BoardP> boardPList = new ArrayList<>();
    GuiService guiService;
    static int dx = 3, dy = -20;

    public guiT() throws HeadlessException {
        guiService = new GuiService();
        this.setTitle("DSQ");
        // 设置窗体大小
        this.setSize(450, 550);
        // 设置窗体大小可改变
        this.setResizable(false);
        // 设置窗体显示正中央
        this.setLocationRelativeTo(null);
        // 设置默认关闭方式，关闭窗体的同时结束程序
        this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        // 将窗体显示出来
        this.setVisible(true);
        // 初始化棋盘
        boardPList.add(new BoardP(BoardN.Trap,3,1));
        boardPList.add(new BoardP(BoardN.Den,4,1));
        boardPList.add(new BoardP(BoardN.Trap,5,1));
        boardPList.add(new BoardP(BoardN.Trap,4,2));
        boardPList.add(new BoardP(BoardN.River,2,4));
        boardPList.add(new BoardP(BoardN.River,3,4));
        boardPList.add(new BoardP(BoardN.River,5,4));
        boardPList.add(new BoardP(BoardN.River,6,4));
        boardPList.add(new BoardP(BoardN.River,2,5));
        boardPList.add(new BoardP(BoardN.River,3,5));
        boardPList.add(new BoardP(BoardN.River,5,5));
        boardPList.add(new BoardP(BoardN.River,6,5));
        boardPList.add(new BoardP(BoardN.River,2,6));
        boardPList.add(new BoardP(BoardN.River,3,6));
        boardPList.add(new BoardP(BoardN.River,5,6));
        boardPList.add(new BoardP(BoardN.River,6,6));
        boardPList.add(new BoardP(BoardN.Trap,4,8));
        boardPList.add(new BoardP(BoardN.Trap,3,9));
        boardPList.add(new BoardP(BoardN.Den,4,9));
        boardPList.add(new BoardP(BoardN.Trap,5,9));

    }

    @Override
    public void paint(Graphics g) {
        g.setColor(Color.black);
        int sx = 50,sy = 50;
        int dx = 50,dy = 50;
        // 画一个7*9的棋盘
        for (int i = 0; i < 10; i++)
            g.drawLine(sx,sy + i * dy,sx + 7 * dx,sy + i * dy);
        for (int i = 0; i < 8; i++)
            g.drawLine(sx + i * dx,sy,sx + i * dx,sy + 9 * dy);
        // 画出棋盘上的陷阱、河流、兽穴
        for (BoardP boardP : boardPList) {
            boardP.draw(g);
        }
    }

    public void updatePaint(Graphics g, int x, int y) {
        g.setColor(Color.yellow);
        g.fillRect(x * 50+1,y * 50+1,50-1,50-1);
    }

    public static void main(String[] args) {
        guiT guiT = new guiT();
        guiT.setLayout(null);
        MouseAdapter mouseAdapter = new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点击按钮");
                // List<int[]> generateStep = guiT.guiService.generateStep(curP, x, y);
                // for (int[] k : generateStep) {
                //     System.out.println(k[0] + " " + k[1]);
                //     top.novashen.gui.guiT.updatePaint(guiT.getGraphics(),k[0],k[1]);
                // }
                //  pane.remove(0);
                //  reflash.setBounds(6*50 + guiT.dx, 7*50 + guiT.dy, 30, 30);
                //  pane.add(reflash);
                //  guiT.paint(guiT.getGraphics());
                //  guiT.updatePaint(guiT.getGraphics(),5,7);
            }
            // @Override
            // public void mouseEntered(MouseEvent e) {
            //     //鼠标状态设置成手型
            //     button.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            // }
            // @Override
            // public void mouseExited(MouseEvent e) {
            //     //鼠标恢复默认状态
            //     super.setCursor(Cursor.getDefaultCursor());
            // }
        };

        // Button button = new Button("./images/readf.png",1, 1, 1,,mouseAdapter);
        // JLayeredPane pane = guiT.getLayeredPane();
        // pane.add(button.jLabel, JLayeredPane.PALETTE_LAYER);


    }
}
