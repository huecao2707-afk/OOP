import java.util.Scanner;

public class HoaDon {
    private String mahoadon;
    private String manvstring;
    private String makhstring;
    private String ngaylaphd;
    private NhanVien nv; // Tham chiếu đối tượng Nhân viên
    private KhachHang kh; // Tham chiếu đối tượng Khách hàng
   
    private int tongtien;
    // Lưu tạm mã NV/KH dạng String khi đọc/ghi file, sẽ được liên kết sang object sau
    
    public HoaDon(){
        tongtien=0;
    }
    public HoaDon(String mahoadon,String manvstring, String makhstring, String ngaylaphd, int tongtien){
        this.mahoadon=mahoadon;
        this.manvstring=manvstring;
        this.makhstring=makhstring;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
   public HoaDon(HoaDon x){
        this.mahoadon=x.mahoadon;
        this.manvstring=x.manvstring;
        this.makhstring=x.makhstring;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
        this.nv = x.nv;       // Sao chép đối tượng Nhân viên
        this.kh = x.kh;       // Sao chép đối tượng Khách hàng
    }
        public void nhap() {
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhập Mã hóa đơn: ");
            mahoadon = sc.nextLine();
            System.out.print("Nhập Ngày lập HĐ (VD: dd/mm/yyyy): ");
            ngaylaphd = sc.nextLine();
        }
        void xuatHoaDonDayDu() {
            System.out.println("\n==================================================================================================");
            System.out.println("======================================== PHIẾU THANH TOÁN ========================================");
            System.out.println("==================================================================================================");
            System.out.printf("| Mã Hóa Đơn: %-61s Ngày Lập: %s | \n", mahoadon, ngaylaphd);
            System.out.println("--------------------------------------------------------------------------------------------------");
            String tennv = (nv != null) ? (nv.getHo() + " " + nv.getTen()) : "Chưa gán";
            String tenkh = (kh != null) ? (kh.getHo() + " " + kh.getTen()) : "Chưa gán";
            String manvhienthi = (nv != null) ? nv.getMaNV() : "N/A";
            String makhhienthi = (kh != null) ? kh.getMaKH() : "N/A";
            String sdtKH = (kh != null) ? kh.getSoDT() : "N/A";
            System.out.printf("Nhân Viên Lập: %-20s | Mã NV: %s%n", tennv, manvhienthi);
            System.out.printf("Khách Hàng:    %-20s | Mã KH: %s%n", tenkh, makhhienthi );
            System.out.printf("Số Điện Thoại: %s%n", sdtKH);
            System.out.println("==================================================================================================");
        //Xuất chi tiết hóa đơn
            System.out.printf("| %-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n","STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
            System.out.println("--------------------------------------------------------------------------------------------------");
            // Lấy chi tiết theo mã hóa đơn
            ChiTietHoaDon[] cthdcuahoadon  = QuanLyBanHang.dscthd.timCTHDTheoMaHD(this.mahoadon);
            int soluongcthd = (cthdcuahoadon != null) ? cthdcuahoadon.length : 0;
            for (int i = 0; i < soluongcthd; i++) {
                cthdcuahoadon[i].xuatThongTinCT(i + 1);
            }
            System.out.println("--------------------------------------------------------------------------------------------------");
            System.out.printf("| TỔNG CỘNG: %71s %,d VNĐ |\n", "", tongtien);
            System.out.println("==================================================================================================");
            System.out.println("Cảm ơn quý khách và hẹn gặp lại!");
        }
        // Hàm xuất ngắn gọn (Cập nhật để xuất Tên thay vì Mã)
        public void xuat() { // Hàm này dùng cho việc xuất danh sách
            String manvhienthi = (nv != null) ? nv.getMaNV() : "N/A";
            String makhhienthi = (kh != null) ? kh.getMaKH() : "N/A";
            System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10s |\n" ,mahoadon, manvhienthi, makhhienthi, ngaylaphd, tongtien);
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
        public void setTongTien(int tongtien) {this.tongtien = tongtien;}
        public String getNgayLapHD() { return ngaylaphd; }
        public void setNgayLapHD(String ngaylaphd) {
            this.ngaylaphd = ngaylaphd;
        }
        public NhanVien getNhanVien() { return nv; }
        public void setNhanVien(NhanVien nv) { this.nv = nv; }
        public KhachHang getKhachHang() { return kh; }
        public void setKhachHang(KhachHang kh) { this.kh = kh; }
        public String getMaKH(){
            return makhstring;
        }
        public void setMaKH(String makhstring){
            this.makhstring = makhstring;
        }
        public void setMaNhanVien(String manvstring){
            this.manvstring = manvstring;
        }
        public String getMaNhanVien(){
            return manvstring;
        }

}

