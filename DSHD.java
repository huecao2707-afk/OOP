import java.util.Scanner;
public class DSHD {
    private int n;
    public HoaDon[] dshd = new HoaDon[1];
    public DSHD(){
    }
    public DSHD(HoaDon[] dshd, int n){
        this.n = n;
        this.dshd = dshd;
    }
    public DSHD(DSHD other) {
        this.n = other.n;
        this.dshd = new HoaDon[this.n];
        for (int i = 0; i < this.n; i++) {
            this.dshd[i] = new HoaDon(other.dshd[i]);
        }
    }
    public void nhap(NhanVien nv_lap_don) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhap so luong hoa don: ");
        n = sc.nextInt();
        sc.nextLine();
        
        // Khởi tạo mảng với đúng số lượng 'n'
        dshd = new HoaDon[n]; 
        
        for (int i = 0; i < n; i++) {
            System.out.println("----------HOA DON " + (i + 1) + "----------");
            dshd[i] = new HoaDon(); // Tạo Hóa đơn
            dshd[i].nhap(); // Gọi hàm nhập của Hóa đơn (để nhập Mã HD, Mã KH...)

            // Gán nhân viên đang lập HĐ này cho Hóa đơn
            // (Bạn cần có hàm setNhanVien trong class HoaDon)
            dshd[i].setNhanVien(nv_lap_don); 
        }
    }
    public void xuat(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
        for(int i = 0; i < n; i++){
            dshd[i].xuat();
        }
    }
    public static void main(String[] args) {
       // 1. Tạo nhân viên SẼ LẬP các hóa đơn
        NhanVien nv1 = new NhanVien();
        // (Bạn cũng nên có hàm nhap() cho NhanVien)
        nv1.nhap(); 

        // 2. Tạo danh sách
        DSHD ds = new DSHD();
        
        // 3. Gọi hàm nhập và TRUYỀN nhân viên 'nv1' vào
        ds.nhap(nv1);

        // 4. Xuất danh sách
        ds.xuat();
    }
}
