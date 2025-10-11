public class CHITIETPNH{ // Chi tiet phieu nhan hang
  private String ma_pnh;// ma phieu nhan hang
  private String ma_sp;
  private int don_gia, so_luong, thanh_tien; 

  public CHITIETPNH(){}
  public CHITIETPNH(String ma_pnh, String ma_sp, int don_gia, int so_luong, int thanh_tien){
    this.ma_pnh = ma_pnh;
    this.ma_sp = ma_sp;
    this.don_gia = don_gia;
    this.so_luong = so_luong;
    this.thanh_tien = thanh_tien;
  }
   public CHITIETPNH(CHITIETPNH x){
    ma_pnh = x.ma_pnh;
    ma_sp = x.ma_sp;
    don_gia = x.don_gia;
    so_luong = x.so_luong;
    thanh_tien = x.thanh_tien;
  }
}
