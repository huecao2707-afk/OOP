import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Scanner;

public class DSHD {
    private int n;
    public HoaDon[] dshd;
    public DSHD(){
        dshd = new HoaDon[100];
        n = 0;
    }
    public DSHD(int n,HoaDon[] dshd){
        this.n = n;
        this.dshd = dshd;
    }
   public DSHD(DSHD other) {
        this.n = other.n; 
        this.dshd = new HoaDon[other.dshd.length];
        for (int i = 0; i < this.n; i++) { 
            this.dshd[i] = new HoaDon(other.dshd[i]);
        }
    }

    // Trong class DSHD.java
    public void ghiFileHoaDon(HoaDon hd){
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("HoaDon.txt", true))) {
            // Lấy mã NV/KH từ các trường String tạm thời:
            String line = hd.getMaHoaDon() + "," + hd.getMaNhanVien() + "," + hd.getMaKH() + "," + hd.getNgayLapHD() + "," + hd.getTongTien();

            bw.write(line);
            bw.newLine();
        }
        catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi thêm vào file: " + e.getMessage());
        }
    }

    public void ghiLaiToanBoFileHoaDon() {
        try (BufferedWriter bw = new BufferedWriter(new FileWriter("HoaDon.txt", false))) {
            for (int i = 0; i < n; i++) {
                HoaDon hd = dshd[i];

                // Lấy mã NV/KH từ các trường String tạm thời:
                String manv = hd.getMaNhanVien(); // Đây là String manvstring
                String makh = hd.getMaKH();      // Đây là String makhstring

                String line = hd.getMaHoaDon() + "," + manv + "," + makh + "," + hd.getNgayLapHD() + "," + hd.getTongTien();

                bw.write(line);
                bw.newLine();
            }
        } catch (IOException e) {
            System.out.println("❌ Lỗi khi ghi lại toàn bộ file: " + e.getMessage());
        }
    }
    public void docFileHoaDon() {
            n = 0; // Reset số lượng về 0
            try (BufferedReader br = new BufferedReader(new FileReader("HoaDon.txt"))) {
                String line;
        
                while ((line = br.readLine()) != null) { // Đọc từng dòng
                    String[] thongtin = line.split(",");
        
                    // YÊU CẦU 5 TRƯỜNG: mahd, manv, makh, ngaylaphd, tongtien
                    if (thongtin.length < 5) {
                        System.out.println("Lỗi dữ liệu: Không đủ thông tin hóa đơn. Bỏ qua: " + line);
                        continue;
                    }
        
                    // Chuyển đổi Tổng Tiền từ String sang int
                    int tongtien;
                    try {
                        tongtien = Integer.parseInt(thongtin[4].trim());
                    } catch (NumberFormatException e) {
                        System.out.println("Lỗi dữ liệu: Tổng tiền không hợp lệ. Bỏ qua: " + line);
                        continue;
                    }
                    
                    // LƯU Ý QUAN TRỌNG:
                    // Vì đối tượng HoaDon lưu trữ NhanVien và KhachHang (Object) chứ không phải mã (String),
                    // ở đây ta chỉ có thể lưu trữ Mã NV và Mã KH (String) tạm thời.
                    // Để có đối tượng NhanVien/KhachHang hoàn chỉnh, bạn cần một hàm khác
                    // để tra cứu (lookup) NhanVien/KhachHang từ ID sau khi toàn bộ file đã được đọc.
                    // TẠM THỜI: Ta chỉ cần 3 trường chính của HoaDon cho file: mahd, ngaylaphd, tongtien. 
                    // Hai trường còn lại (manv, makh) sẽ được gán sau.
        
                    HoaDon hd = new HoaDon();
                    hd.setMaHoaDon(thongtin[0].trim()); // mahoadon
                    hd.setMaNhanVien(thongtin[1].trim()); //  Mã NV string
                    hd.setMaKH(thongtin[2].trim()); //Mã KH string
                    hd.setNgayLapHD(thongtin[3].trim()); // ngaylaphd (Cần thêm setNgayLapHD() vào class HoaDon)
                    hd.setTongTien(tongtien);           // tongtien
        
                    dshd[n] = hd; // Thêm hóa đơn vào mảng
                    n++; // Tăng số lượng
        
                    if (n >= dshd.length) {
                        System.out.println("Cảnh báo: Đã đầy mảng lưu trữ hóa đơn.");
                        break;
                    }
                }
            } catch (IOException e) {
                System.out.println("❌ Lỗi đọc file HoaDon.txt: " + e.getMessage());
            }
        }

    public void nhap(NhanVien nv_dang_nhap) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        int soLuongMoi = sc.nextInt();
        sc.nextLine();

        // Tăng kích thước mảng cũ lên n + soLuongMoi
        this.dshd = Arrays.copyOf(this.dshd, this.n + soLuongMoi);

        for (int i = 0; i < soLuongMoi; i++) {
            System.out.println("----------HOA DON " + (this.n + i + 1) + "----------"); // Cập nhật STT
            HoaDon hd = new HoaDon();

            // TRUYỀN THÔNG TIN NHÂN VIÊN CHO HÓA ĐƠN NÀY
            // Việc tìm KH và nhập CTHD đã được chuyển sang QLHD.themMotHoaDonMoi()
            // Nếu dùng hàm nhap() này thì phải đảm bảo QLBH.dskh và QLBH.dsvpp đã được tải.
            hd.setNhanVien(nv_dang_nhap);
            hd.nhap(); // Hàm nhập cơ bản (Mã HĐ, Ngày Lập)
            // **KHUYẾN NGHỊ:** Bạn cần thêm logic TÌM KH và NHẬP CTHD vào đây nếu dùng hàm này.
            // Tốt nhất là chỉ dùng hàm themMotHoaDon() được điều phối từ QLHD.

            // Lưu Hóa đơn vào mảng
            this.dshd[this.n + i] = hd;
        }

        this.n += soLuongMoi; // Cập nhật n
    }
    public HoaDon timHDtheoMa(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                return dshd[i]; // Trả về hóa đơn tìm thấy
            }
        }
        return null; // Không tìm thấy
    }
    
    public int timViTriHD(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                return i; // Trả về vị trí (index)
            }
        }
        return -1; 
    }
    
    public boolean maDuyNhat(String mahd){
        for(int i = 0; i < n; i++)
            if(dshd[i].getMaHoaDon().equals(mahd))
                return false;
        return true;
    }
    
    public void themMotHoaDon(HoaDon hd) {
        if (maDuyNhat(hd.getMaHoaDon())) {
        this.dshd = Arrays.copyOf(this.dshd, this.n + 1);
        this.dshd[this.n] = new HoaDon(hd);
        this.n++;
            System.out.println("Đã thêm thành công 1 hóa đơn mới vào danh sách.");
        } else {
            System.out.println("❌ Lỗi: Mã hóa đơn '" + hd.getMaHoaDon() + "' đã tồn tại. Không thể thêm.");
        }ghiFileHoaDon(hd);
    }
    public void xoaHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String mahd = sc.nextLine();
        int vitrixoa = timViTriHD(mahd);

        if (vitrixoa == -1) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + mahd);
            return;
        }
        for (int i = vitrixoa; i < n - 1; i++) {
            dshd[i] = dshd[i + 1];
        }
        dshd[n - 1] = null;
        n--;
        System.out.println("Đã xóa hóa đơn " + mahd);
        ghiLaiToanBoFileHoaDon();
    }
    public void xuat(){
        String line = "+------------+------------+------------+-----------------+------------+";
        String format = "| %-10s | %-10s | %-10s | %-15s | %-10s |\n";
        System.out.println("\n--- Danh sách Đơn Hàng ---");
        System.out.println(line);
        System.out.printf(format,"Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
        System.out.println(line);
        for(int i = 0; i < n; i++){
            dshd[i].xuat();
        }
        System.out.println(line);

    }
}