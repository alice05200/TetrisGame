/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

import java.util.Random;

/**
 * T字型方塊
 * @author User
 */
public class Square_T extends AbstractSquare{
    public Square_T(){
        initGrids();
    }
    @Override
    public void changeState(int[][] flags){
        switch(state){
            case 0://|-
                xLocs[0] = grids[0].getX();
                yLocs[0] = grids[0].getY();
                xLocs[1] = xLocs[0];
                yLocs[1] = yLocs[0] + 1;
                xLocs[2] = xLocs[0] + 1;
                yLocs[2] = yLocs[0] + 1;
                xLocs[3] = xLocs[0];
                yLocs[3] = yLocs[0] + 2;
                isStateChangeAllowed(flags, 4);
                break;
            case 1://T
                xLocs[0] = grids[0].getX() - 1;
                yLocs[0] = grids[0].getY() + 1;
                xLocs[1] = xLocs[0] + 1;
                yLocs[1] = yLocs[0];
                xLocs[2] = xLocs[0] + 2;
                yLocs[2] = yLocs[0];
                xLocs[3] = xLocs[0] + 1;
                yLocs[3] = yLocs[0] + 1;
                isStateChangeAllowed(flags, 4);
                break;
            case 2://-|
                xLocs[0] = grids[0].getX() + 1;
                yLocs[0] = grids[0].getY() - 1;
                xLocs[1] = xLocs[0] - 1;
                yLocs[1] = yLocs[0] + 1;
                xLocs[2] = xLocs[0];
                yLocs[2] = yLocs[0] + 1;
                xLocs[3] = xLocs[0];
                yLocs[3] = yLocs[0] + 2;
                isStateChangeAllowed(flags, 4);
                break;
            case 3://凸
                xLocs[0] = grids[0].getX();
                yLocs[0] = grids[0].getY();
                xLocs[1] = xLocs[0] - 1;
                yLocs[1] = yLocs[0] + 1;
                xLocs[2] = xLocs[0];
                yLocs[2] = yLocs[0] + 1;
                xLocs[3] = xLocs[0] + 1;
                yLocs[3] = yLocs[0] + 1;
                isStateChangeAllowed(flags, 4);
                break;
            default:
                break;
        }
    }
    @Override
    public void initGrids(){
        state = new Random().nextInt(4);
        switch(state){
            case 0://凸
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() - 1);
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX());
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX() + 1);
                grids[3].setY(grids[0].getY() + 1);
                break;
            case 1://|-
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX());
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX() + 1);
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX());
                grids[3].setY(grids[0].getY() + 2);
                break;
            case 2://T
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() + 1);
                grids[1].setY(grids[0].getY());
                grids[2].setX(grids[0].getX() + 2);
                grids[2].setY(grids[0].getY());
                grids[3].setX(grids[0].getX() + 1);
                grids[3].setY(grids[0].getY() + 1);
                break;
            case 3://-|
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() - 1);
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX());
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX());
                grids[3].setY(grids[0].getY() + 2);
                break;
            default:
                break;
        }
    }
}
