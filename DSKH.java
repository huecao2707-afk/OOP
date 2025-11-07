import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
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
}
