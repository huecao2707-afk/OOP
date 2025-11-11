import java.util.Scanner;

public class HoaDon {
    private NhanVien manv; // Kế thừa has a từ  class NhanVien
    private KhachHang makh; // Kế thừa has a từ  class KhachHang
    private String mahoadon, ngaylaphd;
    private int tongtien;
    private String maNV_string;
    private String maKH_string;
    public HoaDon(){
        tongtien=0;
    }
    public HoaDon(String mahoadon,NhanVien manv, KhachHang makh, String ngaylaphd, int tongtien){
        this.mahoadon=mahoadon;
        this.manv=manv;
        this.makh=makh;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
    public HoaDon(HoaDon x){
        this.mahoadon=x.mahoadon;
        this.manv=x.manv;
        this.makh=x.makh;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
    }

    public String getMaHoaDon() {
       return mahoadon;
   }

    public void setMaHoaDon(String mahoadon) {
        this.mahoadon = mahoadon;
    }
    
    public int getTongTien() {
        return tongtien;
    }
    
    public void setTongTien(int tongtien) {
    
        this.tongtien = tongtien;
    }
    
    public String getNgaylaphd() {
        return ngaylaphd;
    }
    
    public void setNgaylaphd(String ngaylaphd) {
        this.ngaylaphd = ngaylaphd;
    }
    
    public NhanVien getManv() {
        return manv;
    }
    
    public void setNhanVien(NhanVien nv) {
        this.manv = nv;
    }

    public KhachHang getMakh() {
        return makh;
    }
    
    public void setKhachHang(KhachHang makh) {
        this.makh = makh;
    }
    
    public String getMaNV_string() { 
        return maNV_string; 
    }
    
    public void setMaNV_string(String maNV_string) { 
        this.maNV_string = maNV_string; 
    }
    
    public String getMaKH_string() { 
        return maKH_string; 
    }
    
    public void setMaKH_string(String maKH_string) { 
        this.maKH_string = maKH_string; 
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

        String tenNV = (manv != null) ? (manv.getHo() + " " + manv.getTen()) : "Chưa gán"; // Giả định NV có getHo/getTen
        String tenKH = (makh != null) ? (makh.getHo() + " " + makh.getTen()) : "Chưa gán";
        String sdtKH = (makh != null) ? makh.getSoDT() : "N/A";

        System.out.println("\n==================================================================================================");
        System.out.println("======================================== PHIẾU THANH TOÁN ========================================");
        System.out.println("==================================================================================================");
        System.out.printf("| Mã Hóa Đơn: %-61s Ngày Lập: %s | \n", mahoadon, ngaylaphd);
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("Nhân Viên Lập: %-20s | Mã NV: %s%n", tenNV, manv.getMaNV());
        System.out.printf("Khách Hàng:    %-20s | Mã KH: %s%n", tenKH, makh.getMaKH());
        System.out.printf("Số Điện Thoại: %s%n", sdtKH);
        System.out.println("==================================================================================================");

        // TIÊU ĐỀ CHI TIẾT
        System.out.printf("| %-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n","STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
        System.out.println("--------------------------------------------------------------------------------------------------");

       // TÌM CÁC CTHD CÓ MÃ HÓA ĐƠN TRÙNG KHỚP VỚI mahoadon CỦA ĐỐI TƯỢNG NÀY
        ChiTietHoaDon[] cthd_cua_hd = QuanLyBanHang.dscthd.timCTHDTheoMaHD(this.mahoadon);
        int n_cthd_cua_hd = (cthd_cua_hd != null) ? cthd_cua_hd.length : 0;

        // IN CHI TIẾT HÓA ĐƠN ĐÃ TÌM ĐƯỢC
        for (int i = 0; i < n_cthd_cua_hd; i++) {
            cthd_cua_hd[i].xuatThongTinCT(i + 1);
        }

        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("| TỔNG CỘNG: %71s %,d VNĐ |\n", "", tongtien);
        System.out.println("==================================================================================================");
        System.out.println("Cảm ơn quý khách và hẹn gặp lại!");
    }
    // Hàm xuất ngắn gọn (Cập nhật để xuất Tên thay vì Mã)
    public void xuat() { // Hàm này dùng cho việc xuất danh sách
        String maNV_hien_thi = (manv != null) ? manv.getMaNV() : "N/A";        
        String maKH_hien_thi = (makh != null) ? makh.getMaKH() : "N/A";
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10s |\n" ,mahoadon, maNV_hien_thi, maKH_hien_thi, ngaylaphd, tongtien);
    }
}

