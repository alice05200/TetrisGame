/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.ui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;
import tetrisgame.constants.TetrisGameConstants;
import tetrisgame.enums.GameState;
import tetrisgame.model.AbstractSquare;
import tetrisgame.model.Grid;
import tetrisgame.record.ReadRecord;
import tetrisgame.record.Record;
import tetrisgame.record.TetrisGameRecords;
import tetrisgame.record.WriteRecord;
import tetrisgame.util.TetrisSquareFactory;

/**
 *
 * @author User
 */
public class TetrisGamePanel extends JPanel{
    public int[][] flag = new int[TetrisGameConstants.GRID_COLUMN_NUMBER][TetrisGameConstants.GRID_ROW_NUMBER];//在一個10*20的界面中，設置每個方塊的flag
    public Color[][] color = new Color[TetrisGameConstants.GRID_COLUMN_NUMBER][TetrisGameConstants.GRID_ROW_NUMBER];//在一個10*20的界面中，設置每個方塊的顏色
    public AbstractSquare sr1; //遊戲畫面中的圖形
    public AbstractSquare sr2; //下一個出現的圖形
    public Timer timer;
    public TimerAction timerAction;
    public int score;
    public TetrisGameFrame frame;
    public Grid square;
    public GameState gameState = GameState.INITIALIZE;
    
