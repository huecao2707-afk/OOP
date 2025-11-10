import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;
public class DSKH {
        private int n;
        private KhachHang[] dskh;
        public DSKH(){
            dskh = new KhachHang[300]; // 300 san pham t
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
    public void docFileKhachHang() {
        n = 0; // Reset số lượng về 0
        try (BufferedReader br = new BufferedReader(new FileReader("KhachHang.txt"))) {
            String line;

            while ((line = br.readLine()) != null) { // Đọc từng dòng
                String[] thongtin = line.split(",");

                // Kiểm tra xem có đủ 5 trường thông tin hay không:
                // makh, ho, ten, diachi, sodt
                if (thongtin.length < 5) {
                    System.out.println("Lỗi dữ liệu: Không đủ thông tin khách hàng. Bỏ qua: " + line);
                    continue; // Bỏ qua dòng này
                }

                // 1. Mã KH (thongtin[0])
                // 2. Họ (thongtin[1])
                // 3. Tên (thongtin[2])
                // 4. Địa chỉ (thongtin[3])
                // 5. Số ĐT (thongtin[4])

                KhachHang kh = new KhachHang(
                        thongtin[0].trim(), // makh
                        thongtin[1].trim(), // ho
                        thongtin[2].trim(), // ten
                        thongtin[3].trim(), // diachi
                        thongtin[4].trim()  // sodt
                );

                dskh[n] = kh; // Thêm khách hàng vào mảng
                n++; // Tăng số lượng

                if (n >= dskh.length) {
                    System.out.println("Cảnh báo: Đã đầy mảng lưu trữ khách hàng.");
                    break; // Dừng nếu mảng đầy
                }
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi đọc file KhachHang.txt: " + e.getMessage());
        }
    }

    // Hàm in ra danh sách khách hàng để kiểm tra
    public void xuatDanhSachKhachHang() {
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
    // Trong file DSKH.java (Thêm vào class DSKH)
    public KhachHang timKiemTheoMa(String makh) {
        // Giả sử dskh là mảng chứa các đối tượng KhachHang
        for (int i = 0; i < dskh.length; i++) {
            // Phải kiểm tra null nếu mảng có thể có phần tử null
            if (dskh[i] != null && dskh[i].getMaKH().equalsIgnoreCase(makh)) {
                return dskh[i];
            }
        }
        return null;
    }
    public KhachHang timKiemTheoMa() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Nhập mã của khách hàng cần tìm: ");
        String ma = sc.nextLine();      // Giả sử dskh là mảng chứa các đối tượng KhachHang
        for (int i = 0; i < dskh.length; i++) {
            // Phải kiểm tra null nếu mảng có thể có phần tử null
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
                return false; // Mã đã tồn tại
            }
        }
        return true; // Mã duy nhất
    }

    public void themKhachHang(){
        // 1. Kiểm tra mảng còn chỗ không
        if (n >= dskh.length) {
            System.out.println("❌ Mảng trong bộ nhớ đã đầy, không thể thêm!");
            return ;
        }
        Scanner sc = new Scanner(System.in);
        KhachHang khmoi = new KhachHang();
        khmoi.nhap();
        while (!maDuyNhat(khmoi.getMaKH())) {
            System.out.println("❌ Mã khách hàng đã tồn tại. Vui lòng nhập lại.");
            System.out.println("Nhập lại mã khách hàng: ");
            String ma = sc.nextLine();
            khmoi.setMaKH(ma);
        }
        dskh[n] = khmoi;
        n++;

        System.out.println("-> Đã thêm khách hàng mới thành công !");
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
                System.out.println("2. Sửa Địa Chỉ");
                System.out.println("3. Sửa Số Điện Thoại");
                System.out.print("Chọn: ");
                int luachon = sc.nextInt();
                sc.nextLine(); // tránh trôi dòng
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

                } else if (luachon == 3) {
                    System.out.print("Nhập lương mới: ");
                    String newsdt = sc.nextLine();
                    dskh[i].setSoDT(newsdt);

                }
                System.out.println("✅ Đã cập nhật thông tin khách hàng.");
                return;
            }
        }
        System.out.println("❌ Không tìm thấy khách hàng với mã: " + ma);
    }

}
