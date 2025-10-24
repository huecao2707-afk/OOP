import java.util.Scanner;
public class PhieuNhanHang{
  private String ma_pnh;// ma phieu nhan hang
  private NhaCungCap ncc;
  private NhanVien nv;
  private String ngay;
  private int tong_tien;//
  
  public PhieuNhanHang(){}
  
  public PhieuNhanHang(String ma_pnh, NhanVien ma_nv, NhaCungCap ma_ncc, String ngay, int tong_tien){
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
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap ma phieu nhan hang: ");
    ma_pnh = sc.nextLine();
    this.nv = new NhanVien();
    nv.nhap();
    this.ncc = new NhaCungCap();
    ncc.nhap();
    System.out.println("Nhap ngay: ");
    ngay = sc.nextLine();
    
  }

  public void xuat(){
    System.out.printf("%-10s %-10s %-10s %-15s %-10d\n",ma_pnh,nv.getMaNV(),ncc.getMaNCC(),ngay,tong_tien);
  }
  
  public String getMaPNH(){
    return ma_pnh;
  }
  public NhanVien getNhanVien(){ // Lấy thông tin nhân viên
    return nv;
  }
  public String getNgay(){
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
