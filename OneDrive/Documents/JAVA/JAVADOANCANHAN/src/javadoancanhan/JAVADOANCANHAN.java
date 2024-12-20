package javadoancanhan;

import javax.swing.*;

public class JAVADOANCANHAN {
    public static void main(String[] args) {
        try {
            // Thiết lập giao diện theo giao diện hệ điều hành
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            e.printStackTrace();
        }
        
        // Tạo và hiển thị giao diện trong một luồng riêng
        SwingUtilities.invokeLater(() -> {
            QuanLyFrame mainFrame = new QuanLyFrame();
            mainFrame.setVisible(true); // Hiển thị cửa sổ chính
        });
    }
}
