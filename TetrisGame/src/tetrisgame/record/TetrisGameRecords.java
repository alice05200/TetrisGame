/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.record;

import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;

/**
 *
 * @author User
 */
public class TetrisGameRecords implements Serializable{
    private static final int TOP_TEN = 10;
    private Record[] records = null;
    private int numberInRecord = 0;

    public TetrisGameRecords(){
        records = new Record[TOP_TEN];
    }
    public void sortRecords(){
        Record[] availableRecords = getAvailableRecords();
        Collections.sort(Arrays.asList(availableRecords), new RecordComparator());
        for(int i = 0; i < numberInRecord; i++)
            records[i] = availableRecords[i];
    }
    private Record[] getAvailableRecords(){
        Record[] availableRecords = new Record[numberInRecord];
        for(int i = 0; i < numberInRecord; i++)
            availableRecords[i] = new Record(records[i].getPlayer(), records[i].getScore());
        return availableRecords;
    }
    /**
     * 取得最後一筆遊戲紀錄
     * @return 
     */
    public Record getLastAvailableRecord(){
        return isEmpty() ? null : records[numberInRecord - 1];
    }
    /**
     * 添加遊戲紀錄分數
     * @param record 
     */
    public void addRecordToTopTen(Record record){
        if(isEmpty()){
            records[0] = record;
            numberInRecord++;
            return;
        }
        if(isFull()){
            if(records[TOP_TEN - 1].getScore() < record.getScore()){
                records[TOP_TEN - 1] = record;
                sortRecords();
                return;
            }
        }
        records[numberInRecord] = record;
        numberInRecord++;
        sortRecords();
    }
    public boolean isEmpty(){
        return numberInRecord == 0;
    }
    public boolean isFull(){
        return numberInRecord == TOP_TEN;
    }
    public int getNumberInRecord(){
        return numberInRecord;
    }
    public void setNumberInRecord(int numberInRecord){
        this.numberInRecord = numberInRecord;
    }
    public Record[] getRecords(){
        return records;
    }
    public void setRecords(Record[] records){
        this.records = records;
    }
}
