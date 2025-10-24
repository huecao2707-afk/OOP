import java.util.Scanner;

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
    public void setHo(String ho) {
        this.ho = ho;
    }
    public void setMaNV(String ma_nv) {
        this.ma_nv = ma_nv;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void setLuongThang(int luong_thang) {
        this.luong_thang = luong_thang;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma Nv: ");
        ma_nv=sc.nextLine();
        System.out.print("Ho: ");
        ho=sc.nextLine();
        System.out.print("Ten: ");
        ten=sc.nextLine();
        System.out.print("Luong thang: ");
        luong_thang=sc.nextInt();
        sc.nextLine();
    }
    public void xuat(){
        System.out.printf("%-15s %-25s %-10s\n",ma_nv,(ho + " " + ten),luong_thang);
    }

}
