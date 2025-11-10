import java.util.Scanner;

public class HoaDon {
    private NhanVien nv; // Kế thừa has a từ  class NhanVien
    private KhachHang makh; // Kế thừa has a từ  class KhachHang
    private String mahoadon, ngaylaphd;
    private int tongtien;
    public HoaDon(){
        tongtien=0;
    }
    public HoaDon(String mahoadon,NhanVien manv, KhachHang makh, String ngaylaphd, int tongtien){
        this.mahoadon=mahoadon;
        this.nv=manv;
        this.makh=makh;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
    public HoaDon(HoaDon x){
        this.mahoadon=x.mahoadon;
        this.nv=x.nv;
        this.makh=x.makh;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
    }

   public String getMaHoaDon() {
       return mahoadon;
   }

    public int getTongTien() {
        return tongtien;
    }

    public void setMaHoaDon(String mahoadon) {
        this.mahoadon = mahoadon;
    }
    public void setNhanVien(NhanVien nv) {
        this.nv = nv;
    }
    public void setKhachHang(KhachHang makh) {
        this.makh = makh;
    }
    public void setTongTien(int tongtien) {
        this.tongtien = tongtien;
    }
    public void nhap() { // Không cần truyền NV, KH, CTHD vào hàm nhập cơ bản này nữa
        Scanner sc = new Scanner(System.in);

        // 1. Nhập Mã Hóa đơn (Phải đảm bảo Mã không trùng - cần thêm kiểm tra nếu có)
        System.out.print("Nhập Mã hóa đơn: ");
        mahoadon = sc.nextLine();

        // 2. Nhập Ngày lập hóa đơn
        System.out.print("Nhập Ngày lập HĐ (VD: dd/mm/yyyy): ");
        ngaylaphd = sc.nextLine();

    }
    void xuatHoaDonDayDu() {

        String tenNV = (nv != null) ? (nv.getHo() + " " + nv.getTen()) : "Chưa gán"; // Giả định NV có getHo/getTen
        String tenKH = (makh != null) ? (makh.getHo() + " " + makh.getTen()) : "Chưa gán";
        String sdtKH = (makh != null) ? makh.getSoDT() : "N/A";

        System.out.println("\n==========================================================================");
        System.out.println("========================== PHIẾU THANH TOÁN ==============================");
        System.out.println("==========================================================================");
        System.out.printf("Mã Hóa Đơn: %-20s Ngày Lập: %s%n", mahoadon, ngaylaphd);
        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("Nhân Viên Lập: %-20s | Mã NV: %s%n", tenNV, nv.getMaNV());
        System.out.printf("Khách Hàng:    %-20s | Mã KH: %s%n", tenKH, makh.getMaKH());
        System.out.printf("Số Điện Thoại: %s%n", sdtKH);
        System.out.println("==========================================================================");

        // TIÊU ĐỀ CHI TIẾT
        System.out.printf("%-5s | %-10s | %-30s | %-10s | %-15s | %-15s%n",
                "STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
        System.out.println("--------------------------------------------------------------------------");

       // TÌM CÁC CTHD CÓ MÃ HÓA ĐƠN TRÙNG KHỚP VỚI mahoadon CỦA ĐỐI TƯỢNG NÀY
        ChiTietHoaDon[] cthd_cua_hd = QuanLyBanHang.dscthd.timCTHDTheoMaHD(this.mahoadon);
        int n_cthd_cua_hd = (cthd_cua_hd != null) ? cthd_cua_hd.length : 0;

        // IN CHI TIẾT HÓA ĐƠN ĐÃ TÌM ĐƯỢC
        for (int i = 0; i < n_cthd_cua_hd; i++) {
            cthd_cua_hd[i].xuatThongTinCT(i + 1);
        }

        System.out.println("--------------------------------------------------------------------------");
        System.out.printf("TỔNG CỘNG: %62s %,d VNĐ%n", "", tongtien);
        System.out.println("==========================================================================");
        System.out.println("Cảm ơn quý khách và hẹn gặp lại!");
    }
    // Hàm xuất ngắn gọn (Cập nhật để xuất Tên thay vì Mã)
    public void xuat() { // Hàm này dùng cho việc xuất danh sách
        // Giả định NhanVien có getHo() và getTen()
        String tenNV = (nv != null) ? (nv.getHo() + " " + nv.getTen()) : "Chưa gán";
        String tenKH = (makh != null) ? (makh.getHo() + " " + makh.getTen()) : "Chưa gán";

        System.out.printf("%-10s %-20s %-20s %-15s %,10d\n",
                mahoadon, tenNV, tenKH, ngaylaphd, tongtien); // SỬA ĐỔI
    }
}
//    public static void main(String[] args) {
//        NhanVien nv1 = new NhanVien();
//        nv1.setMaNV("NV001");
//        NhanVien nv2 = new NhanVien();
//        nv2.setMaNV("NV002");
//        KhachHang kh1 = new KhachHang();
//        kh1.setMaKH("KH01");
//        HoaDon hd = new HoaDon("HD001", nv1, kh1, "2024-06-01", 500000);
//        hd.tieude();
//        hd.xuat();
//    }

