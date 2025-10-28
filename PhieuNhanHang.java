import java.util.Scanner;
public class PhieuNhanHang{
  private String mapnh;// ma phieu nhan hang
  private NhaCungCap ncc;
  private NhanVien nv;
  private String ngay;
  private int tongtien;//
  
  public PhieuNhanHang(){}
  
  public PhieuNhanHang(String mapnh, NhanVien manv, NhaCungCap mancc, String ngay, int tongtien){
    this.mapnh = mapnh;
    this.nv = manv;
    this.ncc = mancc;
    this.ngay = ngay;
    this.tongtien = tongtien;
  }
  
  public PhieuNhanHang(PhieuNhanHang x){
    mapnh = x.mapnh;
    nv = x.nv;
    ncc = x.ncc;
    ngay = x.ngay;
    tongtien = x.tongtien;
  }
//  public void nhap(){
//    Scanner sc = new Scanner(System.in);
//    System.out.println("Nhap ma phieu nhan hang: ");
//    mapnh = sc.nextLine();
//    this.nv = new NhanVien();
//    nv.nhap();
//    this.ncc = new NhaCungCap();
//    ncc.nhap();
//    System.out.println("Nhap ngay: ");
//    ngay = sc.nextLine();
//
//  }

  public void xuat(){
    System.out.printf("%-10s %-10s %-10s %-15s %-10d\n",mapnh,nv.getMaNV(),ncc.getMaNCC(),ngay,tongtien);
  }
  
  public String getMaPNH(){
    return mapnh;
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
    return tongtien;
  }
  
  public void setMaPNH(String mapnh){
    this.mapnh = mapnh;
  }
  public void setTongTien(int tongtien){
    this.tongtien = tongtien;
  }
}
