/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;
import java.io.Serializable;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.geom.Rectangle2D;
import tetrisgame.constants.TetrisGameConstants;
/**
 * 組成俄羅斯方塊的格子
 * @author User
 */
public class Grid implements Serializable{
    private int x, y;//座標
    private Color color;//顏色
    
    public Grid(){}
    public Grid(int x, int y, Color color){
        this.x = x;
        this.y = y;
        this.color = color;
    }
    public void draw(Graphics2D g2){
        int clientX = TetrisGameConstants.TETRIS_GAME_PANEL_LEFT + x * TetrisGameConstants.GRID_SIZE;
        int clientY = TetrisGameConstants.TETRIS_GAME_PANEL_TOP + y * TetrisGameConstants.GRID_SIZE;
        Rectangle2D.Double rect = new Rectangle2D.Double(clientX, clientY, TetrisGameConstants.GRID_SIZE, TetrisGameConstants.GRID_SIZE);
        g2.setPaint(color);
        g2.fill(rect);
        g2.setPaint(Color.BLACK);
        g2.draw(rect);
    }
    public int getX(){
        return x;
    }
    public void setX(int x){
        this.x = x;
    }
    public int getY(){
        return y;
    }
    public void setY(int y){
        this.y = y;
    }
    public Color getColor(){
        return color;
    }
    public void setColor(Color color){
        this.color = color;
    }
}
