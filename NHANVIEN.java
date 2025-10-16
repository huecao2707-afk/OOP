public class NhanVien {
    private String ma_nv ,ho,ten;
    private int luong_thang;
    public NhanVien(){
        ma_nv=" ";
        ho=" ";
        ten=" ";
        luong_thang=0;
    }
    public NhanVien(String ma_nv,String ho,String ten,int luong_thang){
        this.ma_nv=ma_nv;
        this.ho=ho;
        this.ten=ten;
        this.luong_thang=luong_thang;
    }
    public NhanVien(NhanVien a){
        ma_nv=a.ma_nv;
        ho=a.ho;
        ten=a.ten;
        luong_thang=a.luong_thang;
    }

    public String getHo() {
        return ho;
    }

    public String getMaNV() {
        return ma_nv;
    }

    public String getTen() {
        return ten;
    }

    public int getLuongThang() {
        return luong_thang;
    }
}
