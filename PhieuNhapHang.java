import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class PhieuNhapHang {
    private String mapnh;
    private String manv;
    private String mancc;
    private LocalDate ngaynhan;
    private int tongtien;

    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");

    public PhieuNhapHang(){
        tongtien = 0;
    }

    public PhieuNhapHang(String mapnh, String manv, String mancc, LocalDate ngaynhan, int tongtien){
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
    }
    public void nhap() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Mã Phiếu Nhận Hàng: ");
        mapnh = sc.nextLine();
        while(true){
            System.out.print("Nhập Ngày Nhận (VD: dd/mm/yyyy): ");
            String ngaystr = sc.nextLine();
            try{
                this.ngaynhan = LocalDate.parse(ngaystr, DATE_FORMATTER);
                break;
            }
            catch(DateTimeParseException e){
                System.out.println("Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }
    }

    public void xuat() {
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10d |\n" ,
                mapnh, manv, mancc, getNgayNhanString(), tongtien);
    }
    public void xuatPhieuDayDu() {
        System.out.println("\n==================================================================================================");
        System.out.println("====================================== PHIẾU NHẬP HÀNG ===========================================");
        System.out.println("==================================================================================================");
        
        System.out.printf("| Mã Phiếu: %-62s Ngày Nhận: %s | \n", mapnh, getNgayNhanString());
        System.out.println("--------------------------------------------------------------------------------------------------");

        String tennv = "N/A";
        String tenncc = "N/A";
        
        NhanVien nvtamthoi = QuanLyBanHang.dsnv.timKiemTheoMa(this.manv);
        if (nvtamthoi != null) tennv = nvtamthoi.getHo() + " " + nvtamthoi.getTen();
        
        NhaCungCap ncc_temp = QuanLyBanHang.dsncc.timKiemTheoMa(this.mancc);
        if (ncc_temp != null) tenncc = ncc_temp.getTenNCC();

        System.out.printf("Nhân Viên Lập: %-20s | Mã NV: %s%n", tennv, this.manv);
        System.out.printf("Nhà Cung Cấp: %-20s | Mã NCC: %s%n", tenncc, this.mancc);
        System.out.println("==================================================================================================");

        System.out.printf("| %-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n","STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
        System.out.println("--------------------------------------------------------------------------------------------------");

        ChiTietPNH[] ctpnhcuaphieu = QuanLyBanHang.dsctpnh.timCTPNHTheoMaPNH(this.mapnh);
        int soluongctpnh = (ctpnhcuaphieu != null) ? ctpnhcuaphieu.length : 0;

        for (int i = 0; i < soluongctpnh; i++) {
            ctpnhcuaphieu[i].xuatThongTinCT(i + 1);
        }
        String tongtienda = String.format("%,d VNĐ", tongtien);
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("| %-74s %19s |\n", "TỔNG CỘNG:", tongtienda);
        System.out.println("==================================================================================================");
    }

    public String getMaPNH() { return mapnh; }
    public void setMaPNH(String mapnh) { this.mapnh = mapnh; }
    public String getMaNV() { return manv; }
    public void setMaNV(String manv) { this.manv = manv; }
    public String getMaNCC() { return mancc; }
    public void setMaNCC(String mancc) { this.mancc = mancc; }
    
    public LocalDate getNgayNhan() { return ngaynhan; }
    public void setNgayNhan(LocalDate ngaynhan) { this.ngaynhan = ngaynhan; }

    public String getNgayNhanString() {
        if (ngaynhan != null) {
            return ngaynhan.format(DATE_FORMATTER);
        }
        return "N/A";
    }

    public int getTongTien() { return tongtien; }
    public void setTongTien(int tongtien) { this.tongtien = tongtien; }
    
}