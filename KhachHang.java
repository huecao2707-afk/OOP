import java.util.Scanner;
public class KhachHang {
    private String makh, ho, ten, diachi, sodt;
    public KhachHang(){
        makh="";
        ho="";
        ten="";
        diachi="";
        sodt="";
    }
    public KhachHang(String makh,String ho,String ten, String diachi,String sodt){
        this.makh=makh;
        this.ho=ho;
        this.ten=ten;
        this.diachi=diachi;
        this.sodt=sodt;
    }
    public KhachHang(KhachHang a){
        makh= a.makh;
        ho=a.ho;
        ten=a.ten;
        diachi=a.diachi;
        sodt=a.sodt;
    }

    public String getHo() {
        return ho;
    }

    public String getTen() {
        return ten;
    }

    public String getDiaChi() {
        return diachi;
    }

    public String getMaKH() {
        return makh;
    }

    public String getSoDT() {
        return sodt;
    }

    public void setDiaChi(String diachi) {
        this.diachi = diachi;
    }

    public void setHo(String ho) {
        this.ho = ho;
    }

    public void setMaKH(String makh) {
        this.makh = makh;
    }

    public void setSoDT(String sodt) {
        this.sodt = sodt;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Ma khach hang: ");
        makh = sc.nextLine();
        System.out.print("Ho: ");
        ho = sc.nextLine();
        System.out.print("Ten: ");
        ten = sc.nextLine();
        System.out.print("Dia chi: ");
        diachi = sc.nextLine();
        System.out.print("SDT: ");
        sodt = sc.nextLine();
    }
    public void xuat(){
        System.out.printf("| %-15s | %-25s | %-15s | %-24s | \n",makh,(ho + " " + ten),sodt, diachi);

    }
}

