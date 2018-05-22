/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tetrisgame.ui;

import java.awt.Container;
import java.awt.FileDialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.File;
import javax.swing.ButtonGroup;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JScrollPane;
import tetrisgame.constants.TetrisGameConstants;
import tetrisgame.enums.GameState;
import tetrisgame.record.ReadRecord;
import tetrisgame.record.TetrisGameRecords;

/**
 *
 * @author User
 */
public class TetrisGameFrame extends JFrame{
    private final int WIDTH = TetrisGameConstants.TETRIS_GAME_FRAME_WIDTH;
    private final int HEIGHT = TetrisGameConstants.TETRIS_GAME_FRAME_HEIGHT;
    private TetrisGamePanel panel;
    private Container contentPane;
    private JMenuItem startMI = new JMenuItem("開始");
    private JMenuItem restartMI = new JMenuItem("重新開始");
    private JMenuItem pauseMI = new JMenuItem("暫停");
    private JMenuItem recordMI = new JMenuItem("排行榜");
    private JMenu speedMenu = new JMenu("速度");
    private JMenuItem exitMI = new JMenuItem("離開");
    private JMenuItem aboutMI = new JMenuItem("關於");
    private JMenuItem loadMI = new JMenuItem("載入遊戲");
    public JMenuItem saveMI = new JMenuItem("儲存遊戲");
    private JRadioButtonMenuItem speedMI1 = new JRadioButtonMenuItem("速度1", true);
    private JRadioButtonMenuItem speedMI2 = new JRadioButtonMenuItem("速度2", false);
    private JRadioButtonMenuItem speedMI3 = new JRadioButtonMenuItem("速度3", false);
    private JRadioButtonMenuItem speedMI4 = new JRadioButtonMenuItem("速度4", false);
    private JRadioButtonMenuItem speedMI5 = new JRadioButtonMenuItem("速度5", false);
    public int speedFlag = 1;
    
