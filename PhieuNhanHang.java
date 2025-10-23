public class PhieuNhanHang{
  private String ma_pnh;// ma phieu nhan hang
  private String ma_nv, ma_ncc, ngay;
  private int tong_tien;//
  
  public PhieuNhanHang(){}
  
  public PhieuNhanHang(String ma_pnh, String ma_nv, String ma_ncc, String ngay, int tong_tien){
    this.ma_pnh = ma_pnh;
    this.ma_nv = ma_nv;
    this.ma_ncc = ma_ncc;
    this.ngay = ngay;
    this.tong_tien = tong_tien;
  }
  
  public PhieuNhanHang(PhieuNhanHang x){
    ma_pnh = x.ma_pnh;
    ma_nv = x.ma_nv;
    ma_ncc = x.ma_ncc;
    ngay = x.ngay;
    tong_tien = x.tong_tien;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap ma phieu nhan hang: ");
    ma_pnh = sc.nextLine();
    System.out.println("Nhap ma nhan vien: ");
    ma_nv = sc.nextLine();
    System.out.println("Nhap ma nha cung cap: ");
    ma_ncc = sc.nextLine();
    System.out.println("Nhap ngay: ");
    ngay = sc.nextLine();
    
  }
  public void xuat(){
  }
  
  public String getMaPNH(){
    return ma_pnh;
  }
  public String getMaNV(){
    return ma_nv;
  }
  public String getMaNCC(){
    return ma_ncc;
  }
  public String getNgay(){
    return ngay;
  }
  public int getTongTien(){
    return tong_tien;
  }
  
  public void setTongTien(int tong_tien){
    this.tong_tien = tong_tien;
  }
}
