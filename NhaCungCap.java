import java.util.Scanner;
public class NhaCungCap {
    private String tenncc, diachi, mancc, dt, namthanhlap;

    public NhaCungCap() {
    }

    public NhaCungCap(String mancc, String tenncc, String diachi, String dt, String namthanhlap) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.dt = dt;
        this.namthanhlap = namthanhlap;
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
    public String getDT(){
        return dt;
    }
    public String getNamThanhLap(){
        return namthanhlap;
    }
    public void setmaNCC(String mancc){
        this.mancc = mancc;
    }
    public void setTenNCC(String tenncc){
        this.tenncc = tenncc;
    }
    public void setDiaChi(String diachi){
        this.diachi = diachi;
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
