import java.util.Scanner;

public class DSPNH{
  private int n; //so luong phieu nhan hang
  private PhieuNhanHang ds_pnh [] = new PhieuNhanHang[1];// mang ds phieu nhan hang
  public DSPNH(){}
  public DSPNH(int n, PhieuNhanHang [] ds_pnh){
    this.n = n;
    this.ds_pnh = ds_pnh;
  }
  public DSPNH(DSPNH other){
        this.n = other.n;
        this.ds_pnh = new PhieuNhanHang[n];
        for (int i = 0; i < ds_pnh.length; i++){
            this.ds_pnh[i] = new PhieuNhanHang(other.ds_pnh[i]);
        }
  }
//
public void nhap(DSNV ds_nv, DSNCC ds_ncc){
    Scanner sc = new Scanner(System.in);
    System.out.print("Nhap so luong phieu nhan hang: ");
    n = sc.nextInt();
    sc.nextLine(); // doc bo dong thua
    ds_pnh = new PhieuNhanHang [n]; // mang chua phieu nhan hang
    for(int i = 0; i < n; i++){
        System.out.println("Nhap thong tin phieu nhan hang thu " + (i+1) + ":");
        ds_pnh[i] = new PhieuNhanHang(); //tao ra doi tuong phieu nhan hang
        ds_pnh[i].nhap(ds_nv, ds_ncc); // GỌI HÀM NHẬP MỚI VỚI ĐỐI SỐ
    }
}
  public void xuat(){
    System.out.println("-------------DANH SÁCH PHIẾU NHẬN HÀNG------------");
    System.out.printf("%-20s %-30s %-20s %-15s %-20s\n","Mã Phiếu Nhận Hàng","Mã Nhân Viên","Mã Nhà Cung Cấp","Ngày Nhận","Tổng Tiền");
    for(int i = 0; i < n; i++){
      ds_pnh[i].xuat();
    }
  }
  // public static void main(String[] args) {
  //   DSPNH ds = new DSPNH();
  //   ds.nhap();
  //   ds.xuat();
  // }
}
