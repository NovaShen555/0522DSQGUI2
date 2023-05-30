package top.novashen.gui;

import top.novashen.pojo.BoardN;
import top.novashen.pojo.Chess;
import top.novashen.pojo.ChessN;
import top.novashen.pojo.Step;
import top.novashen.service.GameServiceImpl;

import javax.swing.*;
import javax.swing.filechooser.FileFilter;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class GuiC extends JFrame implements MouseListener {
    List<BoardP> boardPList = new ArrayList<>();
    public static GameServiceImpl gameService = new GameServiceImpl();
    List<int[]> waitingList = new ArrayList<>();
    List<ImgLabel> picList = new ArrayList<>();
    public static int dx = 3;
    public static int dy = -20;
    // 0 is me 1 is far
    public int curP = 0;
    // 0 is not 1 is chosen
    public int isChosen = 0;
    public int cX,cY;

    // 保存
    JButton saveButton = new JButton("保存");

    // 读取
    JButton readButton = new JButton("读取");



    public void setBtn() {
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(saveButton);
        buttonPanel.add(readButton);
        this.add(buttonPanel,BorderLayout.SOUTH);
    }

    public GuiC() throws HeadlessException {
        saveButton.addActionListener(e -> {
            String word = gameService.toJson();
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public String getDescription() {
                    return "TXT(*.txt)";
                }
                @Override
                public boolean accept(File f) {
                    if(f.getName().toLowerCase().endsWith(".txt")) {
                        return true;
                    }
                    return false;
                }
            });
            fileChooser.showOpenDialog(this);
            FileOutputStream fileOutputStream = null;
            File file = fileChooser.getSelectedFile();
            try {
                if(!file.exists()) {
                    file.createNewFile();
                }
                fileOutputStream = new FileOutputStream(file);
                fileOutputStream.write(word.getBytes("gbk"));
                fileOutputStream.flush();
                fileOutputStream.close();
            } catch(Exception w) {
                System.out.println("save wrong");
            }

        });
        readButton.addActionListener(e -> {
            JFileChooser fileChooser = new JFileChooser(".");
            fileChooser.addChoosableFileFilter(new FileFilter() {
                @Override
                public String getDescription() {
                    return "TXT(*.txt)";
                }

                @Override
                public boolean accept(File f) {
                    if (f.getName().toLowerCase().endsWith(".txt")) {
                        return true;
                    }
                    return false;
                }
            });
            fileChooser.showOpenDialog(this);
            File file = fileChooser.getSelectedFile();
            String json = null;
            try {
                InputStreamReader reader = new InputStreamReader(new FileInputStream(file)); // 建立一个输入流对象reader
                BufferedReader br = new BufferedReader(reader); // 建立一个对象，它把文件内容转成计算机能读懂的语言
                json = br.readLine();
            } catch (IOException ex) {
                System.out.println("read wrong");
            }
            gameService.readFromJson(json);

        });

        setBtn();

        this.setTitle("DSQ");
        // 设置窗体大小
        this.setSize(450, 550);
        // 设置窗体大小可改变
        this.setResizable(true);
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
        flashChess();



    }

    public void flashChess() {
        // 画棋子
        Chess[][] chessBoard = GuiC.gameService.game.board.getChessBoard();
        JLayeredPane pane = this.getLayeredPane();

        for (Chess[] i : chessBoard) {
            for (Chess j : i) {
                if (j != null) {
                    int x = 1 + j.getY();
                    int y = 9 - j.getX();
                    int curP = j.getPlayer();
                    System.out.println(j.getX()+"-"+j.getY()+"-"+j.getChessN());
                    picList.add(new ImgLabel(curP, x, y, GuiC.getSrc(j.getChessN(), curP)));
                    // System.out.println(GuiC.getSrc(j.getChessN(), curP));
                }
            }
        }
        for (ImgLabel i : picList) {
            // System.out.println(i.x+" "+i.y);
            pane.add(i.pic);
        }
    }

    @Override
    public void paint(Graphics g) {
        super.paint(g);
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
        // 画出waitingList
        g.setColor(Color.yellow);
        for (int[] i : waitingList) {
            g.fillRect(i[0] * 50+1,i[1] * 50+1,50-1,50-1);
        }
    }

    public static List<int[]> generateStep(int curP, int x,int y) {
        // // convert


        Chess[][] chessBoard = gameService.game.getBoard().getChessBoard();

        Chess chess = chessBoard[x][y];
        List<int[]> result = new ArrayList<>();
        int[] dx = {0,1,0,-1},dy = {1,0,-1,0};
        for (int i = 0; i <= 3; i++) {
            if (x + dx[i] < 0 || x + dx[i] >8 || y + dy[i] < 0 || y + dy[i] > 6)
                continue;
            if (gameService.isValuable(new Step(curP, x, y, x + dx[i], y + dy[i]))) {
                result.add(new int[]{x + dx[i],y + dy[i]});
                System.out.println((x+dx[i])+ "++" + (y+dy[i]));
            }
            if (gameService.game.getBoard().getBoard(x+dx[i],y+dy[i]).equals(BoardN.River)) {
                int tx = x+dx[i],ty = y+dy[i];
                while (gameService.game.getBoard().getBoard(tx,ty).equals(BoardN.River)) {
                    tx += dx[i];
                    ty += dy[i];
                }
                if (gameService.isValuable(new Step(curP, x, y, tx,ty))) {
                    result.add(new int[]{tx, ty});
                    System.out.println(tx+"**"+ty);
                }
            }
        }
        return result;
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        System.out.println("点击按钮");
        if (isChosen == 1) chosen(e);
        else notChosen(e);
        if (gameService.isWin()!=-1) {
            JOptionPane.showConfirmDialog(null, gameService.isWin()+"玩家胜利！");
        }
        this.repaint();
    }
    public void chosen(MouseEvent e) {
        int x = (e.getX())/50;
        int y = (e.getY())/50;
        int cx = 9 - y;
        int cy = x - 1;
        System.out.println(x + " - " + y);
        for (int[] i : waitingList) {
            System.out.println(i[0] + "+" + i[1]);
            if (i[0] == x && i[1] == y) {
                Step step = new Step(curP,cX,cY,cx,cy);
                gameService.move(step);
                System.out.println("Move successful");
                ImgLabel imgLabel = null;
                int bj = 0;
                int nowX = cY + 1;
                int nowY = 9 - cX;
                for (int j = 0; j <= picList.size() - 1; j++) {
                    imgLabel = picList.get(j);
                    if (imgLabel.x == nowX && imgLabel.y == nowY) {
                        bj = 1;
                        break;
                    }
                }
                if (bj == 1) {
                    System.out.println("move " + x + " " + y);
                    // imgLabel.pic.setBounds(x * 50 + GuiC.dx, y * 50 + GuiC.dy, 30, 30);
                    if (curP == 0) curP = 1;
                    else curP = 0;
                    isChosen = 0;
                    JLayeredPane pane = this.getLayeredPane();
                    pane.removeAll();
                    // for (ImgLabel k : picList) {
                    //     pane.add(k.pic);
                    // }
                    picList.clear();
                    flashChess();
                    repaint();
                    break;
                }
            }
        }
        waitingList.clear();

        isChosen = 0;
        repaint();
    }
    public void notChosen(MouseEvent e) {
        int x = (e.getX())/50;
        int y = (e.getY())/50;
        int cx = 9 - y;
        int cy = x - 1;
        System.out.println(x + " - " + y);
        if (x<1 || x>7 || y<1 || y>9) return;
        Chess[][] chessBoard = gameService.game.getBoard().getChessBoard();
        if (chessBoard[cx][cy] == null) return;
        int curP = chessBoard[cx][cy].getPlayer();
        if (curP != this.curP) return;
        // System.out.println(cx + " " + cy);
        List<int[]> ints = generateStep(curP, cx, cy);
        waitingList.clear();
        for (int[] i : ints) {
            int ty = 9 - i[0];
            int tx = i[1] + 1;
            waitingList.add(new int[]{tx, ty});
            // System.out.println(tx+"=="+ty);
        }
        repaint();
        cX = cx;
        cY = cy;
        isChosen = 1;
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    public static String getSrc(ChessN chessN, int curP) {
        String src = "./images/";
        src += chessN.toString();
        src += curP;
        src += ".png";
        return src;
    }
}
