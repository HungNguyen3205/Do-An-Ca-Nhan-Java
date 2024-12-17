package javadoancanhan;

import java.awt.*;
import java.awt.event.*;
import java.text.DecimalFormat;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaNV, txtHoTen, txtNgaySinh, txtDiaChi, txtSDT, txtChucVu, txtLuong;
    private JButton btnThem, btnSua, btnXoa, btnMoi;
    private DecimalFormat formatter;

    public NhanVienPanel() {
        setLayout(new BorderLayout(10, 10));
        formatter = new DecimalFormat("#,###");
        initComponents();
    }

    private void initComponents() {
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(7, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin nhân viên"));

        inputPanel.add(new JLabel("Mã NV:"));
        txtMaNV = new JTextField();
        inputPanel.add(txtMaNV);

        inputPanel.add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        inputPanel.add(txtHoTen);

        inputPanel.add(new JLabel("Ngày sinh:"));
        txtNgaySinh = new JTextField();
        inputPanel.add(txtNgaySinh);

        inputPanel.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        inputPanel.add(txtDiaChi);

        inputPanel.add(new JLabel("Số điện thoại:"));
        txtSDT = new JTextField();
        inputPanel.add(txtSDT);

        inputPanel.add(new JLabel("Chức vụ:"));
        txtChucVu = new JTextField();
        inputPanel.add(txtChucVu);

        inputPanel.add(new JLabel("Lương:"));
        txtLuong = new JTextField();
        inputPanel.add(txtLuong);

        // Panel chứa nút
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        btnThem = new JButton("Thêm");
        btnSua = new JButton("Sửa");
        btnXoa = new JButton("Xóa");
        btnMoi = new JButton("Làm mới");

        buttonPanel.add(btnThem);
        buttonPanel.add(btnSua);
        buttonPanel.add(btnXoa);
        buttonPanel.add(btnMoi);

        // Tạo bảng
        String[] columns = {"Mã NV", "Họ tên", "Ngày sinh", "Địa chỉ", "SĐT", "Chức vụ", "Lương"};
        model = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);

        // Layout
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.add(inputPanel, BorderLayout.CENTER);
        topPanel.add(buttonPanel, BorderLayout.SOUTH);

        add(topPanel, BorderLayout.NORTH);
        add(scrollPane, BorderLayout.CENTER);

        // Thêm sự kiện
        addEvents();
    }

    private void addEvents() {
        btnMoi.addActionListener(e -> lamMoi());
        btnThem.addActionListener(e -> themNhanVien());
        btnXoa.addActionListener(e -> xoaNhanVien());
        btnSua.addActionListener(e -> suaNhanVien());
        
        table.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int row = table.getSelectedRow();
                if (row >= 0) {
                    hienThiThongTin(row);
                }
            }
        });
    }

    private void lamMoi() {
        txtMaNV.setText("");
        txtHoTen.setText("");
        txtNgaySinh.setText("");
        txtDiaChi.setText("");
        txtSDT.setText("");
        txtChucVu.setText("");
        txtLuong.setText("");
        table.clearSelection();
    }

    private boolean validateInput() {
        if (txtMaNV.getText().trim().isEmpty() || 
            txtHoTen.getText().trim().isEmpty() || 
            txtNgaySinh.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc");
            return false;
        }
        
        try {
            if (!txtLuong.getText().trim().isEmpty()) {
                Double.parseDouble(txtLuong.getText().trim());
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Lương phải là số");
            return false;
        }
    }

    private void themNhanVien() {
        if (!validateInput()) {
            return;
        }

        String luongStr = txtLuong.getText().trim().isEmpty() ? "0" : 
                         formatter.format(Double.parseDouble(txtLuong.getText().trim()));

        String[] data = {
            txtMaNV.getText(),
            txtHoTen.getText(),
            txtNgaySinh.getText(),
            txtDiaChi.getText(),
            txtSDT.getText(),
            txtChucVu.getText(),
            luongStr
        };
        model.addRow(data);
        lamMoi();
    }

    private void xoaNhanVien() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa nhân viên này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                model.removeRow(row);
                lamMoi();
            }
        }
    }

    private void suaNhanVien() {
        if (!validateInput()) {
            return;
        }

        int row = table.getSelectedRow();
        if (row >= 0) {
            String luongStr = txtLuong.getText().trim().isEmpty() ? "0" : 
                             formatter.format(Double.parseDouble(txtLuong.getText().trim()));

            model.setValueAt(txtMaNV.getText(), row, 0);
            model.setValueAt(txtHoTen.getText(), row, 1);
            model.setValueAt(txtNgaySinh.getText(), row, 2);
            model.setValueAt(txtDiaChi.getText(), row, 3);
            model.setValueAt(txtSDT.getText(), row, 4);
            model.setValueAt(txtChucVu.getText(), row, 5);
            model.setValueAt(luongStr, row, 6);
            
            lamMoi();
        }
    }

    private void hienThiThongTin(int row) {
        txtMaNV.setText(model.getValueAt(row, 0).toString());
        txtHoTen.setText(model.getValueAt(row, 1).toString());
        txtNgaySinh.setText(model.getValueAt(row, 2).toString());
        txtDiaChi.setText(model.getValueAt(row, 3).toString());
        txtSDT.setText(model.getValueAt(row, 4).toString());
        txtChucVu.setText(model.getValueAt(row, 5).toString());
        txtLuong.setText(model.getValueAt(row, 6).toString());
    }
} 