public class DONHANG {
    private String maDonHang;
    private int tongTien;
    public DONHANG(){
        maDonHang = "";
        tongTien = 0;
    }
    public DONHANG(String maDonHang, int tongTien){
        this.maDonHang = maDonHang;
        this.tongTien = tongTien;
    }
    public DONHANG(DONHANG x){
        x.maDonHang = maDonHang;
        x.tongTien = tongTien;
    }

    public String getMaDonHang() {
        return maDonHang;
    }

    public int getTongTien() {
        return tongTien;
    }

    public void setMaDonHang(String maDonHang) {
        this.maDonHang = maDonHang;
    }

    public void setTongTien(int tongTien) {
        this.tongTien = tongTien;
    }
}
