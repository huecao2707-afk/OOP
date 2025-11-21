import java.util.Scanner;
import java.time.LocalDate; 
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
public class HoaDon {
    private String mahd;
    private String manv;
    private String makh;
    private LocalDate ngaylaphd;
    private int tongtien;
    private static final DateTimeFormatter DATE_FORMATTER = DateTimeFormatter.ofPattern("dd/MM/yyyy");
    
    public HoaDon(){
        tongtien=0;
    }
    public HoaDon(String mahd,String manv, String makh, LocalDate ngaylaphd, int tongtien){
        this.mahd=mahd;
        this.manv=manv;
        this.makh=makh;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
    public HoaDon(HoaDon x){
        this.mahd=x.mahd;
        this.manv=x.manv;
        this.makh=x.makh;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
    }

    public String getMaHD() { 
        return mahd; 
    }
    public String getMaNV(){ 
        return manv; 
    }
    public String getMaKH(){ 
        return makh; 
    }
    public int getTongTien() { 
        return tongtien; 
    }
    public LocalDate getNgayLapHD() { 
        return ngaylaphd; 
    }
    public String getNgayLapHDString() { 
        if (ngaylaphd != null) {
            return ngaylaphd.format(DATE_FORMATTER);
        }
        return "N/A";
    }

    public void setMaHD(String mahd) {
        this.mahd = mahd;
    }
    public void setMaNV(String manv){
        this.manv = manv;
    }
    public void setMaKH(String makh){
        this.makh = makh;
    }
    public void setNgayLapHD(LocalDate ngaylaphd) { 
        this.ngaylaphd = ngaylaphd; 
    }
    public void setTongTien(int tongtien){
        this.tongtien = tongtien;
    }

    public void nhap() { 
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập Mã hóa đơn: ");
        mahd = sc.nextLine();
        while(true){
            System.out.print("Nhập Ngày lập HĐ (VD: dd/mm/yyyy): ");
            String ngaystr = sc.nextLine();
            try{
                this.ngaylaphd = LocalDate.parse(ngaystr, DATE_FORMATTER);
                break;
            }
            catch(DateTimeParseException e){
                System.out.println("Lỗi: Ngày nhập không đúng định dạng. Vui lòng nhập lại.");
            }
        }
    }

    void xuatHoaDonDayDu() {
        System.out.println("\n==================================================================================================");
        System.out.println("======================================== PHIẾU THANH TOÁN ========================================");
        System.out.println("==================================================================================================");
        System.out.printf("| Mã Hóa Đơn: %-61s Ngày Lập: %s | \n", mahd, getNgayLapHDString());
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("Mã Nhân Viên Lập: %s%n", manv);
        System.out.printf("Mã Khách Hàng:    %s%n", makh);
        System.out.println("==================================================================================================");
        System.out.printf("| %-5s | %-10s | %-30s | %-10s | %-9s | %-15s |\n","STT", "Mã SP", "Tên Sản Phẩm", "Đơn Giá", "Số Lượng", "Thành Tiền");
        System.out.println("--------------------------------------------------------------------------------------------------");
        ChiTietHoaDon[] cthdcuahoadon  = QuanLyBanHang.dscthd.timCTHDTheoMaHD(this.mahd);
        int soluongcthd = (cthdcuahoadon != null) ? cthdcuahoadon.length : 0;
        for (int i = 0; i < soluongcthd; i++) {
            cthdcuahoadon[i].xuatThongTinCT(i + 1);
        }
        String tongtienda = String.format("%,d VNĐ", tongtien);
        System.out.println("--------------------------------------------------------------------------------------------------");
        System.out.printf("| %-74s %19s |\n", "TỔNG CỘNG:", tongtienda);
        System.out.println("==================================================================================================");
    }
    public void xuat() {
        System.out.printf("| %-10s | %-10s | %-10s | %-15s | %-10s |\n" ,mahd, manv, makh, getNgayLapHDString(), tongtien);
    }
}