    public TetrisGamePanel(TetrisGameFrame frame){
        for (int i = TetrisGameConstants.LEFT; i <= TetrisGameConstants.RIGHT; i++){
            for (int j = TetrisGameConstants.UP; j <= TetrisGameConstants.DOWN; j++)
                flag[i][j] = 0;
        }
        addKeyListener(new KeyHandler());
        setFocusable(true);
        timerAction = new TimerAction();
        timer = new Timer(1000, timerAction);
        sr1 = TetrisSquareFactory.generateNextTetrisSquareByRandom();
        sr2 = TetrisSquareFactory.generateNextTetrisSquareByRandom();
        score = 0;
        this.frame = frame;
        square = new Grid();
    }
    public void drawGameFrame(Graphics2D g2){
        Rectangle2D.Double leftFrame = new Rectangle2D.Double(
                TetrisGameConstants.TETRIS_GAME_PANEL_LEFT, TetrisGameConstants.TETRIS_GAME_PANEL_TOP, 400, 600);
        Rectangle2D.Double rightFrame = new Rectangle2D.Double(
                TetrisGameConstants.TETRIS_GAME_NEXT_PANEL_LEFT, TetrisGameConstants.TETRIS_GAME_NEXT_PANEL_TOP, 80, 80);
        g2.draw(leftFrame);
        g2.draw(rightFrame);
    }
    public void paintComponent(Graphics g){
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        drawGameFrame(g2);
        if (gameState.isInitializedState())
            return;
        sr1.draw(g2);
        sr2.drawNext(g2);
        for(int i = TetrisGameConstants.LEFT; i <= TetrisGameConstants.RIGHT; i++){
            for(int j = TetrisGameConstants.UP; j <= TetrisGameConstants.DOWN; j++){
                if(flag[i][j] == 1){
                    square.setX(i);
                    square.setY(j);
                    square.setColor(color[i][j]);
                    square.draw(g2);
                }
            }
        }
        g.drawString("Score: " + score, TetrisGameConstants.TETRIS_GAME_NEXT_PANEL_LEFT, 200);
    }
    /**
     * 將遊戲進度寫入檔案
     * @param file 
     */
    public void writeSelfToFile(File file){
        try{
            FileOutputStream fileStream = new FileOutputStream(file);
            ObjectOutputStream objectStream = new ObjectOutputStream(fileStream);
            objectStream.writeObject(flag);
            objectStream.writeObject(color);
            objectStream.writeObject(sr1);
            objectStream.writeObject(sr2);
            objectStream.writeObject(new Integer(score));
            objectStream.close();
            fileStream.close();
            JOptionPane.showConfirmDialog(frame, "保存遊戲進度成功", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
        }catch (Exception e){
            JOptionPane.showConfirmDialog(frame, e.toString() + "\n保存遊戲進度失敗", "俄羅斯方塊Tetris",
                    JOptionPane.DEFAULT_OPTION);
        }
    }
    /**
     * 讀取遊戲進度檔案
     * @param file 
     */
    public void readSelfFromFile(File file){
        try{
            int[][] f;
            AbstractSquare s1, s2;
            Integer integer;
            Color[][] c;
            FileInputStream fileStream = new FileInputStream(file);
            ObjectInputStream objectStream = new ObjectInputStream(fileStream);
            f = (int[][]) objectStream.readObject();
            c = (Color[][]) objectStream.readObject();
            s1 = (AbstractSquare) objectStream.readObject();
            s2 = (AbstractSquare) objectStream.readObject();
            integer = (Integer) objectStream.readObject();
            objectStream.close();
            fileStream.close();
            if(f != null && c != null && s1 != null && s2 != null && integer != null){
                flag = f;
                color = c;
                sr1 = s1;
                sr2 = s2;
                score = integer.intValue();
                gameState = GameState.RUN;
                frame.saveMI.setEnabled(true);
                if(!timer.isRunning())
                    timer.start();
                repaint();
                JOptionPane.showConfirmDialog(frame, "加載遊戲進度成功", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
            }
        }catch (Exception e){
            JOptionPane.showConfirmDialog(frame, e.toString() + "\n加載遊戲進度失敗", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
        }
    }
    public void setGameState(GameState state){
        gameState = state;
    }
    /**
     * 加入遊戲排行榜
     */
    private void writeScore(){
        //if(score == 0)
        //    return;
        File file = new File("file.dat");
        TetrisGameRecords records = new ReadRecord().readRecordsFromFile(file);
        if(records == null || records.isEmpty() || !records.isFull()
                || (records.getLastAvailableRecord().getScore() < score && records.isFull())){
            String playerName = JOptionPane.showInputDialog("請輸入你的名字");
            if(playerName == null || playerName.length() == 0)//若玩家沒有輸入名字
                playerName = "無名氏";
            Record record = new Record(playerName, score);
            records.addRecordToTopTen(record);
            new WriteRecord().writeRecordToFile(records, file);
        }
    }
    /**
     * 檢查方塊是否碰到遊戲畫面頂端 -> 判斷是否遊戲結束
     * @return 
     */
    private boolean isTopTouched(){
        for(int i = TetrisGameConstants.LEFT; i <= TetrisGameConstants.RIGHT; i++){
            if(flag[i][TetrisGameConstants.UP] == 1)
                return true;
        }
        return false;
    }
    /**
     * 若isTopTouched()為真，則遊戲結束，跳出提示訊息
     */
    private void judgeGameOver(){
        if(isTopTouched()){
            gameState = GameState.OVER;
            writeScore();
            int result = JOptionPane.showConfirmDialog(frame, "遊戲結束，是否繼續？", "俄羅斯方塊Tetris", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION)
                restartGame();
            else
                System.exit(0);
        }
    }
    private void restartGame(){
        //重設方塊
        for(int i = TetrisGameConstants.LEFT; i <= TetrisGameConstants.RIGHT; i++){
            for (int j = TetrisGameConstants.UP; j <= TetrisGameConstants.DOWN; j++)
                flag[i][j] = 0;
        }
        gameState = GameState.RUN;
        score = 0;
        timer.start();
        sr1 = TetrisSquareFactory.generateNextTetrisSquareByRandom();
        sr2 = TetrisSquareFactory.generateNextTetrisSquareByRandom();
    }
    private class KeyHandler implements KeyListener{
        public void keyPressed(KeyEvent event){
            if(!gameState.isRunState())
                return;
            int keyCode = event.getKeyCode();
            switch(keyCode){
                case KeyEvent.VK_LEFT:
                    sr1.moveLeft(flag);
                    break;
                case KeyEvent.VK_RIGHT:
                    sr1.moveRight(flag);
                    break;
                case KeyEvent.VK_UP:
                    sr1.changeState(flag);
                    break;
                case KeyEvent.VK_DOWN:
                    sr1.moveDown(flag);
                    break;
                case KeyEvent.VK_SPACE://空白鍵一鍵到底
                    while (sr1.isAliveOrNot())
                        sr1.moveDown(flag);
                    break;
                default:
                    break;
            }
            repaint();
        }
        public void keyReleased(KeyEvent event){
        }
        public void keyTyped(KeyEvent event){
        }
    }
    private class TimerAction implements ActionListener, Serializable{
        public void actionPerformed(ActionEvent event){
            if(gameState.isRestartState()){
                restartGame();
                System.out.println("Restart");
            }
            if(!gameState.isRunState())
                return;
            int num = 0;//滿列的數量
            sr1.moveDown(flag);
            if(!sr1.isAliveOrNot()){
                for(int i = 0; i < 4; i++){
                    Grid[] grids = sr1.getGrids();
                    flag[grids[i].getX()][grids[i].getY()] = 1;
                    color[grids[i].getX()][grids[i].getY()] = sr1.getColor();
                }
                judgeGameOver();
                for(int i = TetrisGameConstants.UP; i <= TetrisGameConstants.DOWN; i++){
                    int count = 0;
                    //計算一列目前有幾個方塊
                    for (int j = TetrisGameConstants.LEFT; j <= TetrisGameConstants.RIGHT; j++)
                        count += flag[j][i];
                    //方塊數=行數=滿列 -> 消除
                    if(count == TetrisGameConstants.GRID_COLUMN_NUMBER){
                        num++;//滿列數加一
                        //將上方方塊往下移
                        for(int m = i; m > TetrisGameConstants.UP; m--){
                            for(int n = TetrisGameConstants.LEFT; n <= TetrisGameConstants.RIGHT; n++){
                                flag[n][m] = flag[n][m - 1];
                                color[n][m] = color[n][m - 1];
                            }
                        }
                        //將第一列的格子消除
                        for(int s = TetrisGameConstants.LEFT; s <= TetrisGameConstants.RIGHT; s++)
                            flag[s][TetrisGameConstants.UP] = 0;
                    }
                }
                sr1 = sr2;
                sr2 = TetrisSquareFactory.generateNextTetrisSquareByRandom();
            }
            calculateScore(num);//加分
            repaint();
        }
    }
    private void calculateScore(int num){
        switch(num){
            case 1:
                score += 10;
                break;
            case 2:
                score += 20;
                break;
            case 3:
                score += 50;
                break;
            case 4:
                score += 100;
                break;
            default:
                break;
        }
    }
}
