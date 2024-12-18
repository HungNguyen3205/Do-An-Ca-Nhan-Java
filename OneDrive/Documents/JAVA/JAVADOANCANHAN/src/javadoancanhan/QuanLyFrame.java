package javadoancanhan;

import javax.swing.*;

public class QuanLyFrame extends JFrame {
    private final NhanVienPanel nhanVienPanel;
    
    public QuanLyFrame() {
        setTitle("Hệ Thống Quản Lý Sinh Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        nhanVienPanel = new NhanVienPanel();
        add(nhanVienPanel);
        
        // Load dữ liệu khi khởi động
        nhanVienPanel.loadData();
    }
} 