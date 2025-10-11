public class DSPHIEUNHANHANG{
  private int n; //so luong phieu nhan hang
  private PHIEUNHANHANG ds_pnh [] = new PHIEUNHANHANG[1];// mang ds phieu nhan hang
  public DSPHIEUNHANHANG(){}
  public DSPHIEUNHANHANG(int n, PHIEUNHANHANG [] ds_pnh){
    this.n = n;
    this.ds_pnh = ds_pnh;
  }
  public void nhap(){
    Scanner sc = new Scanner(System.in);
    System.out.println("Nhap so luong phieu nhan hang: ");
    n = sc.nextInt();
    sc.nextLine();
    ds_pnh = new PHIEUNHANHANG [n]; // mang chua phieu nhan hang
    for(int i = 0; i < n; i++){
      ds_pnh[i] = new PHIEUNHANHANG(); //tao ra doi tuong phieu nhan hang
      ds_pnh[i].nhap(); // hàm nhập gọi từ class PHIEUNHANHANG 
    }
  }
}
