import java.util.Scanner;

public class PhieuNhapHang {
    private String mapnh;
    private String manv; // Lưu mã NV String để ghi file
    private String mancc; // Lưu mã NCC String để ghi file
    private String ngaynhan;
    private int tongtien;

    // Đối tượng để gán sau khi liên kết (Lookup)
    private NhanVien nv;
    private NhaCungCap ncc; // Giả định có lớp NhaCungCap

    public PhieuNhapHang(){
        tongtien = 0;
    }

    public PhieuNhapHang(String mapnh, String manv, String mancc, String ngaynhan, int tongtien){
        this.mapnh = mapnh;
        this.manv = manv;
        this.mancc = mancc;
        this.ngaynhan = ngaynhan;
        this.tongtien = tongtien;
    }

    public PhieuNhapHang(PhieuNhapHang x){
        this.mapnh = x.mapnh;
        this.manv = x.manv;
        this.mancc = x.mancc;
        this.ngaynhan = x.ngaynhan;
        this.tongtien = x.tongtien;
        this.nv = x.nv;       // Sao chép đối tượng Nhân viên
        this.ncc = x.ncc;     // Sao chép đối tượng Nhà Cung Cấp
    }

    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Mã Phiếu Nhận Hàng: ");
        mapnh = sc.nextLine();
        System.out.print("Nhập Ngày Nhận (VD: dd/mm/yyyy): ");
        ngaynhan = sc.nextLine();
    }

    // Hàm xuất ngắn gọn (dùng cho danh sách)
    public void xuat() {
        String manvhienthi = (nv != null) ? nv.getMaNV() : "N/A";
        String mancchienthi = (ncc != null) ? ncc.getMaNCC() : "N/A"; // Giả định NhaCungCap có getMaNCC()
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10d |\n" ,
                mapnh, manvhienthi, mancchienthi, ngaynhan, tongtien);
    }

    // Hàm xuất đầy đủ (tương tự xuatHoaDonDayDu)
    public void xuatPhieuDayDu() {
        System.out.println("\n==================================================================================================");
        System.out.println("====================================== PHIẾU NHẬN HÀNG ===========================================");
        System.out.println("==================================================================================================");
        System.out.printf("| Mã Phiếu: %-63s Ngày Nhận: %s | \n", mapnh, ngaynhan);
        System.out.println("--------------------------------------------------------------------------------------------------");

        String tennv = (nv != null) ? (nv.getHo() + " " + nv.getTen()) : "Chưa gán";
        String tenncc = (ncc != null) ? (ncc.getTenNCC()) : "Chưa gán"; // Giả định NhaCungCap có getTenNCC()
        String manvhienthi = (nv != null) ? nv.getMaNV() : "N/A";
        String mancchienthi = (ncc != null) ? ncc.getMaNCC() : "N/A";

        System.out.printf("Nhân Viên Lập: %-20s | Mã NV: %s%n", tennv, manvhienthi);
        System.out.printf("Nhà Cung Cấp: %-20s | Mã NCC: %s%n", tenncc, mancchienthi);
        System.out.println("==================================================================================================");

        // Xuất chi tiết phiếu nhận hàng
        System.out.printf("| %-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n","STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
        System.out.println("--------------------------------------------------------------------------------------------------");

        // Lấy chi tiết theo mã PNH (Giả định QuanLyBanHang.dsctpnh đã được tạo)
        ChiTietPNH[] ctpnhcuaphieu = QuanLyBanHang.dsctpnh.timCTPNHTheoMaPNH(this.mapnh);
        int soluongctpnh = (ctpnhcuaphieu != null) ? ctpnhcuaphieu.length : 0;

        for (int i = 0; i < soluongctpnh; i++) {
            // Cần hàm xuatThongTinCT() trong ChiTietPNH
            ctpnhcuaphieu[i].xuatThongTinCT(i + 1);
        }
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("| TỔNG CỘNG: %71s %,d VNĐ |\n", "", tongtien);
        System.out.println("==================================================================================================");
    }

    // Getters/Setters
    public String getMaPNH() { return mapnh; }
    public void setMaPNH(String mapnh) { this.mapnh = mapnh; }
    public String getManv() { return manv; }
    public void setManv(String manv) { this.manv = manv; }
    public String getMancc() { return mancc; }
    public void setMancc(String mancc) { this.mancc = mancc; }
    public String getNgaynhan() { return ngaynhan; }
    public void setNgaynhan(String ngaynhan) { this.ngaynhan = ngaynhan; }
    public int getTongTien() { return tongtien; }
    public void setTongTien(int tongtien) { this.tongtien = tongtien; }
    public NhanVien getNhanVien() { return nv; }
    public void setNhanVien(NhanVien nv) { this.nv = nv; }
    public NhaCungCap getNhaCungCap() { return ncc; }
    public void setNhaCungCap(NhaCungCap ncc) { this.ncc = ncc; }
}