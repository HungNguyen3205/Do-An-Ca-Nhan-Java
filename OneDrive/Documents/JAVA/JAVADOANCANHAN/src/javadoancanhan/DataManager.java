package javadoancanhan;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

// Class quản lý việc lưu trữ và đọc dữ liệu
public class DataManager {
    // Tên file để lưu trữ dữ liệu
    private static final String DATA_FILE = "students.dat";
    
    // Phương thức lưu danh sách sinh viên vào file
    public static void saveData(List<Student> students) {
        try (ObjectOutputStream oos = new ObjectOutputStream(
                new FileOutputStream(DATA_FILE))) {
            // Ghi danh sách sinh viên vào file
            oos.writeObject(students);
        } catch (IOException e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi nếu không thể lưu
            JOptionPane.showMessageDialog(null, 
                "Lỗi khi lưu dữ liệu: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    // Phương thức đọc danh sách sinh viên từ file
    @SuppressWarnings("unchecked")
    public static List<Student> loadData() {
        // Kiểm tra xem file có tồn tại không
        if (!new File(DATA_FILE).exists()) {
            return new ArrayList<>();
        }
        
        try (ObjectInputStream ois = new ObjectInputStream(
                new FileInputStream(DATA_FILE))) {
            // Đọc và trả về danh sách sinh viên
            return (List<Student>) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
            // Hiển thị thông báo lỗi nếu không thể đọc
            JOptionPane.showMessageDialog(null, 
                "Lỗi khi đọc dữ liệu: " + e.getMessage(), 
                "Lỗi", 
                JOptionPane.ERROR_MESSAGE);
            return new ArrayList<>();
        }
    }
} 