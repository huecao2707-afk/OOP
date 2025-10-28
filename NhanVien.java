import java.util.Scanner;

public class NhanVien {
    private String manv ,ho,ten;
    private int luongthang;
    public NhanVien(){
        manv=" ";
        ho=" ";
        ten=" ";
        luongthang=0;
    }
    public NhanVien(String manv,String ho,String ten,int luongthang){
        this.manv=manv;
        this.ho=ho;
        this.ten=ten;
        this.luongthang=luongthang;
    }
    public NhanVien(NhanVien a){
        manv=a.manv;
        ho=a.ho;
        ten=a.ten;
        luongthang=a.luongthang;
    }

    public String getHo() {
        return ho;
    }

    public String getMaNV() {
        return manv;
    }

    public String getTen() {
        return ten;
    }

    public int getLuongThang() {
        return luongthang;
    }
    public void setHo(String ho) {
        this.ho = ho;
    }
    public void setMaNV(String manv) {
        this.manv = manv;
    }
    public void setTen(String ten) {
        this.ten = ten;
    }
    public void setLuongThang(int luongthang) {
        this.luongthang = luongthang;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma NV: ");
        manv=sc.nextLine();
        System.out.print("Ho: ");
        ho=sc.nextLine();
        System.out.print("Ten: ");
        ten=sc.nextLine();
        System.out.print("Luong thang: ");
        luongthang=sc.nextInt();
        sc.nextLine();
    }
    public void xuat(){
        System.out.printf("%-10s %-15s %-10s %-10d\n",manv,ho,ten,luongthang);
    }

}
