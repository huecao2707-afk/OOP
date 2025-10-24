import java.util.Scanner;
public class DSKH {
        private int n;
        private KhachHang[] ds_kh = new KhachHang[1];
        public DSKH(){
        }
        public DSKH(int n, KhachHang []dskh){
            this.n = n;
            this.ds_kh = ds_kh;
        }
        public DSKH(DSKH other){
            this.n = other.n;
            this.ds_kh = new KhachHang[n];
            for (int i = 0; i < ds_kh.length; i++){
                this.ds_kh[i] = new KhachHang(other.ds_kh[i]);
            }
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.println("Nhap so luong khach hang n =  ");
            n = sc.nextInt();
            ds_kh = new KhachHang[n];
            for(int i = 0; i < n; i++){
                ds_kh[i] = new KhachHang();
                ds_kh[i].nhap();
            }
        }
        public void tieude(){
            System.out.println("-------------DANH SACH KHACH HANG-------------");
            System.out.printf("%-10s %-20s %-15s %-15s %-15s\n","Ma KH","Ho ten","Gioi tinh","Dia chi","So dien thoai");
        }
        public void xuat(){
            tieude();
            for(int i = 0; i < n; i++){
                ds_kh[i].xuat();
            }
        }
}
