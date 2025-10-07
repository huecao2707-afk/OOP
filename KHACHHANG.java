public class KHACHHANG {
    private String maKH, ho, ten, diaChi, soDT;
    public KHACHHANG(){
        maKH="";
        ho="";
        ten="";
        diaChi="";
        soDT="";
    }
    public KHACHHANG(String maKH,String ho,String ten, String diaChi,String soDT){
        this.maKH=maKH;
        this.ho=ho;
        this.ten=ten;
        this.diaChi=diaChi;
        this.soDT=soDT;
    }
    public KHACHHANG(KHACHHANG a){
        maKH= a.maKH;
        ho=a.ho;
        ten=a.ten;
        diaChi=a.diaChi;
        soDT=a.soDT;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diaChi;
    }

    public String getMaKH() {
        return maKH;
    }

    public String getSoDT() {
        return soDT;
    }
}
