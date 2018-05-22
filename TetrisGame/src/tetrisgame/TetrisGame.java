/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame;

import javax.swing.JFrame;
import tetrisgame.ui.TetrisGameFrame;

/**
 *
 * @author User
 */
public class TetrisGame {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        TetrisGameFrame frame = new TetrisGameFrame();
	frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
	frame.show();
    }
}
