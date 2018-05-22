/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.record;

import java.io.Serializable;

/**
 * 遊戲分數紀錄
 * @author User
 */
public class Record implements Serializable{
    private String player = null;
    private int score = 0;

    public Record(String player, int score){
        this.player = player;
        this.score = score;
    }
    public String getPlayer(){
        return player;
    }
    public void setPlayer(String player){
        this.player = player;
    }
    public int getScore(){
        return score;
    }
    public void setScore(int score){
        this.score = score;
    }
}
