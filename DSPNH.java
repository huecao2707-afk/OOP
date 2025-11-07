import java.util.Scanner;

public class DSPNH{
  private int n; //so luong phieu nhan hang
  private PhieuNhanHang dspnh [] = new PhieuNhanHang[1];// mang ds phieu nhan hang
  public DSPNH(){}
  public DSPNH(int n, PhieuNhanHang [] dspnh){
    this.n = n;
    this.dspnh = dspnh;
  }
  public DSPNH(DSPNH other){
        this.n = other.n;
        this.dspnh = new PhieuNhanHang[n];
        for (int i = 0; i < dspnh.length; i++){
            this.dspnh[i] = new PhieuNhanHang(other.dspnh[i]);
        }
  }
////
//public void nhap(DSNV ds_nv, DSNCC ds_ncc){
//    Scanner sc = new Scanner(System.in);
//    System.out.print("Nhap so luong phieu nhan hang: ");
//    n = sc.nextInt();
//    sc.nextLine(); // doc bo dong thua
//    dspnh = new PhieuNhanHang [n]; // mang chua phieu nhan hang
//    for(int i = 0; i < n; i++){
//        System.out.println("Nhap thong tin phieu nhan hang thu " + (i+1) + ":");
//        dspnh[i] = new PhieuNhanHang(); //tao ra doi tuong phieu nhan hang
//        dspnh[i].nhap(ds_nv, ds_ncc); // GỌI HÀM NHẬP MỚI VỚI ĐỐI SỐ
//    }
//}
//  public void xuat(){
//    System.out.println("-------------DANH SÁCH PHIẾU NHẬN HÀNG------------");
//    System.out.printf("%-20s %-30s %-20s %-15s %-20s\n","Mã Phiếu Nhận Hàng","Mã Nhân Viên","Mã Nhà Cung Cấp","Ngày Nhận","Tổng Tiền");
//    for(int i = 0; i < n; i++){
//      dspnh[i].xuat();
//    }
//  }
}
