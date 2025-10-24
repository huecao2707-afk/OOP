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
<<<<<<< HEAD
        this.n = other.n;
        this.ds_pnh = new PhieuNhanHang[n];
        for (int i = 0; i < ds_pnh.length; i++){
            this.ds_pnh[i] = new PhieuNhanHang(other.ds_pnh[i]);
        }
  }
//  public void nhap(){
//    Scanner sc = new Scanner(System.in);
//    System.out.println("Nhap so luong phieu nhan hang: ");
//    n = sc.nextInt();
//    sc.nextLine();
//    ds_pnh = new PhieuNhanHang [n]; // mang chua phieu nhan hang
//    for(int i = 0; i < n; i++){
//      ds_pnh[i] = new PhieuNhanHang(); //tao ra doi tuong phieu nhan hang
//      ds_pnh[i].nhap(); // hàm nhập gọi từ class PhieuNhanHang
//    }
//  }
=======
    this.n = other.n;
    this.ds_pnh = new PhieuNhanHang[n];
    for(int i = 0; i < n; i++){
      this.ds_pnh[i] = new PhieuNhanHang(other.ds_pnh[i]);
    }
  }

  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap so luong phieu nhan hang n = ");
    n = sc.nextInt();
    ds_pnh = new PhieuNhanHang[n];
    for(int i = 0; i < n; i++){
      System.out.println("Nhap thong tin phieu nhan hang thu " + (i + 1) + ":");
      ds_pnh[i] = new PhieuNhanHang();
      ds_pnh[i].nhap();
    }
  }
  public void tieude(){
    System.out.println("-------------DANH SACH PHIEU NHAN HANG-------------");
    System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma PNH","Ma NV","Ma NCC","Ngay","Tong tien");
  }
  public void xuat(){
    tieude();
    for(int i = 0; i < n; i++){
      ds_pnh[i].xuat();
    }
  }
  // public static void main(String[] args) {
  //   DSPNH ds = new DSPNH();
  //   ds.nhap();
  //   ds.xuat();
  // }
>>>>>>> 04989e9b90a73194d809521c79870a1a0dfe2c21
}
