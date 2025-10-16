public class KhachHang {
    private String ma_kh, ho, ten, dia_chi, so_dt;
    public KhachHang(){
        ma_kh="";
        ho="";
        ten="";
        dia_chi="";
        so_dt="";
    }
    public KhachHang(String ma_kh,String ho,String ten, String dia_chi,String so_dt){
        this.ma_kh=ma_kh;
        this.ho=ho;
        this.ten=ten;
        this.dia_chi=dia_chi;
        this.so_dt=so_dt;
    }
    public KhachHang(KhachHang a){
        ma_kh= a.ma_kh;
        ho=a.ho;
        ten=a.ten;
        dia_chi=a.dia_chi;
        so_dt=a.so_dt;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return dia_chi;
    }

    public String getMaKH() {
        return ma_kh;
    }

    public String getSoDT() {
        return so_dt;
    }
}
