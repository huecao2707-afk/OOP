public class HOADON {
    private String ma_hoa_don;
    private int tong_tien;
    public HOADON(){
        ma_hoa_don = "";
        tong_tien = 0;
    }
    public HOADON(String ma_hoa_don, int tong_tien){
        this.ma_hoa_don = ma_hoa_don;
        this.tong_tien = tong_tien;
    }
    public HOADON(HOADON x){
        x.ma_hoa_don = ma_hoa_don;
        x.tong_tien = tong_tien;
    }

   public String getMaHoaDon() {
       return ma_hoa_don;
   }

    public int getTongTien() {
        return tong_tien;
    }

    public void setMaHoaDon(String ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }

    public void setTongTien(int tong_tien) {
        this.tong_tien = tong_tien;
    }
}
