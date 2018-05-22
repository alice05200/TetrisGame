/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.model;

import java.util.Random;

/**
 * L型方塊
 * @author User
 */
public class Square_L extends AbstractSquare{
    private int direction = 1;
    private int newDir = 0;//L的朝向方向
    
    public Square_L(){
        initGrids();
    }
    @Override
    public void changeState(int[][] flags){
        switch(state){
            case 0://L
                xLocs[1] = grids[1].getX();
                yLocs[1] = grids[1].getY();
                xLocs[0] = xLocs[1];
                yLocs[0] = yLocs[1] - 1;
                xLocs[2] = xLocs[1];
                yLocs[2] = yLocs[1] + 1;
                xLocs[3] = xLocs[1] + direction * 1;
                yLocs[3] = yLocs[1] + 1;
                isStateChangeAllowed(flags, 4);
                break;
            case 1://|-
                xLocs[1] = grids[1].getX();
                yLocs[1] = grids[1].getY();
                xLocs[0] = xLocs[1] - 1;
                yLocs[0] = yLocs[1];
                xLocs[2] = xLocs[1] + 1;
                yLocs[2] = yLocs[1];
                xLocs[3] = xLocs[1] - 1;
                yLocs[3] = yLocs[1] + direction * 1;
                isStateChangeAllowed(flags, 4);
                break;
            case 2://_|
                xLocs[1] = grids[1].getX();
                yLocs[1] = grids[1].getY();
                xLocs[0] = xLocs[1] - direction * 1;
                yLocs[0] = yLocs[1];
                xLocs[2] = xLocs[1];
                yLocs[2] = yLocs[1] + 1;
                xLocs[3] = xLocs[1];
                yLocs[3] = yLocs[1] + 2;
                isStateChangeAllowed(flags, 4);
                break;
            case 3://-|
                xLocs[1] = grids[1].getX();
                yLocs[1] = grids[1].getY();
                xLocs[0] = xLocs[1];
                yLocs[0] = yLocs[1] - direction * 1;
                xLocs[2] = xLocs[1] - 1;
                yLocs[2] = yLocs[1];
                xLocs[3] = xLocs[1] - 2;
                yLocs[3] = yLocs[1];
                isStateChangeAllowed(flags, 4);
                break;
            default:
                break;
        }
    }

    @Override
    public void initGrids(){
        newDir = new Random().nextInt(2);
        state = new Random().nextInt(4);
        if(newDir == 1)
            direction = -direction;
        switch(state){
            case 0://L
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX());
                grids[1].setY(grids[0].getY() + 1);
                grids[2].setX(grids[0].getX());
                grids[2].setY(grids[0].getY() + 2);
                grids[3].setX(grids[0].getX() + direction * 1);
                grids[3].setY(grids[0].getY() + 2);
                break;
            case 1://|-
                grids[0].setX(8);
                if(direction == 1)
                    grids[0].setY(0);
                else
                    grids[0].setY(1);
                grids[1].setX(grids[0].getX() + 1);
                grids[1].setY(grids[0].getY());
                grids[2].setX(grids[0].getX() + 2);
                grids[2].setY(grids[0].getY());
                grids[3].setX(grids[0].getX());
                grids[3].setY(grids[0].getY() + direction * 1);
                break;
            case 2://-|
                grids[0].setX(9);
                grids[0].setY(0);
                grids[1].setX(grids[0].getX() + direction * 1);
                grids[1].setY(grids[0].getY());
                grids[2].setX(grids[0].getX() + direction * 1);
                grids[2].setY(grids[0].getY() + 1);
                grids[3].setX(grids[0].getX() + direction * 1);
                grids[3].setY(grids[0].getY() + 2);
                break;
            case 3://_|
                grids[0].setX(9);
                if(direction == 1)
                    grids[0].setY(0);
                else
                    grids[0].setY(1);
                grids[1].setX(grids[0].getX());
                grids[1].setY(grids[0].getY() + direction * 1);
                grids[2].setX(grids[0].getX() - 1);
                grids[2].setY(grids[0].getY() + direction * 1);
                grids[3].setX(grids[0].getX() - 2);
                grids[3].setY(grids[0].getY() + direction * 1);
                break;
            default:
                break;
        }
    }
    
}
