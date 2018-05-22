/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

import java.awt.Color;
import java.awt.Graphics2D;
import java.io.Serializable;
import tetrisgame.constants.TetrisGameConstants;

/**
 * 俄羅斯方塊抽象類別
 * @author User
 */
public abstract class AbstractSquare implements Serializable{
    protected Grid[] grids = {null, null, null, null};//一個俄羅斯方塊由四個格子組成
    protected int[] xLocs = {0, 0, 0, 0};
    protected int[] yLocs = {0, 0, 0, 0};
    protected boolean isAlive;//圖形是否還能移動變換
    protected Color color;//圖形格子顏色
    public int state;//圖形轉換的狀態
    
    public AbstractSquare(){
        int r = (int)(Math.random() * 256);
        int g = (int)(Math.random() * 256);
        int b = (int)(Math.random() * 256);
        this.color = new Color(r, g, b);
        for(int i = 0; i < 4; i++)
            grids[i] = new Grid(0, 0, color);
        isAlive = true;
        initGrids();
    }
    /**
     * 
     * @param g2 
     */
    public void draw(Graphics2D g2){
        for(Grid grid : grids)
            grid.draw(g2);
    }
    /**
     * 向左移動
     * @param flags 
     */
    public void moveLeft(int[][] flags){
        if(!isAlive)
            return;
        for(int i = 0; i < grids.length; i++){//向左移動=X座標減一
            xLocs[i] = grids[i].getX() - 1;
            yLocs[i] = grids[i].getY();
        }
        //檢查是否超過遊戲畫面最左邊及是否有方塊，都沒有才賦值
        if(xLocs[0] >= TetrisGameConstants.LEFT && flags[xLocs[0]][yLocs[0]] == 0
                && xLocs[1] >= TetrisGameConstants.LEFT && flags[xLocs[1]][yLocs[1]] == 0
                && xLocs[2] >= TetrisGameConstants.LEFT && flags[xLocs[2]][yLocs[2]] == 0
                && xLocs[3] >= TetrisGameConstants.LEFT && flags[xLocs[3]][yLocs[3]] == 0){
            for (int i = 0; i < grids.length; i++)
                grids[i].setX(xLocs[i]);
        }
    }
    /**
     * 向右移動
     * @param flags 
     */
    public void moveRight(int[][] flags){
        if(!isAlive)
            return;
        for(int i = 0; i < grids.length; i++){//向右移動=X座標加一
            xLocs[i] = grids[i].getX() + 1;
            yLocs[i] = grids[i].getY();
        }
        if(xLocs[0] <= TetrisGameConstants.RIGHT && flags[xLocs[0]][yLocs[0]] == 0
                && xLocs[1] <= TetrisGameConstants.RIGHT && flags[xLocs[1]][yLocs[1]] == 0
                && xLocs[2] <= TetrisGameConstants.RIGHT && flags[xLocs[2]][yLocs[2]] == 0
                && xLocs[3] <= TetrisGameConstants.RIGHT && flags[xLocs[3]][yLocs[3]] == 0){
            for (int i = 0; i < grids.length; i++)
                grids[i].setX(xLocs[i]);
        }
    }
    public void moveDown(int[][] flags){
        for(int i = 0; i < grids.length; i++){
            xLocs[i] = grids[i].getX();
            yLocs[i] = grids[i].getY() + 1;
        }
        if(yLocs[0] <= TetrisGameConstants.DOWN && flags[xLocs[0]][yLocs[0]] == 0
                && yLocs[1] <= TetrisGameConstants.DOWN && flags[xLocs[1]][yLocs[1]] == 0
                && yLocs[2] <= TetrisGameConstants.DOWN && flags[xLocs[2]][yLocs[2]] == 0
                && yLocs[3] <= TetrisGameConstants.DOWN && flags[xLocs[3]][yLocs[3]] == 0){
            for (int i = 0; i < grids.length; i++)
                grids[i].setY(yLocs[i]);
        }else//撞到方塊或底部停止移動
            isAlive = false;
    }
     public void drawNext(Graphics2D g2){
        for (int i = 0; i < grids.length; i++){//取得每個格子座標
            xLocs[i] = grids[i].getX();
            yLocs[i] = grids[i].getY();
        }
        while(true){
            for(int i = 0; i < 4; i++)
                xLocs[i]++;
            if (xLocs[0] >= TetrisGameConstants.LITTLEX && xLocs[1] >= TetrisGameConstants.LITTLEX
                && xLocs[2] >= TetrisGameConstants.LITTLEX && xLocs[3] >= TetrisGameConstants.LITTLEX)
                break;
        }
        for(int i = 0; i < grids.length; i++)
            new Grid(xLocs[i], yLocs[i], color).draw(g2);
    }
    protected void isStateChangeAllowed(int[][] flags, int count){
        if(!isAlive)
            return;
        if(xLocs[0] >= TetrisGameConstants.LEFT && xLocs[0] <= TetrisGameConstants.RIGHT
                && yLocs[0] >= TetrisGameConstants.UP && yLocs[0] <= TetrisGameConstants.DOWN
                && flags[xLocs[0]][yLocs[0]] == 0
                && xLocs[1] >= TetrisGameConstants.LEFT && xLocs[1] <= TetrisGameConstants.RIGHT
                && yLocs[1] >= TetrisGameConstants.UP && yLocs[1] <= TetrisGameConstants.DOWN
                && flags[xLocs[1]][yLocs[1]] == 0
                && xLocs[2] >= TetrisGameConstants.LEFT  && xLocs[2] <= TetrisGameConstants.RIGHT
                && yLocs[2] >= TetrisGameConstants.UP && yLocs[2] <= TetrisGameConstants.DOWN
                && flags[xLocs[2]][yLocs[2]] == 0
                && xLocs[3] >= TetrisGameConstants.LEFT && xLocs[3] <= TetrisGameConstants.RIGHT
                && yLocs[3] >= TetrisGameConstants.UP && yLocs[3] <= TetrisGameConstants.DOWN
                && flags[xLocs[3]][yLocs[3]] == 0){
            for(int i = 0; i < grids.length; i++){
                grids[i].setX(xLocs[i]);
                grids[i].setY(yLocs[i]);
            }
            state = (state + 1) % count;//count為圖形可變化的種類數
        }
    }
    /**
     * 每種圖形變化不同，所以使用抽象方法
     * @param flags 
     */
    public abstract void changeState(int[][] flags);
    public abstract void initGrids();
    public boolean isAliveOrNot(){
        return isAlive;
    }
    public void setIsAlive(boolean isAlive){
        this.isAlive = isAlive;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
    public Grid[] getGrids(){
        return grids;
    }
    public void setGrids(Grid[] grids){
        this.grids = grids;
    }
}
