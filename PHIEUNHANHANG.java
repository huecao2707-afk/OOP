public class PHIEUNHANHANG{
  private String ma_pnh;// ma phieu nhan hang
  private String ma_nv, ma_ncc, ngay;
  private int tong_tien;//
  
  public PHIEUNHANHANG(){}
  
  public PHIEUNHANHANG(String ma_pnh, String ma_nv, String ma_ncc, String ngay, int tong_tien){
    this.ma_pnh = ma_pnh;
    this.ma_nv = ma_nv;
    this.ma_ncc = ma_ncc;
    this.ngay = ngay;
    this.tong_tien = tong_tien;
  }
  
  public PHIEUNHANHANG(PHIEUNHANHANG x){
    ma_pnh = x.ma_pnh;
    ma_nv = x.ma_nv;
    ma_ncc = x.ma_ncc;
    ngay = x.ngay;
    tong_tien = x.tong_tien;
  }
  public void nhap(){
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
