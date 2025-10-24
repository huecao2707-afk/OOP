import java.util.Scanner;
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
    public void setDiaChi(String dia_chi) {
        this.dia_chi = dia_chi;
    }
    public void setHo(String ho) {
        this.ho = ho;
    }
    public void setMaKH(String ma_kh) {
        this.ma_kh = ma_kh;
    }
    public void setSoDT(String so_dt) {
        this.so_dt = so_dt;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma khach hang: ");
        ma_kh = sc.nextLine();
        System.out.print("Ho: ");
        ho = sc.nextLine();
        System.out.print("Ten: ");
        ten = sc.nextLine();
        System.out.print("Dia chi: ");
        dia_chi = sc.nextLine();
        System.out.print("SDT: ");
        so_dt = sc.nextLine();
    }
    public void xuat(){
        System.out.println("Ma khach hang: " + ma_kh);
        System.out.println("Ho: " + ho);
        System.out.println("Ten: " + ten);
        System.out.println("Dia chi: " + dia_chi);
        System.out.println("SDT: " + so_dt);
    }
}

