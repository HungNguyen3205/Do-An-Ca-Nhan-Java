package javadoancanhan;

public class ThongTinLuong {
    private String maNV;
    private String hoTen;
    private int soNgayCong;
    private double luongCoBan;
    private double phuCap;
    private double tongLuong;
    
    public ThongTinLuong(String maNV, String hoTen, int soNgayCong, double luongCoBan, double phuCap) {
        this.maNV = maNV;
        this.hoTen = hoTen;
        this.soNgayCong = soNgayCong;
        this.luongCoBan = luongCoBan;
        this.phuCap = phuCap;
        tinhTongLuong();
    }
    
    private void tinhTongLuong() {
        // Công thức tính lương: (Lương cơ bản / 22) * số ngày công + phụ cấp
        this.tongLuong = (luongCoBan / 22) * soNgayCong + phuCap;
    }
    
    // Getters
    public String getMaNV() { return maNV; }
    public String getHoTen() { return hoTen; }
    public int getSoNgayCong() { return soNgayCong; }
    public double getLuongCoBan() { return luongCoBan; }
    public double getPhuCap() { return phuCap; }
    public double getTongLuong() { return tongLuong; }
} 