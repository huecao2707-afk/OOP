import java.util.Scanner;

public class HoaDon {
    private NhanVien nv; // Kế thừa has a từ  class NhanVien
    private KhachHang ma_kh; // Kế thừa has a từ  class KhachHang
    private String ma_hoa_don, ngay_lap_hd;
    private int tong_tien;
    public HoaDon(){
        tong_tien=0;
    }
    public HoaDon(String ma_hoa_don,NhanVien ma_nv, KhachHang ma_kh, String ngay_lap_hd, int tong_tien){
        this.ma_hoa_don=ma_hoa_don;
        this.nv=ma_nv;
        this.ma_kh=ma_kh;
        this.ngay_lap_hd=ngay_lap_hd;
        this.tong_tien=tong_tien;
        
    }
    public HoaDon(HoaDon x){
        this.ma_hoa_don=x.ma_hoa_don;
        this.nv=x.nv;
        this.ma_kh=x.ma_kh;
        this.ngay_lap_hd=x.ngay_lap_hd;
        this.tong_tien=x.tong_tien;
    }

   public String getMaHoaDon() {
       return ma_hoa_don;
   }

    public int getTongTien() {
        return tong_tien;
    }

    public void setMaHoaDon(String ma_hoa_don) {
        this.ma_hoa_don = ma_hoa_don;
    }
    public void setNhanVien(NhanVien nv) {
        this.nv = nv;
    }
    // public void setTongTien(int tong_tien) {
    //     this.tong_tien = tong_tien;
    // }
    public void nhap(){
        Scanner sc=new Scanner(System.in);
        System.out.print("Ma hoa don: ");
        ma_hoa_don=sc.nextLine();
        this.ma_kh = new KhachHang();
        ma_kh.nhap();
        System.out.print("Ngay lap hoa don: ");
        ngay_lap_hd=sc.nextLine();
        System.out.print("Tong tien: ");
        tong_tien=sc.nextInt();
    }
    public void tieude(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
    }
    public void xuat(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10d\n",ma_hoa_don,nv.getMaNV(),ma_kh.getMaKH(),ngay_lap_hd,tong_tien);
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
