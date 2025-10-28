import java.util.Scanner;

public class DSCTPNH{
  private int n; //so luong chi tiet phieu nhan hang
  private ChiTietPNH ds_ctpnh [] = new ChiTietPNH[1];// mang ds chi tiet phieu nhan hang
  public DSCTPNH(){}
  public DSCTPNH(int n, ChiTietPNH [] ds_ctpnh){
    this.n = n;
    this.ds_ctpnh = ds_ctpnh;
  }
    public DSCTPNH(DSCTPNH other){
        this.n = other.n;
        this.ds_ctpnh = new ChiTietPNH[n];
        for (int i = 0; i < ds_ctpnh.length; i++){
            this.ds_ctpnh[i] = new ChiTietPNH(other.ds_ctpnh[i]);
        }
    }
//  public void nhap(){
//    Scanner sc = new Scanner(System.in);
//    System.out.println("Nhap so luong chi tiet phieu nhan hang: ");
//    n = sc.nextInt();
//    sc.nextLine();
//    ds_ctpnh = new DSCTPNH [n]; // mang chua phieu nhan hang
//    for(int i = 0; i < n; i++){
//      ds_ctpnh[i] = new DSCTPNH(); //tao ra doi tuong phieu nhan hang
//      ds_ctpnh[i].nhap(); // hàm nhập gọi từ class PhieuNhanHang
//    }
//  }
}
