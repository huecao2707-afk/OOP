import java.util.Scanner;
public class ChiTietHoaDon {
    private String mahoadon;
    private VanPhongPham masp;
    private int dongia;
    private int soluong;
    private int thanhtien;

    public ChiTietHoaDon() {
        thanhtien = 0;
    }

    public ChiTietHoaDon(String mahoadon, VanPhongPham masp, int soluong, int dongia, int thanhtien) {
        this.mahoadon = mahoadon;
        this.masp = masp;
        this.soluong = soluong;
        this.dongia = dongia;
        this.thanhtien = thanhtien;
    }

    public ChiTietHoaDon(ChiTietHoaDon a) {
        this.mahoadon = a.mahoadon;
        this.masp = a.masp;
        this.soluong = a.soluong;
        this.dongia = a.dongia;
        this.thanhtien = a.thanhtien;
    }

    public void nhap() { // KHÔNG CẦN TRUYỀN THAM SỐ DSVPP NỮA
        Scanner sc = new Scanner(System.in);
        String maSPCanTim;
        VanPhongPham vppTimDuoc = null;

        do {
            System.out.print("  > Nhập Mã sản phẩm cần mua: ");
            maSPCanTim = sc.nextLine();

            // SỬ DỤNG QuanLyBanHang.dsvpp để tra cứu
            // Phải đảm bảo QLBH đã được khởi tạo và tải dữ liệu DSVPP
            vppTimDuoc = QuanLyBanHang.dsvpp.timMaSP(maSPCanTim); // SỬA ĐỔI

            if (vppTimDuoc != null) {
                this.masp = vppTimDuoc;
                // 🎯 IN THÔNG TIN SẢN PHẨM TRƯỚC KHI NHẬP SỐ LƯỢNG
                System.out.println("  🎯 Đã tìm thấy: " + vppTimDuoc.getTenSP() + ".");
                this.dongia = vppTimDuoc.getDonGia(); // Giả sử hàm getGiaBan tồn tại
                System.out.printf("  > Giá Bán: %,d | Tồn Kho: %d%n", this.dongia, vppTimDuoc.getSoLuong());

                // 5. Nhập số lượng mua và kiểm tra tồn kho
                int soLuongCanMua;
                do {
                    System.out.print("  > Nhập Số lượng mua: ");
                    soLuongCanMua = sc.nextInt();
                    sc.nextLine();

                    if (soLuongCanMua <= 0 || soLuongCanMua > vppTimDuoc.getSoLuong()) {
                        System.out.println("  ❌ Lỗi: Số lượng không hợp lệ hoặc vượt quá tồn kho (" + vppTimDuoc.getSoLuong() + "). Nhập lại!");
                    }
                } while (soLuongCanMua <= 0 || soLuongCanMua > vppTimDuoc.getSoLuong());

                this.soluong = soLuongCanMua;

                // 5. Tính Thành tiền
                this.thanhtien = this.soluong * this.dongia;

                break;

            } else {
                System.out.println("❌ Lỗi: Không tìm thấy Sản phẩm có mã: " + maSPCanTim + ". Vui lòng nhập lại!");
            }
        } while (true);
    }

    // Hàm xuất thông tin chi tiết (để dùng trong vòng lặp của HoaDon.xuat())
    public void xuatThongTinCT(int stt) {
        // Giả sử VanPhongPham có getTenSP()
        System.out.printf("%-5d | %-10s | %-30s | %,10d | %,-15d | %,15d%n",
                stt, masp.getMaSP(), masp.getTenSP(), dongia, soluong, thanhtien);
    }
    
    public String getMahoadon() {
        return mahoadon;
    }
    public void setMaHoaDon(String mahd) { 
        this.mahoadon = mahd; 
    }
    public int getDongia() {
        return dongia;
    }
    public void setDongia(int dongia) {
        this.dongia = dongia;
    }
    public int getSoluong() {
        return soluong;
    }
    public void setSoluong(int soluong) {
        this.soluong = soluong;
    }
    public VanPhongPham getMasp() {
        return masp;
    }
    public void setMasp(VanPhongPham masp) {
        this.masp = masp;
    }
    public int getThanhtien() {
        return thanhtien;
    }
    public void setThanhtien(int thanhtien) {
        this.thanhtien = thanhtien;
    }
}

