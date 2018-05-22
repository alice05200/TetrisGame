/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

/**
 * 田字型方塊
 * @author User
 */
public class Square_Tian extends AbstractSquare{
    public Square_Tian(){
        initGrids();
    }
    /**
     * 田只有一種型態
     * @param flags 
     */
    @Override
    public void changeState(int[][] flags){
    }
    @Override
    public void initGrids(){
        grids[0].setX(9);
        grids[0].setY(0);
        grids[1].setX(grids[0].getX() + 1);
        grids[1].setY(0);
        grids[2].setX(grids[0].getX());
        grids[2].setY(grids[0].getY() + 1);
        grids[3].setX(grids[0].getX() + 1);
        grids[3].setY(grids[0].getY() + 1);
    }
}
