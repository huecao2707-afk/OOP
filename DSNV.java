import java.util.Scanner;
public class DSNV {
        private int n;
        public NhanVien[] dsnv = new NhanVien[1];
        public DSNV(){
            n = 0;
            dsnv = new NhanVien[0];
        }
        public DSNV(int n, NhanVien[] dsnv){
            this.n = n;
            this.dsnv = dsnv;
        }
        public DSNV(DSNV other){
            this.n = other.n;
            this.dsnv = new NhanVien[n];
            for (int i = 0; i < dsnv.length; i++){
                this.dsnv[i] = new NhanVien(other.dsnv[i]);
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
            System.out.printf("%-10s %-15s %-10s %-10s\n","Ma NV","Ho","Ten","Luong thang");
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
