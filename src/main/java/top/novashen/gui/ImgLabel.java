package top.novashen.gui;

import javax.swing.*;

public class ImgLabel {
    int curP;
    public int x;
    public int y;
    public JLabel pic;

    public ImgLabel(int curP, int x, int y, String src) {
        this.curP = curP;
        this.x = x;
        this.y = y;
        pic = new JLabel(new ImageIcon(ClassLoader.getSystemResource(src)));
        pic.setBounds(x * 50 + GuiC.dx, y * 50 + GuiC.dy, 30, 30);
    }

    void setPos(int x, int y) {
        this.x = x;
        this.y = y;
        pic.setBounds(x * 50 + GuiC.dx, y * 50 + GuiC.dy, 30, 30);
    }
}
