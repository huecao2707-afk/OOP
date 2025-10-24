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
        public DSKH(DSKH other) {
            this.n = other.n;
            this.dskh = new KhachHang[this.n];
            for (int i = 0; i < this.n; i++) {
                this.dskh[i] = new KhachHang(other.dskh[i]);
            }
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
        public void tieude(){
            System.out.println("-------------DANH SACH KHACH HANG-------------");
            System.out.printf("%-10s %-20s %-15s %-15s %-15s\n","Ma KH","Ho ten","Gioi tinh","Dia chi","So dien thoai");
        }
        public void xuat(){
            tieude();
            for(int i = 0; i < n; i++){
                dskh[i].xuat();
            }
        }
}
