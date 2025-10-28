import java.util.Scanner;
public class ChiTietPNH{ // Chi tiet phieu nhan hang
  private String ma_pnh;// ma phieu nhan hang
  private String ma_sp;
  private int don_gia, so_luong, thanh_tien; 

  public ChiTietPNH(){}
  public ChiTietPNH(String ma_pnh, String ma_sp, int don_gia, int so_luong, int thanh_tien){
    this.ma_pnh = ma_pnh;
    this.ma_sp = ma_sp;
    this.don_gia = don_gia;
    this.so_luong = so_luong;
    this.thanh_tien = thanh_tien;
  }
   public ChiTietPNH(ChiTietPNH x){
    ma_pnh = x.ma_pnh;
    ma_sp = x.ma_sp;
    don_gia = x.don_gia;
    so_luong = x.so_luong;
    thanh_tien = x.thanh_tien;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap ma phieu nhan hang: ");
    ma_pnh = sc.nextLine();
    System.out.print("Nhap ma san pham: ");
    ma_sp = sc.nextLine();
    System.out.print("Nhap don gia: ");
    don_gia = sc.nextInt();
    sc.nextLine();
    System.out.print("Nhap so luong: ");
    so_luong = sc.nextInt();
    sc.nextLine();
    thanh_tien = don_gia * so_luong;
  }
  public void xuat(){
    System.out.printf("%-15s %-15s %-15d %-15d %-15d\n", ma_pnh, ma_sp, don_gia, so_luong, thanh_tien);
  }
}
