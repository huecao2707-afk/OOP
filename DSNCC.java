import java.util.Scanner;
public class DSNCC {
        private int n;
        public NhaCungCap[] dsncc = new NhaCungCap[1];
        public DSNCC(){
            n = 0;
            dsncc = new NhaCungCap[0];
        }
        public DSNCC(NhaCungCap[] dsncc, int n){
            this.n = n;
            this.dsncc = dsncc;
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.dsncc = new NhaCungCap[n];
            for (int i = 0; i < dsncc.length; i++){
                this.dsncc[i] = new NhaCungCap(other.dsncc[i]);
            }
        }
        public void nhap(){
            Scanner sc = new Scanner(System.in);
            System.out.print("Nhap so luong nha cung cap n = ");
            n = sc.nextInt();
            dsncc = new NhaCungCap[n];
            for (int i = 0; i < n; i++){
                System.out.println("Nhap thong tin nha cung cap thu " + (i + 1) + ":");
                dsncc[i] = new NhaCungCap();
                dsncc[i].nhap();
            }
        }
        public void tieude(){
            System.out.println("-------------DANH SACH NHA CUNG CAP-------------");
            System.out.printf("%-10s %-20s %-30s\n","Ma NCC","Ten NCC","Dia chi");
        }
        public void xuat(){
            tieude();
            for (int i = 0; i < n; i++){
                dsncc[i].xuat();
            }
        }
        public static void main(String[] args) {
            DSNCC ds = new DSNCC();
            ds.nhap();
            ds.xuat();
        }
}
