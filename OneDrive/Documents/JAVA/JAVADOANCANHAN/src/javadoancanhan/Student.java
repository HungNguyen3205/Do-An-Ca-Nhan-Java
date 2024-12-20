package javadoancanhan;

import java.io.Serializable;

// Class đại diện cho thông tin của một sinh viên
public class Student implements Serializable {
    // SerialVersionUID để đảm bảo tính tương thích khi serialize
    private static final long serialVersionUID = 1L;
    
    // Các thuộc tính của sinh viên
    private String maSV;      // Mã sinh viên
    private String hoTen;     // Họ tên sinh viên
    private int tuoi;         // Tuổi
    private String diaChi;    // Địa chỉ
    private String gioiTinh;  // Giới tính
    
    // Constructor để khởi tạo đối tượng sinh viên
    public Student(String maSV, String hoTen, int tuoi, String diaChi, String gioiTinh) {
        this.maSV = maSV;
        this.hoTen = hoTen;
        this.tuoi = tuoi;
        this.diaChi = diaChi;
        this.gioiTinh = gioiTinh;
    }
    
    // Các phương thức getter và setter
    public String getMaSV() { return maSV; }
    public void setMaSV(String maSV) { this.maSV = maSV; }
    
    public String getHoTen() { return hoTen; }
    public void setHoTen(String hoTen) { this.hoTen = hoTen; }
    
    public int getTuoi() { return tuoi; }
    public void setTuoi(int tuoi) { this.tuoi = tuoi; }
    
    public String getDiaChi() { return diaChi; }
    public void setDiaChi(String diaChi) { this.diaChi = diaChi; }
    
    public String getGioiTinh() { return gioiTinh; }
    public void setGioiTinh(String gioiTinh) { this.gioiTinh = gioiTinh; }
} 