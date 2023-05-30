package gui;

import top.novashen.gui.GuiC;
import top.novashen.gui.ImgLabel;
import top.novashen.gui.guiT;
import top.novashen.pojo.Chess;
import top.novashen.pojo.ChessN;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class Tetst1 {
    public static void main(String[] args) {
        GuiC guiC = new GuiC();
        guiC.setLayout(null);
//        String pic = "./images/red.png";
//        JLabel jLabel = new JLabel(new ImageIcon(ClassLoader.getSystemResource(pic)));
//        jLabel.setBounds(1 * 50 + guiC.dx, 1 * 50 + guiC.dy, 30, 30);
        // jLabel.addMouseListener(guiC);
        guiC.addMouseListener(guiC);
        JLayeredPane pane = guiC.getLayeredPane();
//        pane.add(jLabel, JLayeredPane.PALETTE_LAYER);


        guiC.repaint();

//        ImgLabel imgLabel = new ImgLabel(0, 7, 7, GuiC.getSrc(ChessN.Mouse, 0));
//        pane.add(imgLabel.pic, JLayeredPane.PALETTE_LAYER);
    }
}
