/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

import java.util.Random;

/**
 * Z字型方塊
 * @author User
 */
public class Square_Z extends AbstractSquare{
    private int direction = 1;
    private int newDir = 0;//Z的朝向方向
    
    public Square_Z(){
        initGrids();
    }
    @Override
    public void changeState(int[][] flags){
        int fixedXLoc = grids[1].getX();
        int fixedYLoc = grids[1].getY();
        switch(state){
            case 0://Z
                xLocs[1] = fixedXLoc;
                yLocs[1] = fixedYLoc;
                xLocs[0] = fixedXLoc - direction * 1;
                yLocs[0] = fixedYLoc;
                xLocs[2] = fixedXLoc;
                yLocs[2] = fixedYLoc + 1;
                xLocs[3] = fixedXLoc + direction * 1;
                yLocs[3] = fixedYLoc + 1;
                isStateChangeAllowed(flags, 2);
                break;
            case 1://直Z
                xLocs[1] = fixedXLoc;
                yLocs[1] = fixedYLoc;
                xLocs[0] = fixedXLoc;
                yLocs[0] = fixedYLoc - 1;
                xLocs[2] = fixedXLoc - direction * 1;
                yLocs[2] = fixedYLoc;
                xLocs[3] = fixedXLoc - direction * 1;
                yLocs[3] = fixedYLoc + 1;
                isStateChangeAllowed(flags, 2);
                break;
            default:
                break;
        }
    }
    @Override
    public void initGrids(){
        newDir = new Random().nextInt(2);
        if(newDir == 1)
            direction = -direction;
        state = new Random().nextInt(2);
        switch(state){
            case 0://Z
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() + direction * 1);
                grids[1].setY(grids[0].getY());
                grids[2].setX(grids[0].getX() + direction * 1);
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX() + direction * 2);
                grids[3].setY(grids[0].getY() + 1);
                break;
            case 1://直Z
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX());
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX() - direction * 1);
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX() - direction * 1);
                grids[3].setY(grids[0].getY() + 2);
                break;
            default:
                break;
        }
    }
}
