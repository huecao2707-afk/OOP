import java.util.Scanner;
public class NhaCungCap {
    private String tenncc, diachi, mancc;

    public NhaCungCap() {
        mancc = "";
        tenncc = "";
        diachi = "";
    }

    public NhaCungCap(String mancc, String tenncc, String diachi) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
    }

    public NhaCungCap(NhaCungCap a) {
        mancc = a.mancc;
        tenncc = a.tenncc;
        diachi = a.diachi;
    }

    public String getMaNCC() {
        return mancc;
    }

    public String getTenNCC() {
        return tenncc;
    }

    public String getDiaChi() {
        return diachi;
    }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma NCC: ");
        mancc=sc.nextLine();
        System.out.print("Ten NCC: ");
        tenncc=sc.nextLine();
        System.out.print("Dia chi: ");
        diachi=sc.nextLine();
    }
    public void xuat(){
        System.out.printf("%-20s %-20s %-20s\n",mancc,tenncc,diachi);
    }
}
