import java.util.Scanner;
public class NhaCungCap {
    private String tenncc, diachi, mancc, sdt, namthanhlap;

    public NhaCungCap() {
    }

    public NhaCungCap(String mancc, String tenncc, String diachi, String sdt, String namthanhlap) {
        this.mancc = mancc;
        this.tenncc = tenncc;
        this.diachi = diachi;
        this.sdt = sdt;
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
    
    public void setmaNCC(String mancc){
        this.mancc = mancc;
    }

    public String getTenNCC() {
        return tenncc;
    }

    public void setTenNCC(String tenncc){
        this.tenncc = tenncc;
    }

    public String getDiaChi() {
        return diachi;
    }
    
    public void setDiaChi(String diachi){
        this.diachi = diachi;
    }
    
    public String getSDT(){
        return sdt;
    }
    
    public void setSDT(String sdt){
        this.sdt = sdt;
    }
    
    public String getNamThanhLap(){
        return namthanhlap;
    }
    
    public void setNamthanhlap(String namthanhlap) {
        this.namthanhlap = namthanhlap;
    }
    
    public void nhap(){
        Scanner sc = new Scanner(System.in);
        System.out.print("Mã nhà cung cấp: ");
        mancc = sc.nextLine();
        System.out.print("Tên nhà cung cấp: ");
        tenncc = sc.nextLine();
        System.out.print("Số điện thoại: ");
        sdt = sc.nextLine();
        System.out.print("Năm thành lập: ");
        namthanhlap = sc.nextLine();
        System.out.print("Địa chỉ: ");
        diachi = sc.nextLine();
    }
    public void xuat(){
        System.out.printf("| %-15s | %-20s | %-15s | %-15s | %-20s |\n",mancc,tenncc,sdt,namthanhlap,diachi);
    }
}