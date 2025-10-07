public class NHANVIEN {
    private String maNV ,ho,ten;
    private int luongThang;
    public NHANVIEN(){
        maNV=" ";
        ho=" ";
        ten=" ";
        luongThang=0;
    }
    public NHANVIEN(String maNV,String ho,String ten,int luongThang){
        this.maNV=maNV;
        this.ho=ho;
        this.ten=ten;
        this.luongThang=luongThang;
    }
    public NHANVIEN(NHANVIEN a){
        maNV=a.maNV;
        ho=a.ho;
        ten=a.ten;
        luongThang=a.luongThang;
    }

    public String getHo() {
        return ho;
    }

    public String getMaNV() {
        return maNV;
    }

    public String getTen() {
        return ten;
    }

    public int getLuongThang() {
        return luongThang;
    }
}
