package javadoancanhan;

import javax.swing.*;

public class QuanLyFrame extends JFrame {
    
    public QuanLyFrame() {
        setTitle("Hệ Thống Quản Lý Nhân Viên");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null);
        
        // Thêm trực tiếp panel quản lý nhân viên
        add(new NhanVienPanel());
    }
} 