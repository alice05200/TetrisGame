/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.record;

import java.util.Comparator;

/**
 * 遊戲紀錄分數比較器
 * @author User
 */
public class RecordComparator implements Comparator<Record>{
    public int compare(Record o1, Record o2){
        Record r1 = (Record) o1;
        Record r2 = (Record) o2;
        return (compareScore(r1, r2) == 0) ? compareName(r1, r2) : compareScore(r1, r2);
    }
    private int compareScore(Record r1, Record r2){
        return r2.getScore() - r1.getScore();
    }
    private int compareName(Record r1, Record r2){
        return r1.getPlayer().compareTo(r2.getPlayer());
    }
}
