import java.time.LocalDate;
import java.util.Scanner;
public class PhieuNhanHang{
  private String ma_pnh;// ma phieu nhan hang
  private NhaCungCap ncc;
  private NhanVien nv;
  private LocalDate ngay;
  private int tong_tien;//
  
  public PhieuNhanHang(){}
  
  public PhieuNhanHang(String ma_pnh, NhanVien ma_nv, NhaCungCap ma_ncc, LocalDate ngay, int tong_tien){
    this.ma_pnh = ma_pnh;
    this.nv = ma_nv;
    this.ncc = ma_ncc;
    this.ngay = ngay;
    this.tong_tien = tong_tien;
  }
  
  public PhieuNhanHang(PhieuNhanHang x){
    ma_pnh = x.ma_pnh;
    nv = x.nv;
    ncc = x.ncc;
    ngay = x.ngay;
    tong_tien = x.tong_tien;
  }
//  public void nhap(){
//    Scanner sc = new Scanner(System.in);
//    System.out.println("Nhap ma phieu nhan hang: ");
//    ma_pnh = sc.nextLine();
//    ngay = LocalDate.now();
//  }
    public void nhap(DSNV ds_nv, DSNCC ds_ncc) {
        Scanner sc = new Scanner(System.in);
        String ma_nv_temp, ma_ncc_temp;

        // 1. Nhập Mã Phiếu Nhận Hàng và Ngày
        System.out.println("Nhap ma phieu nhan hang: ");
        ma_pnh = sc.nextLine();
        ngay = LocalDate.now(); // Lấy ngày hiện tại

        // 2. Nhập và tìm kiếm Nhân Viên
        do {
            System.out.print("Nhap ma nhan vien (NV): ");
            ma_nv_temp = sc.nextLine();
            nv = ds_nv.timKiemTheoMa(ma_nv_temp);
            if (nv == null) {
                System.out.println("(!) Ma nhan vien khong ton tai. Vui long nhap lai.");
            }
        } while (nv == null);

        // 3. Nhập và tìm kiếm Nhà Cung Cấp
        do {
            System.out.print("Nhap ma nha cung cap (NCC): ");
            ma_ncc_temp = sc.nextLine();
            ncc = ds_ncc.timKiemTheoMa(ma_ncc_temp);
            if (ncc == null) {
                System.out.println("(!) Ma nha cung cap khong ton tai. Vui long nhap lai.");
            }
        } while (ncc == null);
    }
  public void xuat(){
    System.out.printf("%-20s %-30s %-20s %-15s %-20d\n",ma_pnh,nv.getHo() + " " + nv.getTen(),ncc.getTenNCC(),ngay,tong_tien);
  }
  
  public String getMaPNH(){
    return ma_pnh;
  }

  public NhanVien getNhanVien(){ // Lấy thông tin nhân viên
    return nv;
  }

  public LocalDate getNgay(){
    return ngay;
  }

  public NhaCungCap getNhaCungCap(){ // Lấy thông tin nhà cung cấp
    return ncc;
  }

  public int getTongTien(){
    return tong_tien;
  }
  
  public void setMaPNH(String ma_pnh){
    this.ma_pnh = ma_pnh;
  }

  public void setTongTien(int tong_tien){
    this.tong_tien = tong_tien;
  }
}
