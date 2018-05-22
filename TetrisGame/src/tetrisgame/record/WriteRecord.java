/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.record;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

/**
 *
 * @author User
 */
public class WriteRecord {
    public void writeRecordToFile(TetrisGameRecords records, File recordFile){
        FileOutputStream fileOutput = null;
        try{
            fileOutput = new FileOutputStream(recordFile);
        }catch (FileNotFoundException e){
            e.printStackTrace();
        }
        ObjectOutputStream objectOutput = null;
        try{
            objectOutput = new ObjectOutputStream(fileOutput);
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            objectOutput.writeObject(records);
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            objectOutput.close();
        } catch (IOException e){
            e.printStackTrace();
        }
        try{
            fileOutput.close();
        } catch (IOException e){
            e.printStackTrace();
        }
    }
}
