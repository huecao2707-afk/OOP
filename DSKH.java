import java.util.Scanner;
public class DSKH {
        private int n;
        private KhachHang[] dskh = new KhachHang[1];
        public DSKH(){
        }
        public DSKH(int n, KhachHang []dskh){
            this.n = n;
            this.dskh = dskh;
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap so luong khach hang n =  ");
            n = sc.nextInt();
            dskh = new KhachHang[n];
            for(int i = 0; i < n; i++){
                dskh[i] = new KhachHang();
                dskh[i].nhap();
            }
        }
        public void xuat(){
            System.out.println("-------------DANH SACH KHACH HANG-------------");
            for(int i = 0; i < n; i++){
                dskh[i].xuat();
            }
        }
}
