import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
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
                dsnv[i].xuat();
                return dsnv[i];
            }
        }
        return null; // Không tìm thấy
    }
    public void themNhanVien(){
        // 1. Kiểm tra mảng còn chỗ không
        if (n >= dsnv.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return ;
        }

        Scanner sc = new Scanner(System.in);
        
        NhanVien nvmoi = new NhanVien();
        nvmoi.nhap();
        while (!maDuyNhat(nvmoi.getMaNV())) {
            System.out.println("❌ Mã nhân viên đã tồn tại. Vui lòng nhập lại.");
            System.out.println("Nhập lại mã sinh viên: ");
            String ma = sc.nextLine();
            nvmoi.setMaNV(ma);
        }
        dsnv[n] = nvmoi;
        n++;

        System.out.println("-> Đã thêm nhân viên mới thành công !");
    }

    public boolean maDuyNhat(String manv) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(manv)) {
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã duy nhất
    }

    public void xoaSinhVien(String ma) {
        if (dsnv.length == 0) {
            System.out.println("❌ Danh sách trống, không thể xóa.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (ma.equals(dsnv[i].getMaNV())) {
                for (int j = i; j < n - 1; j++){
                    dsnv[j] = dsnv[j + 1];
                }
            }
        }
        dsnv = Arrays.copyOf(dsnv, dsnv.length - 1);
        n--;
        System.out.println("✅ Đã xóa nhân viên có mã " + ma);
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã số sinh viên cần sửa: ");
        String ma = sc.nextLine();
        for (int i = 0; i < dsnv.length; i++) {
            if (ma.equals(dsnv[i].getMaNV())) {
                System.out.println("Chọn thông tin cần sửa");
                System.out.println("1. Sửa Họ");
                System.out.println("2. Sửa Tên");
                System.out.println("2. Sửa Lương Tháng");
                System.out.print("Chọn: ");
                int luachon = sc.nextInt();
                sc.nextLine(); // tránh trôi dòng
                if (luachon == 1) {
                    System.out.print("Nhập họ mới: ");
                    String newHo = sc.nextLine();
                    dsnv[i].setHo(newHo);
                } else if (luachon == 2) {
                    System.out.print("Nhập tên mới: ");
                    String newTen = sc.nextLine();
                    dsnv[i].setTen(newTen);
                } else if (luachon == 3) {
                    System.out.print("Nhập lương mới: ");
                    int newluong = sc.nextInt();
                    dsnv[i].setLuongThang(newluong);

                }
                System.out.println("✅ Đã cập nhật thông tin nhân viên.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy nhân viên với mã: " + ma);
    }

}
