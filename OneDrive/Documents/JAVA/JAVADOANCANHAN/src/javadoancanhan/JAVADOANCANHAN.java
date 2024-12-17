package javadoancanhan;

import javax.swing.*;

public class JAVADOANCANHAN {
    public static void main(String[] args) {
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        SwingUtilities.invokeLater(() -> {
            QuanLyFrame mainFrame = new QuanLyFrame();
            mainFrame.setVisible(true);
        });
    }
}
