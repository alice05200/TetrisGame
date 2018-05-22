/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.record;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.ObjectInputStream;

/**
 *
 * @author User
 */
public class ReadRecord {
    public TetrisGameRecords readRecordsFromFile(File recordFile){
        TetrisGameRecords records = new TetrisGameRecords();
        FileInputStream fileInput = null;
        ObjectInputStream objectInput = null;
        
        if (!recordFile.exists())
            return records;
        try{
            fileInput = new FileInputStream(recordFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        try{
            objectInput = new ObjectInputStream(fileInput);
        }catch (IOException e1){
            e1.printStackTrace();
        }
        Object o = null;
        try{
            o = objectInput.readObject();
        }catch (IOException e){
            e.printStackTrace();
        }catch (ClassNotFoundException e){
            e.printStackTrace();
        }
        try{
            objectInput.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        try{
            fileInput.close();
        }catch (IOException e){
            e.printStackTrace();
        }
        records = (TetrisGameRecords) o;
        //records.sortRecords();
        return records;
    }
}
