/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

import java.util.Random;

/**
 * 一字型方塊
 * @author User
 */
public class Square_One extends AbstractSquare{
    public Square_One(){
        initGrids();
    }
    @Override
    public void changeState(int[][] flags){
        //以第二個點為基準點做轉換
        int fixedXLoc = grids[1].getX();
        int fixedYLoc = grids[1].getY();
        switch(state){
            case 0://橫向到垂直
                xLocs[1] = fixedXLoc;
                yLocs[1] = fixedYLoc;
                xLocs[0] = fixedXLoc;
                yLocs[0] = fixedYLoc - 1;
                xLocs[2] = fixedXLoc;
                yLocs[2] = fixedYLoc + 1;
                xLocs[3] = fixedXLoc;
                yLocs[3] = fixedYLoc + 2;
                isStateChangeAllowed(flags, 2);
                break;
            case 1://垂直到橫向
                xLocs[1] = fixedXLoc;
                yLocs[1] = fixedYLoc;
                xLocs[0] = fixedXLoc - 1;
                yLocs[0] = fixedYLoc;
                xLocs[2] = fixedXLoc + 1;
                yLocs[2] = fixedYLoc;
                xLocs[3] = fixedXLoc + 2;
                yLocs[3] = fixedYLoc;
                isStateChangeAllowed(flags, 2);
                break;
            default:
                break;
        }
    }
    @Override
    public void initGrids(){
        state = new Random().nextInt(2);
        switch(state){
            case 0://垂直
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX());
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX());
                grids[2].setY(grids[0].getY() + 2);
                grids[3].setX(grids[0].getX());
                grids[3].setY(grids[0].getY() + 3);
                break;
            case 1://橫向
                grids[0].setX(8);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() + 1);
                grids[1].setY(grids[0].getY());
                grids[2].setX(grids[0].getX() + 2);
                grids[2].setY(grids[0].getY());
                grids[3].setX(grids[0].getX() + 3);
                grids[3].setY(grids[0].getY());
                break;
            default:
                break;
        }
    }
}
