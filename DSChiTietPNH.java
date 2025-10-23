public class DSChiTietPNH{
  private int n; //so luong chi tiet phieu nhan hang
  private ChiTietPNH ds_chi_tiet_pnh [] = new ChiTietPNH[1];// mang ds chi tiet phieu nhan hang
  public DSChiTietPNH(){}
  public DSChiTietPNH(int n, ChiTietPNH [] ds_chi_tiet_pnh){
    this.n = n;
    this.ds_chi_tiet_pnh = ds_chi_tiet_pnh;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap so luong chi tiet phieu nhan hang: ");
    n = sc.nextInt();
    sc.nextLine();
    ds_chi_tiet_pnh = new DSChiTietPNH [n]; // mang chua phieu nhan hang
    for(int i = 0; i < n; i++){
      ds_chi_tiet_pnh[i] = new DSChiTietPNH(); //tao ra doi tuong phieu nhan hang
      ds_chi_tiet_pnh[i].nhap(); // hàm nhập gọi từ class PhieuNhanHang 
    }
  }
}
