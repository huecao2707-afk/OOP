import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
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

    public void docFileNhanVien() {
        n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("NhanVien.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");

                if (thongtin.length < 4) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin nhân viên. Bỏ qua: " + line);
                    continue;
                }

                int luong;
                try {
                    luong = Integer.parseInt(thongtin[3].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Lỗi dữ liệu: Lương tháng không hợp lệ. Bỏ qua: " + line);
                    continue;
                }

                NhanVien nv = new NhanVien(
                        thongtin[0].trim(),
                        thongtin[1].trim(),
                        thongtin[2].trim(),
                        luong
                );

                if (n < dsnv.length) {
                    dsnv[n] = nv;
                    n++;
                } else {
                    System.out.println("Cảnh báo: Đã đầy mảng lưu trữ nhân viên. Dừng đọc file.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file NhanVien.txt: " + e.getMessage());
        }
    }

    public void ghiFileNhanVien(NhanVien nv){
        String line ="";
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhanVien.txt", true))) {
            line = nv.getMaNV() + "," + nv.getHo() + "," + nv.getTen() + "," + nv.getLuongThang();

            bw.write(line);
            bw.newLine();
            } 
        catch (IOException e) {
                System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
            }
    }

    public void ghiLaiToanBoFileNhanVien() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("NhanVien.txt", false))) {
            for (int i = 0; i < n; i++) {
                NhanVien nv = dsnv[i];
                String line = nv.getMaNV() + "," + nv.getHo() + "," + nv.getTen() + "," + nv.getLuongThang();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }

    public void xuat() {
        if (n == 0) { 
            System.out.println("Danh sách nhân viên trống.");
            return;
        }
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
        return null;
    }

    public NhanVien timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã của nhân viên cần tìm: ");
            String ma = sc.nextLine();
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(ma)) {
                System.out.printf("%s, %s , %d\n", dsnv[i].getMaNV(),dsnv[i].getHo() + " " + dsnv[i].getTen(), + dsnv[i].getLuongThang());
                return dsnv[i];
            }
        }
        return null;
    }

    public void themNhanVien(){
        if (n >= dsnv.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return ;
        }

        Scanner sc = new Scanner(System.in);
        
        NhanVien nvmoi = new NhanVien();
        nvmoi.nhap();
        while (!maDuyNhat(nvmoi.getMaNV())) {
            System.out.println("❌ Mã nhân viên đã tồn tại. Vui lòng nhập lại.");
            System.out.print("Nhập lại mã sinh viên: ");
            String ma = sc.nextLine();
            nvmoi.setMaNV(ma);
        }
        dsnv[n] = nvmoi;
        n++;
        ghiFileNhanVien(nvmoi);
        System.out.println("-> Đã thêm nhân viên mới thành công !");
    }

    public boolean maDuyNhat(String manv) {
        for (int i = 0; i < n; i++) {
            if (dsnv[i].getMaNV().equalsIgnoreCase(manv)) {
                return false;
            }
        }
        return true;
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
        ghiLaiToanBoFileNhanVien();
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
                System.out.println("3. Sửa Lương Tháng");
                System.out.print("Chọn: ");
                int luachon = sc.nextInt();
                sc.nextLine();
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
                ghiLaiToanBoFileNhanVien();
                System.out.println("✅ Đã cập nhật thông tin nhân viên.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy nhân viên với mã: " + ma);
    }
    public int getN() {
        return n; 
    }
}