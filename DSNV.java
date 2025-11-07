import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Scanner;
public class DSNV {
    public NhanVien[] dsnv = new NhanVien[1];
    private int n;

    public DSNV() {
        n = 0;
        dsnv = new NhanVien[50];
    }

    public DSNV(int n, NhanVien[] dsnv) {
        this.n = n;
        this.dsnv = dsnv;
    }

    public DSNV(DSNV other) {
        this.n = other.n;
        this.dsnv = new NhanVien[n];
        for (int i = 0; i < dsnv.length; i++) {
            this.dsnv[i] = new NhanVien(other.dsnv[i]);
        }
    }

    // B. Thêm hàm docFileNhanVien()
    public void docFileNhanVien() {
        n = 0; // Reset số lượng về 0
        try (BufferedReader br = new BufferedReader(new FileReader("NhanVien.txt"))) {
            String line;

            while ((line = br.readLine()) != null) { // Đọc từng dòng
                String[] thongtin = line.split(",");

                // Kiểm tra xem có đủ 4 trường thông tin hay không:
                // manv, ho, ten, luongthang
                if (thongtin.length < 4) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin nhân viên. Bỏ qua: " + line);
                    continue;
                }

                // Chuyển đổi lương tháng từ String sang int
                int luong;
                try {
                    luong = Integer.parseInt(thongtin[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Lương tháng không hợp lệ. Bỏ qua: " + line);
                    continue;
                }

                NhanVien nv = new NhanVien(
                        thongtin[0].trim(), // manv
                        thongtin[1].trim(), // ho
                        thongtin[2].trim(), // ten
                        luong               // luongthang
                );

                if (n < dsnv.length) {
                    dsnv[n] = nv; // Thêm nhân viên vào mảng
                    n++; // Tăng số lượng
                } else {
                    System.out.println("Cảnh báo: Đã đầy mảng lưu trữ nhân viên. Dừng đọc file.");
                    break;
                }
            }
            System.out.println("✅ Đọc file thành công. Đã nạp " + n + " nhân viên.");

        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file NhanVien.txt: " + e.getMessage());
        }
    }

    public void xuat() {
        String line = "+-----------------+---------------------------+-------------+";
        String format = "| %-15s | %-25s | %-10s |\n";

        System.out.println("-------------DANH SACH NHAN VIEN-------------");
        System.out.println(line);
        System.out.printf(format, "Mã Nhân Viên", "Họ và Tên", "Lương Tháng");
        System.out.println(line);
        for (int i = 0; i < n; i++) {
            dsnv[i].xuat();

        }
        System.out.println(line);
    }

    public NhanVien timKiemTheoMa(String ma_nv) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(ma_nv)) {
                return dsnv[i];
            }
        }
        return null; // Không tìm thấy
    }
}
