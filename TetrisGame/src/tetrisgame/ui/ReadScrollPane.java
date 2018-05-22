/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.ui;

import java.io.File;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import tetrisgame.record.Record;
import tetrisgame.record.TetrisGameRecords;

/**
 * 遊戲排行榜顯示視窗
 * @author User
 */
public class ReadScrollPane{
    public JScrollPane getReadScrollPane(TetrisGameRecords records, File recordFile){
        Object[][] data = new Object[records.getNumberInRecord()][3];
        for(int i = 0; i < records.getNumberInRecord(); i++){
            Record record = records.getRecords()[i];
            data[i][0] = String.valueOf(i + 1);
            data[i][1] = record.getPlayer();
            data[i][2] = String.valueOf(record.getScore());
        }
        Object[] columnNames = new Object[3];
        columnNames[0] = "排名";
        columnNames[1] = "名字";
        columnNames[2] = "分數";
        JTable table = new JTable(data, columnNames);
        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        JScrollPane pane = new JScrollPane(table);
        return pane;
    }
}
