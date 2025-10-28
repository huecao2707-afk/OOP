import java.util.Scanner;
public class NhaCungCap {
    private String ten_ncc, dia_chi, ma_ncc;

    public NhaCungCap() {
        ma_ncc = "";
        ten_ncc = "";
        dia_chi = "";
    }

    public NhaCungCap(String ma_ncc, String ten_ncc, String dia_chi) {
        this.ma_ncc = ma_ncc;
        this.ten_ncc = ten_ncc;
        this.dia_chi = dia_chi;
    }

    public NhaCungCap(NhaCungCap a) {
        ma_ncc = a.ma_ncc;
        ten_ncc = a.ten_ncc;
        dia_chi = a.dia_chi;
    }

    public String getMaNCC() {
        return ma_ncc;
    }

    public String getTenNCC() {
        return ten_ncc;
    }

    public String getDiaChi() {
        return dia_chi;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma NCC: ");
        ma_ncc=sc.nextLine();
        System.out.print("Ten NCC: ");
        ten_ncc=sc.nextLine();
        System.out.print("Dia chi: ");
        dia_chi=sc.nextLine();
    }
    public void xuat(){
        System.out.printf("%-20s %-20s %-20s\n",ma_ncc,ten_ncc,dia_chi);
    }
}
