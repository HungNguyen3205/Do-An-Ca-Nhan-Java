package javadoancanhan;

import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class NhanVienPanel extends JPanel {
    private JTable table;
    private DefaultTableModel model;
    private JTextField txtMaSV, txtHoTen, txtTuoi, txtDiaChi;
    private JComboBox<String> cboGioiTinh;
    private JButton btnThem, btnSua, btnXoa, btnMoi;
    private List<Student> studentList;

    public NhanVienPanel() {
        setLayout(new BorderLayout(10, 10));
        studentList = new ArrayList<>();
        initComponents();
    }

    private void initComponents() {
        // Panel nhập liệu
        JPanel inputPanel = new JPanel(new GridLayout(5, 2, 5, 5));
        inputPanel.setBorder(BorderFactory.createTitledBorder("Thông tin sinh viên"));

        inputPanel.add(new JLabel("Mã SV:"));
        txtMaSV = new JTextField();
        inputPanel.add(txtMaSV);

        inputPanel.add(new JLabel("Họ tên:"));
        txtHoTen = new JTextField();
        inputPanel.add(txtHoTen);
        
        inputPanel.add(new JLabel("Giới tính:"));
        String[] genders = {"Nam", "Nữ", "Khác"};
        cboGioiTinh = new JComboBox<>(genders);
        inputPanel.add(cboGioiTinh);

        inputPanel.add(new JLabel("Tuổi :"));
        txtTuoi = new JTextField();
        inputPanel.add(txtTuoi);

        inputPanel.add(new JLabel("Địa chỉ:"));
        txtDiaChi = new JTextField();
        inputPanel.add(txtDiaChi);

        

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
        String[] columns = {"Mã SV", "Họ tên", "Tuổi", "Địa chỉ", "Giới tính"};
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
        btnThem.addActionListener(e -> themSinhVien());
        btnXoa.addActionListener(e -> xoaSinhVien());
        btnSua.addActionListener(e -> suaSinhVien());
        
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
        txtMaSV.setText("");
        txtHoTen.setText("");
        txtTuoi.setText("");
        txtDiaChi.setText("");
        cboGioiTinh.setSelectedIndex(0);
        table.clearSelection();
    }

    private boolean validateInput() {
        if (txtMaSV.getText().trim().isEmpty() || 
            txtHoTen.getText().trim().isEmpty() || 
            txtTuoi.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Vui lòng nhập đầy đủ thông tin bắt buộc");
            return false;
        }
        
        try {
            int tuoi = Integer.parseInt(txtTuoi.getText().trim());
            if (tuoi <= 0) {
                JOptionPane.showMessageDialog(this, "Tuổi phải là số dương");
                return false;
            }
            return true;
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, "Tuổi phải là số");
            return false;
        }
    }

    private void themSinhVien() {
        if (!validateInput()) {
            return;
        }

        Student student = new Student(
            txtMaSV.getText(),
            txtHoTen.getText(),
            Integer.parseInt(txtTuoi.getText().trim()),
            txtDiaChi.getText(),
            cboGioiTinh.getSelectedItem().toString()
        );
        
        studentList.add(student);
        DataManager.saveData(studentList);
        
        String[] data = {
            student.getMaSV(),
            student.getHoTen(),
            String.valueOf(student.getTuoi()),
            student.getDiaChi(),
            student.getGioiTinh()
        };
        model.addRow(data);
        lamMoi();
    }

    private void xoaSinhVien() {
        int row = table.getSelectedRow();
        if (row >= 0) {
            int confirm = JOptionPane.showConfirmDialog(this, 
                "Bạn có chắc muốn xóa sinh viên này?", 
                "Xác nhận xóa", 
                JOptionPane.YES_NO_OPTION);
            
            if (confirm == JOptionPane.YES_OPTION) {
                studentList.remove(row);
                DataManager.saveData(studentList);
                model.removeRow(row);
                lamMoi();
            }
        }
    }

    private void suaSinhVien() {
        if (!validateInput()) {
            return;
        }

        int row = table.getSelectedRow();
        if (row >= 0) {
            Student student = studentList.get(row);
            student.setMaSV(txtMaSV.getText());
            student.setHoTen(txtHoTen.getText());
            student.setTuoi(Integer.parseInt(txtTuoi.getText().trim()));
            student.setDiaChi(txtDiaChi.getText());
            student.setGioiTinh(cboGioiTinh.getSelectedItem().toString());
            
            DataManager.saveData(studentList);
            
            model.setValueAt(txtMaSV.getText(), row, 0);
            model.setValueAt(txtHoTen.getText(), row, 1);
            model.setValueAt(txtTuoi.getText(), row, 2);
            model.setValueAt(txtDiaChi.getText(), row, 3);
            model.setValueAt(cboGioiTinh.getSelectedItem().toString(), row, 4);
            
            lamMoi();
        }
    }

    private void hienThiThongTin(int row) {
        txtMaSV.setText(model.getValueAt(row, 0).toString());
        txtHoTen.setText(model.getValueAt(row, 1).toString());
        txtTuoi.setText(model.getValueAt(row, 2).toString());
        txtDiaChi.setText(model.getValueAt(row, 3).toString());
        cboGioiTinh.setSelectedItem(model.getValueAt(row, 4).toString());
    }

    public void loadData() {
        studentList = DataManager.loadData();
        updateTableFromList();
    }

    private void updateTableFromList() {
        model.setRowCount(0);
        for (Student student : studentList) {
            Object[] row = {
                student.getMaSV(),
                student.getHoTen(),
                student.getTuoi(),
                student.getDiaChi(),
                student.getGioiTinh()
            };
            model.addRow(row);
        }
    }
} 