    public TetrisGameFrame(){
        setUpUIComponent();
        setUpEventListener();
        panel.setGameState(GameState.INITIALIZE);
    }
    private void setUpUIComponent(){
        setTitle("俄羅斯方塊Tetris");
        setSize(WIDTH, HEIGHT);
        setResizable(false);
        JMenuBar menuBar = new JMenuBar();
        setJMenuBar(menuBar);
        JMenu setMenu = new JMenu("設置");
        JMenu helpMenu = new JMenu("Help");
        setMenu.setMnemonic('s');
        setMenu.setMnemonic('H');
        menuBar.add(setMenu);
        menuBar.add(helpMenu);
        setMenu.add(startMI);
        setMenu.add(pauseMI);
        setMenu.add(restartMI);
        setMenu.addSeparator();
        setMenu.add(loadMI);
        setMenu.add(saveMI);
        setMenu.add(recordMI);
        setMenu.addSeparator();
        setMenu.add(speedMenu);
        setMenu.addSeparator();
        setMenu.add(exitMI);
        ButtonGroup group = new ButtonGroup();
        group.add(speedMI1);
        group.add(speedMI2);
        group.add(speedMI3);
        group.add(speedMI4);
        group.add(speedMI5);
        speedMenu.add(speedMI1);
        speedMenu.add(speedMI2);
        speedMenu.add(speedMI3);
        speedMenu.add(speedMI4);
        speedMenu.add(speedMI5);
        helpMenu.add(aboutMI);
        contentPane = getContentPane();
        panel = new TetrisGamePanel(this);
        contentPane.add(panel);
        startMI.setEnabled(true);
        pauseMI.setEnabled(false);
        restartMI.setEnabled(false);
        saveMI.setEnabled(false);
    }
    private void setUpEventListener(){
        addWindowListener(
            new WindowAdapter() {
                public void windowClosing(WindowEvent e) { 
                    int result = JOptionPane.showConfirmDialog(TetrisGameFrame.this,
                    "確定離開？", "俄羅斯方塊Tetris", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE, null);
                    if(result == JOptionPane.YES_OPTION){
                        dispose();
                        System.exit(0);
                    }
                }
            }
        );
        startMI.addActionListener(new StartAction());
        pauseMI.addActionListener(new PauseAction());
        restartMI.addActionListener(new RestartAction());
        loadMI.addActionListener(new LoadAction());
        saveMI.addActionListener(new SaveAction());
        recordMI.addActionListener(new RecordAction());
        exitMI.addActionListener(new ExitAction());
        speedMI1.addActionListener(new SpeedAction());
        speedMI2.addActionListener(new SpeedAction());
        speedMI3.addActionListener(new SpeedAction());
        speedMI4.addActionListener(new SpeedAction());
        speedMI5.addActionListener(new SpeedAction());
        aboutMI.addActionListener(new AboutAction());
    }
    /**
     * 遊戲開始
     */
    private class StartAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            startFunc();
        }
    }
    private void startFunc(){
        startMI.setEnabled(false);
        pauseMI.setEnabled(true);
        restartMI.setEnabled(true);
        saveMI.setEnabled(true);
        panel.setGameState(GameState.RUN);
        panel.timer.start();
    }
    /**
     * 遊戲暫停
     */
    private class PauseAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            pauseFunc();
        }
    }
    private void pauseFunc(){
        pauseMI.setEnabled(false);
        startMI.setEnabled(true);
        //restartMI.setEnabled(false);
        panel.setGameState(GameState.PAUSE);
        if(panel.timer.isRunning())
            panel.timer.stop();
    }
    private class RestartAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            startMI.setEnabled(false);
            pauseMI.setEnabled(true);
            panel.setGameState(GameState.RESTART);
            if(!panel.timer.isRunning())
                panel.timer.start();
        }
    }
    /**
     * 讀取遊戲進度
     */
    private class LoadAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            FileDialog dialog = new FileDialog(TetrisGameFrame.this, "Open", FileDialog.LOAD);
            dialog.setVisible(true);
            String dir = dialog.getDirectory();
            String fileName = dialog.getFile();
            String filePath = dir + fileName;
            if(fileName != null && fileName.trim().length() != 0){
                File file = new File(filePath);
                panel.readSelfFromFile(file);
                startMI.setEnabled(false);
                pauseMI.setEnabled(true);
            }else
                JOptionPane.showConfirmDialog(TetrisGameFrame.this,
                        "文件名稱為空\n加載遊戲進度失敗", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
        }
    }
    /**
     * 儲存遊戲進度
     */
    private class SaveAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(panel.gameState == GameState.INITIALIZE){
                JOptionPane.showConfirmDialog(TetrisGameFrame.this,
                        "遊戲尚未進行\n無法保存遊戲進度", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
                return;
            }
            FileDialog dialog = new FileDialog(TetrisGameFrame.this, "Save", FileDialog.SAVE);
            dialog.setVisible(true);
            String dir = dialog.getDirectory();
            String fileName = dialog.getFile();
            String filePath = dir + fileName;
            if(fileName != null && fileName.trim().length() != 0){
                File file = new File(filePath);
                panel.writeSelfToFile(file);
            }else
                JOptionPane.showConfirmDialog(TetrisGameFrame.this,
                        "文件名稱為空\n保存遊戲進度失敗", "俄羅斯方塊Tetris", JOptionPane.DEFAULT_OPTION);
        }
    }
    /**
     * 排行榜
     */
    private class RecordAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(panel.gameState.isRunState())
                pauseFunc();
            File file = new File("file.dat");
            TetrisGameRecords records = new ReadRecord().readRecordsFromFile(file);
            records.sortRecords();
            JScrollPane sPanel = new ReadScrollPane().getReadScrollPane(records, file);
            JDialog recordDialog = new JDialog(TetrisGameFrame.this, "排行榜");
            recordDialog.setBounds(300, 300, 300, 219);
            Container container = recordDialog.getContentPane();
            container.add(sPanel);
            recordDialog.show();
            recordDialog.addWindowListener(new WindowAdapter(){
                public void windowClosing(WindowEvent e){
                    if(panel.gameState.isPausedState())
                        startFunc();
                }
            });
        }
    }
    /**
     * 速度調整
     */
    private class SpeedAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            Object speed = event.getSource();
            if(speed == speedMI1)
                speedFlag = 1;
            else if(speed == speedMI2)
                speedFlag = 2;
            else if(speed == speedMI3)
                speedFlag = 3;
            else if(speed == speedMI4)
                speedFlag = 4;
            else if(speed == speedMI5)
                speedFlag = 5;
            panel.timer.setDelay(1000 - 200 * (speedFlag - 1));
        }
    }
    /**
     * 離開動作
     */
    private class ExitAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            int result = JOptionPane.showConfirmDialog(TetrisGameFrame.this,
                    "確定離開？", "俄羅斯方塊Tetris", JOptionPane.YES_NO_OPTION);
            if(result == JOptionPane.YES_OPTION){
                dispose();
                System.exit(0);
            }
        }
    }
    /**
     * 顯示遊戲說明
     */
    private class AboutAction implements ActionListener{
        public void actionPerformed(ActionEvent event){
            if(panel.gameState.isRunState())
                pauseFunc();
            String string = "說明:\n1.左鍵向左移動\n" + "2.右键向右移動\n" + "3.向上鍵旋轉\n"
                    + "4.向下鍵加速下降\n" + "5.按空格鍵下降到最底部";
            JOptionPane.showMessageDialog(TetrisGameFrame.this, string);
            if(panel.gameState.isPausedState())
                startFunc();
        }
    }
}
