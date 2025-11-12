import java.util.Scanner;
public class ChiTietHoaDon {
    private String mahoadon;
    private VanPhongPham sp;
    private int dongia;
    private int soluong;
    private int thanhtien;
    private String masp;
    public ChiTietHoaDon() {
        thanhtien = 0;
    }   

    public ChiTietHoaDon(String mahoadon, VanPhongPham sp, int soluong, int dongia, int thanhtien) {
        this.mahoadon = mahoadon;   
        this.sp = sp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public ChiTietHoaDon(ChiTietHoaDon a) {
        this.mahoadon = a.mahoadon;
        this.sp = a.sp;           // <-- DÒNG NÀY BẮT BUỘC PHẢI CÓ
        this.soluong = a.soluong;
        this.dongia = a.dongia;
        this.thanhtien = a.thanhtien;
        this.masp = a.masp;       // <-- DÒNG NÀY CŨNG BẮT BUỘC PHẢI CÓ
    }

    public void nhap() { 
        Scanner sc = new Scanner(System.in);
        String maspcantim;
        VanPhongPham vpptimduoc = null;

        do {
            System.out.print("  > Nhập Mã sản phẩm cần mua: ");
            maspcantim = sc.nextLine();

            // SỬ DỤNG QuanLyBanHang.dsvpp để tra cứu
            // Phải đảm bảo QLBH đã được khởi tạo và tải dữ liệu DSVPP
            vpptimduoc = QuanLyBanHang.dsvpp.timMaSP(maspcantim); // Tra cứu sản phẩm

            if (vpptimduoc != null) {
                this.sp = vpptimduoc;
                // 🎯 IN THÔNG TIN SẢN PHẨM TRƯỚC KHI NHẬP SỐ LƯỢNG
                System.out.println("  🎯 Đã tìm thấy: " + vpptimduoc.getTenSP() + ".");
                this.dongia = vpptimduoc.getDonGia(); // Giả sử hàm getGiaBan tồn tại
                System.out.printf("  > Giá Bán: %,d | Tồn Kho: %d%n", this.dongia, vpptimduoc.getSoLuong());

                // 5. Nhập số lượng mua và kiểm tra tồn kho
                int soluongcanmua;
                do {
                    System.out.print("  > Nhập Số lượng mua: ");
                    soluongcanmua = sc.nextInt();
                    sc.nextLine();

                    if (soluongcanmua <= 0 || soluongcanmua > vpptimduoc.getSoLuong()) {
                        System.out.println("  ❌ Lỗi: Số lượng không hợp lệ hoặc vượt quá tồn kho (" + vpptimduoc.getSoLuong() + "). Nhập lại!");
                    }
                } while (soluongcanmua <= 0 || soluongcanmua > vpptimduoc.getSoLuong());

                this.soluong = soluongcanmua;

                // 5. Tính Thành tiền
                this.thanhtien = this.soluong * this.dongia;

                break;

            } else {
                System.out.println("❌ Lỗi: Không tìm thấy Sản phẩm có mã: " + maspcantim + ". Vui lòng nhập lại!");
            }
        } while (true);
    }

    // Hàm xuất thông tin chi tiết (để dùng trong vòng lặp của HoaDon.xuat())
    public void xuatThongTinCT(int stt) {
        String tenSP = "N/A (Lỗi Tra Cứu)";
        if (sp != null) {
            tenSP = sp.getTenSP();
        } else {
            // Tra cứu từ mã SP String (khi đọc file, đối tượng sp chưa được liên kết)
            // VanPhongPham sp_temp = QuanLyBanHang.dsvpp.timMaSP(this.masp);
            // if (sp_temp != null) tenSP = sp_temp.getTenSP();
            tenSP = this.masp + " (Cần liên kết)";
        }

        // Giả sử VanPhongPham có getTenSP()
        System.out.printf("| %-5d | %-10s | %-30s | %-10d | %-9d | %-15d |\n",
                stt, sp.getMaSP(), sp.getTenSP(), dongia, soluong, thanhtien);
    }
    
    public String getMaHoaDon() {
        return mahoadon;
    }
    
    public void setMaHoaDon(String mahd) { 
        this.mahoadon = mahd; 
    }
    
    public int getDonGia() {
        return dongia;
    }
    
    public void setDonGia(int dongia) {
        this.dongia = dongia;
    }
    
    public int getSoLuong() {
        return soluong;
    }
    
    public void setSoLuong(int soluong) {
        this.soluong = soluong;
    }
    
    public VanPhongPham getSanPham() {
        return sp;
    }
    
    public void setSanPham(VanPhongPham sp) {
        this.sp = sp;
    }
    
    public int getThanhTien() {
        return thanhtien;
    }
    
    public void setThanhTien(int thanhtien) {
        this.thanhtien = thanhtien;
    }

    public String getMaSP() {
        return masp;
    }
    
    public void setMaSP(String masp) { // <-- Setter này nhận String
        this.masp = masp;
    }

}

