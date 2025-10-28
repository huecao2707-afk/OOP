import java.util.Scanner;
public class ChiTietPNH{ // Chi tiet phieu nhan hang
  private String mapnh;// ma phieu nhan hang
  private String masp;
  private int dongia, soluong, thanhtien; 

  public ChiTietPNH(){}
  public ChiTietPNH(String mapnh, String masp, int dongia, int soluong, int thanhtien){
    this.mapnh = mapnh;
    this.masp = masp;
    this.dongia = dongia;
    this.soluong = soluong;
    this.thanhtien = thanhtien;
  }
   public ChiTietPNH(ChiTietPNH x){
    mapnh = x.mapnh;
    masp = x.masp;
    dongia = x.dongia;
    soluong = x.soluong;
    thanhtien = x.thanhtien;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap ma phieu nhan hang: ");
    mapnh = sc.nextLine();
    System.out.print("Nhap ma san pham: ");
    masp = sc.nextLine();
    System.out.print("Nhap don gia: ");
    dongia = sc.nextInt();
    sc.nextLine();
    System.out.print("Nhap so luong: ");
    soluong = sc.nextInt();
    sc.nextLine();
    thanhtien = dongia * soluong;
  }
  public void xuat(){
    System.out.printf("%-15s %-15s %-15d %-15d %-15d\n", mapnh, masp, dongia, soluong, thanhtien);
  }
}
