package top.novashen.gui;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.List;

public class Button {
    private int curP,x,y;
    public JLabel jLabel;

    public Button(String pic,int curP, int x, int y, MouseAdapter mouseAdapter) {
        this.curP = curP;
        this.x = x;
        this.y = y;
        this.jLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(pic)));
        this.jLabel.setBounds(x * 50 + guiT.dx, y * 50 + guiT.dy, 30, 30);
        this.jLabel.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点击按钮");
                // GuiC.gameService
            }
        });
    }
}
