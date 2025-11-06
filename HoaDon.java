import java.util.Scanner;

public class HoaDon {
    private String mahd;
    private NhanVien manv; // Kế thừa has a từ  class NhanVien
    private KhachHang makh; // Kế thừa has a từ  class KhachHang
    private String ngaylaphd;
    private int tongtien;
    public HoaDon(){
    }
    public HoaDon(String mahd,NhanVien manv, KhachHang makh, String ngaylaphd, int tongtien){
        this.mahd=mahd;
        this.manv=manv;
        this.makh=makh;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
    public HoaDon(HoaDon x){
        this.mahd=x.mahd;
        this.manv=x.manv;
        this.makh=x.makh;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
    }

   public String getMaHoaDon() {
       return mahd;
   }

    public int getTongTien() {
        return tongtien;
    }

    public void setmahd(String mahd) {
        this.mahd = mahd;
    }
    public void setNhanVien(NhanVien manv) {
        this.manv = manv;
    }
    // public void setTongTien(int tongtien) {
    //     this.tongtien = tongtien;
    // }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma hoa don: ");
        mahd=sc.nextLine();
        this.makh = new KhachHang();
        makh.nhap();
        System.out.print("Ngay lap hoa don: ");
        ngaylaphd=sc.nextLine();
        System.out.print("Tong tien: ");
        tongtien=sc.nextInt();
    }
    // public void tieude(){
        
    // }
    public void xuat(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
        System.out.printf("%-10s %-10s %-10s %-15s %-10d\n",mahd,manv.getMaNV(),makh.getMaKH(),ngaylaphd,tongtien);
    }
   // public static void main(String[] args) {
   //     NhanVien nv1 = new NhanVien();
   //     nv1.setMaNV("NV001");
   //     NhanVien nv2 = new NhanVien();
   //     nv2.setMaNV("NV002");
   //     KhachHang kh1 = new KhachHang();
   //     kh1.setMaKH("KH01");
   //     HoaDon hd = new HoaDon("HD001", nv1, kh1, "2024-06-01", 500000);
   //     hd.xuat();
   // }
}
