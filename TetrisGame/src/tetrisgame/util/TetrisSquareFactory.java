/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.util;

import java.util.Random;
import tetrisgame.model.AbstractSquare;
import tetrisgame.model.Square_L;
import tetrisgame.model.Square_One;
import tetrisgame.model.Square_T;
import tetrisgame.model.Square_Tian;
import tetrisgame.model.Square_Z;

/**
 * 產生隨機形狀方塊工廠
 * @author User
 */
public class TetrisSquareFactory {
    private static final int TOTAL_TETRIS_SQUARE_COUNT = 5;//總方塊種類數
    
    public static AbstractSquare generateNextTetrisSquareByRandom(){
        AbstractSquare rs = null;
        int index = new Random().nextInt(TOTAL_TETRIS_SQUARE_COUNT);
        switch(index){
            case 0:
                rs = new Square_One();
                break;
            case 1:
                rs = new Square_T();
                break;
            case 2:
                rs = new Square_Tian();
                break;
            case 3:
                rs = new Square_L();
                break;
            case 4:
                rs = new Square_Z();
                break;
            default:
                rs = new Square_One();
                break;
        }
        System.out.println("Square:" + rs.toString());
        return rs;
    }
}
