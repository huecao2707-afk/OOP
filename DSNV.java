import java.util.Scanner;
public class DSNV {
        public NhanVien[] ds_nv = new NhanVien[1];
        private int n;
        public NhanVien[] dsnv = new NhanVien[1];
        public DSNV(){
            n = 0;
            ds_nv = new NhanVien[0];
        }
        public DSNV(int n, NhanVien[] ds_nv) {
            this.n = n;
            this.ds_nv = ds_nv;
        }
        public DSNV(DSNV other){
            this.n = other.n;
            this.ds_nv = new NhanVien[n];
            for (int i = 0; i < ds_nv.length; i++){
                this.ds_nv[i] = new NhanVien(other.ds_nv[i]);
            }
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap so luong nhan vien n = ");
            n = sc.nextInt();
            dsnv = new NhanVien[n];
            for (int i = 0; i < n; i++){
                System.out.println("Nhap thong tin nhan vien thu " + (i + 1) + ":");
                dsnv[i] = new NhanVien();
                dsnv[i].nhap();
            }
        }
        public void tieude(){
            System.out.println("-------------DANH SACH NHAN VIEN-------------");
            System.out.printf("%-15s %-25s %-10s\n","Mã Nhân Viên","Họ và Tên","Lương Tháng");
        }
        public void xuat(){
            tieude();
            for (int i = 0; i < n; i++){
                dsnv[i].xuat();
        
            }
        }

        public static void main(String[] args) {
            DSNV ds = new DSNV();
            ds.nhap();
            ds.xuat();
        }
}
