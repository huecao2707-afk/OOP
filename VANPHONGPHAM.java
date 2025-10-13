public class VANPHONGPHAM {
    private int don_gia,so_luong;
    private String ma_sp, ten_sp,don_vi_tinh;
    public VANPHONGPHAM(){
        don_gia = 0;
        so_luong = 0;
        ma_sp = "";
        ten_sp = "";
        don_vi_tinh = "";
    }
    public VANPHONGPHAM(int don_gia, int so_luong, String ma_sp, String ten_sp, String don_vi_tinh){
        this.don_gia = don_gia;
        this.so_luong = so_luong;
        this.ma_sp = ma_sp;
        this.ten_sp = ten_sp;
        this.don_vi_tinh = don_vi_tinh;
    }
    public  VANPHONGPHAM(VANPHONGPHAM x){
        this.don_gia = x.don_gia;
        this.so_luong = x.so_luong;
        this.ma_sp = x.ma_sp;
        this.ten_sp = x.ten_sp;
        this.don_vi_tinh = x.don_vi_tinh;
    }

    public int getDon_gia() {
        return don_gia;
    }

    public int getSo_luong() {
        return so_luong;
    }

    public String getDon_vi_tinh() {
        return don_vi_tinh;
    }

    public String getMa_sp() {
        return ma_sp;
    }

    public String getTen_sp() {
        return ten_sp;
    }

    public void setDon_gia(int don_gia) {
        this.don_gia = don_gia;
    }

    public void setDon_vi_tinh(String don_vi_tinh) {
        this.don_vi_tinh = don_vi_tinh;
    }

    public void setMa_sp(String ma_sp) {
        this.ma_sp = ma_sp;
    }

    public void setSo_luong(int so_luong) {
        this.so_luong = so_luong;
    }

    public void setTen_sp(String ten_sp) {
        this.ten_sp = ten_sp;
    }

}
