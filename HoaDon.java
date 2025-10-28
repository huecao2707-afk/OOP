import java.util.Scanner;

public class HoaDon {
    private NhanVien nv; // Kế thừa has a từ  class NhanVien
    private KhachHang makh; // Kế thừa has a từ  class KhachHang
    private String mahoadon, ngaylaphd;
    private int tongtien;
    public HoaDon(){
        tongtien=0;
    }
    public HoaDon(String mahoadon,NhanVien ma_nv, KhachHang makh, String ngaylaphd, int tongtien){
        this.mahoadon=mahoadon;
        this.nv=ma_nv;
        this.makh=makh;
        this.ngaylaphd=ngaylaphd;
        this.tongtien=tongtien;
        
    }
    public HoaDon(HoaDon x){
        this.mahoadon=x.mahoadon;
        this.nv=x.nv;
        this.makh=x.makh;
        this.ngaylaphd=x.ngaylaphd;
        this.tongtien=x.tongtien;
    }

   public String getMaHoaDon() {
       return mahoadon;
   }

    public int getTongTien() {
        return tongtien;
    }

    public void setMaHoaDon(String mahoadon) {
        this.mahoadon = mahoadon;
    }
    public void setNhanVien(NhanVien nv) {
        this.nv = nv;
    }
    // public void setTongTien(int tongtien) {
    //     this.tongtien = tongtien;
    // }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma hoa don: ");
        mahoadon=sc.nextLine();
        this.makh = new KhachHang();
        makh.nhap();
        System.out.print("Ngay lap hoa don: ");
        ngaylaphd=sc.nextLine();
        System.out.print("Tong tien: ");
        tongtien=sc.nextInt();
    }
    public void tieude(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
    }
    public void xuat(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10d\n",mahoadon,nv.getMaNV(),makh.getMaKH(),ngaylaphd,tongtien);
    }
//    public static void main(String[] args) {
//        NhanVien nv1 = new NhanVien();
//        nv1.setMaNV("NV001");
//        NhanVien nv2 = new NhanVien();
//        nv2.setMaNV("NV002");
//        KhachHang kh1 = new KhachHang();
//        kh1.setMaKH("KH01");
//        HoaDon hd = new HoaDon("HD001", nv1, kh1, "2024-06-01", 500000);
//        hd.tieude();
//        hd.xuat();
//    }
}
