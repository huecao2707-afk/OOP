import java.util.Scanner;
public class DSNCC {
        public NhaCungCap[] ds_ncc = new NhaCungCap[1];
        private int n;
        public NhaCungCap[] dsncc = new NhaCungCap[1];
        public DSNCC(){
            n = 0;
            ds_ncc = new NhaCungCap[0];
        }
        public DSNCC(NhaCungCap[] dsncc, int n){
            this.n = n;
            this.dsncc = dsncc;
        }
        public DSNCC(DSNCC other){
            this.n = other.n;
            this.ds_ncc = new NhaCungCap[n];
            for (int i = 0; i < ds_ncc.length; i++){
                this.ds_ncc[i] = new NhaCungCap(other.ds_ncc[i]);
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

        public void xuat(){
            System.out.println("-------------DANH SÁCH NHÀ CUNG CẤP-------------");
            System.out.printf("%-20s %-20s %-20s\n","Mã Nhà Cung Cấp","Tên Nhà Cung Cấp","Địa Chỉ");
            for (int i = 0; i < n; i++){
                dsncc[i].xuat();
            }
        }
        public NhaCungCap timKiemTheoMa(String ma_ncc){
            for (int i = 0; i < n; i++){
                if (dsncc[i].getMaNCC().equalsIgnoreCase(ma_ncc)){
                    return dsncc[i];
                }
            }
            return null; // Không tìm thấy
        }
        public static void main(String[] args) {
            DSNCC ds = new DSNCC();
            ds.nhap();
            ds.xuat();
        }
}
