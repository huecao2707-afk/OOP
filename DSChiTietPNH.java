public class DSChiTietPNH{
  private int n; //so luong phieu nhan hang
  private PhieuNhanHang ds_pnh [] = new PhieuNhanHang[1];// mang ds phieu nhan hang
  public DSPNH(){}
  public DSPNH(int n, PhieuNhanHang [] ds_pnh){
    this.n = n;
    this.ds_pnh = ds_pnh;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap so luong phieu nhan hang: ");
    n = sc.nextInt();
    sc.nextLine();
    ds_pnh = new PhieuNhanHang [n]; // mang chua phieu nhan hang
    for(int i = 0; i < n; i++){
      ds_pnh[i] = new PhieuNhanHang(); //tao ra doi tuong phieu nhan hang
      ds_pnh[i].nhap(); // hàm nhập gọi từ class PhieuNhanHang 
    }
  }
}
