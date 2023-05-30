package top.novashen.gui;


import java.awt.Cursor;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;



public class ImageButon {

    public static void main(String[] args) {
        JFrame frame=new JFrame("图片上放置图片按钮");
        frame.setLayout(null);
        frame.setSize(400,300);
        frame.setLocationRelativeTo(null);

        Image img=new ImageIcon(ClassLoader.getSystemResource("./images/1.jpg")).getImage();
        JLabel panel=new JLabel(new ImageIcon(img));
        panel.setBounds(10, 9, 300, 300);
        Image img2 =new ImageIcon(ClassLoader.getSystemResource("./images/big_a.png")).getImage();
        final JLabel reflash = new JLabel(new ImageIcon(img2));


        final JComponent p3 = frame.getLayeredPane();

        reflash.setBounds(144, 90, 60, 60);

        p3.add(reflash,10);
        p3.add(panel, 5);
//        frame.getContentPane().add(panel);

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);

        reflash.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                System.out.println("点击刷新按钮");
                p3.repaint();
            }
            // 鼠标已经进入窗体
            @Override
            public void mouseEntered(MouseEvent e) {
                //鼠标状态设置成手型
                reflash.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            }
            //鼠标移除窗体
            @Override
            public void mouseExited(MouseEvent e) {
                //鼠标恢复默认状态
                reflash.setCursor(Cursor.getDefaultCursor());

            }

        });
    }
}