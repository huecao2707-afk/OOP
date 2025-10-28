import java.util.Scanner;

public class DSCTPNH{
  private int n; //so luong chi tiet phieu nhan hang
  private ChiTietPNH dsctpnh [] = new ChiTietPNH[1];// mang ds chi tiet phieu nhan hang
  public DSCTPNH(){}
  public DSCTPNH(int n, ChiTietPNH [] dsctpnh){
    this.n = n;
    this.dsctpnh = dsctpnh;
  }
    public DSCTPNH(DSCTPNH other){
        this.n = other.n;
        this.dsctpnh = new ChiTietPNH[n];
        for (int i = 0; i < dsctpnh.length; i++){
            this.dsctpnh[i] = new ChiTietPNH(other.dsctpnh[i]);
        }
    }
//  public void nhap(){
//    Scanner sc = new Scanner(System.in);
//    System.out.println("Nhap so luong chi tiet phieu nhan hang: ");
//    n = sc.nextInt();
//    sc.nextLine();
//    dsctpnh = new DSCTPNH [n]; // mang chua phieu nhan hang
//    for(int i = 0; i < n; i++){
//      dsctpnh[i] = new DSCTPNH(); //tao ra doi tuong phieu nhan hang
//      dsctpnh[i].nhap(); // hàm nhập gọi từ class PhieuNhanHang
//    }
//  }
}
