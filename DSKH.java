import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class DSKH {
        private int n;
        public KhachHang[] dskh;
        public DSKH(){
            dskh = new KhachHang[300];
            n = 0;
        }
        public DSKH(int n, KhachHang []dskh){
            this.n = n;
            this.dskh = dskh;
        }
        public DSKH(DSKH other){
            this.n = other.n;
            this.dskh = new KhachHang[n];
            for (int i = 0; i < dskh.length; i++){
                this.dskh[i] = new KhachHang(other.dskh[i]);
            }
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap so luong khach hang n =  ");
            n = sc.nextInt();
            dskh = new KhachHang[n];
            for(int i = 0; i < n; i++){
                dskh[i] = new KhachHang();
                dskh[i].nhap();
            }
        }

    public void ghiFileKhachHang(KhachHang kh){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("KhachHang.txt", true))) {
            String line = kh.getMaKH() + "," + kh.getHo() + "," + kh.getTen() + "," + kh.getSoDT() + "," + kh.getDiaChi();

            bw.write(line);
            bw.newLine();
            } 
        catch (IOException e) {
                System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
            }
    }

    public void ghiLaiToanBoFileKhachHang() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("KhachHang.txt", false))) {
            for (int i = 0; i < n; i++) {
                KhachHang kh = dskh[i];
                String line = kh.getMaKH() + "," + kh.getHo() + "," + kh.getTen() + "," + kh.getSoDT() + "," + kh.getDiaChi();
                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }

    public void docFileKhachHang() {
        n = 0;
        try (BufferedReader br = new BufferedReader(new FileReader("KhachHang.txt"))) {
            String line;

            while ((line = br.readLine()) != null) {
                String[] thongtin = line.split(",");

                if (thongtin.length < 5) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin khách hàng. Bỏ qua: " + line);
                    continue;
                }

                KhachHang kh = new KhachHang(
                        thongtin[0].trim(),
                        thongtin[1].trim(),
                        thongtin[2].trim(),
                        thongtin[3].trim(),
                        thongtin[4].trim()
                );

                dskh[n] = kh;
                n++;

                if (n >= dskh.length) {
                    System.out.println("Cảnh báo: Đã đầy mảng lưu trữ khách hàng.");
                    break;
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file KhachHang.txt: " + e.getMessage());
        }
    }

    public void xuatDanhSachKhachHang() {
        if (n == 0) {
            System.out.println("Danh sách khách hàng trống.");
            return;
        }
        String line = "+-----------------+---------------------------+-----------------+--------------------------+";
        String format = "| %-15s | %-25s | %-15s | %-24s |\n";

        System.out.println("\n--- Danh sách Khách hàng ---");
        System.out.println(line);
        System.out.printf(format, "Mã KH", "Họ Tên", "SĐT", "Địa chỉ");
        System.out.println(line);
        for (int i = 0; i < n; i++) {
            dskh[i].xuat();
        }
        System.out.println(line);
    }
    public KhachHang timKiemTheoMa(String makh) {
        for (int i = 0; i < dskh.length; i++) {
            if (dskh[i] != null && dskh[i].getMaKH().equalsIgnoreCase(makh)) {
                return dskh[i];
            }
        }
        return null;
    }
    
    public KhachHang timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã của khách hàng cần tìm: ");
        String ma = sc.nextLine();
        for (int i = 0; i < dskh.length; i++) {
            if (dskh[i] != null && dskh[i].getMaKH().equalsIgnoreCase(ma)) {
                System.out.printf("%s, %s, %s, %s", dskh[i].getMaKH(), dskh[i].getHo() + " " + dskh[i].getTen(), dskh[i].getSoDT() , dskh[i].getDiaChi());
                return dskh[i];
            }
        }
        return null;
    }
    
    public boolean maDuyNhat(String makh) {
        for (int i = 0; i < n; i++) {
            if (dskh[i].getMaKH().equalsIgnoreCase(makh)) {
                return false;
            }
        }
        return true;
    }

    public void themKhachHang(){
        if (n >= dskh.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return ;
        }
        Scanner sc = new Scanner(System.in);
        KhachHang khmoi = new KhachHang();
        khmoi.nhap();
        while (!maDuyNhat(khmoi.getMaKH())) {
            System.out.println("❌ Mã khách hàng đã tồn tại. Vui lòng nhập lại.");
            System.out.print("Nhập lại mã khách hàng: ");
            String ma = sc.nextLine();
            khmoi.setMaKH(ma);
        }
        dskh[n] = khmoi;
        n++;
        ghiFileKhachHang(khmoi);
        System.out.print("-> Đã thêm khách hàng mới thành công !");
    }
    public void themKhachHang(KhachHang kh){
        if (!maDuyNhat(kh.getMaKH())) {
            System.out.println("❌ Lỗi: Mã khách hàng '" + kh.getMaKH() + "' đã tồn tại. Không thể thêm.");
            return;
        }
        dskh[n]= kh;
        n++;
        ghiFileKhachHang(kh);
    }

    public void xoaKhachHang(String ma) {
        if (dskh.length == 0) {
            System.out.println("❌ Danh sách trống, không thể xóa.");
            return;
        }
        for (int i = 0; i < n; i++) {
            if (ma.equals(dskh[i].getMaKH())) {
                for (int j = i; j < n - 1; j++){
                    dskh[j] = dskh[j + 1];
                }
            }
        }
        dskh = Arrays.copyOf(dskh, dskh.length - 1);
        n--;
        System.out.println("✅ Đã xóa khách hàng có mã " + ma);
        ghiLaiToanBoFileKhachHang();
    }

    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã khách hàng cần sửa: ");
        String ma = sc.nextLine();
        for (int i = 0; i < dskh.length; i++) {
            if (ma.equals(dskh[i].getMaKH())) {
                System.out.println("Chọn thông tin cần sửa");
                System.out.println("1. Sửa Họ");
                System.out.println("2. Sửa Tên");
                System.out.println("3. Sửa Địa Chỉ");
                System.out.println("4. Sửa Số Điện Thoại");
                System.out.print("Chọn: ");
                int luachon = sc.nextInt();
                sc.nextLine();
                if (luachon == 1) {
                    System.out.print("Nhập họ mới: ");
                    String newHo = sc.nextLine();
                    dskh[i].setHo(newHo);
                } else if (luachon == 2) {
                    System.out.print("Nhập tên mới: ");
                    String newTen = sc.nextLine();
                    dskh[i].setTen(newTen);
                } else if (luachon == 3) {
                    System.out.print("Nhập đại chỉ mới: ");
                    String newdiachi = sc.nextLine();
                    dskh[i].setDiaChi(newdiachi);

                } else if (luachon == 4) {
                    System.out.print("Nhập lương mới: ");
                    String newsdt = sc.nextLine();
                    dskh[i].setSoDT(newsdt);

                }
                System.out.println("✅ Đã cập nhật thông tin khách hàng.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy khách hàng với mã: " + ma);
        ghiLaiToanBoFileKhachHang();
    }
    public int getN() {
        return n; 
    }
}