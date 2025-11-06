import java.util.Arrays;
import java.util.Scanner;

public class DSHD {
    private int n;
    public HoaDon[] dshd;
    public DSHD(){
        dshd = new HoaDon[100];
        n = 0;
    }
    public DSHD(int n,HoaDon[] dshd){
        this.n = n;
        this.dshd = dshd;
    }
   public DSHD(DSHD other) {
        this.n = other.n; 
        this.dshd = new HoaDon[other.dshd.length];
        for (int i = 0; i < this.n; i++) { 
            this.dshd[i] = new HoaDon(other.dshd[i]);
        }
    }
    public HoaDon timHDtheoMa(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                return dshd[i]; // Trả về hóa đơn tìm thấy
            }
        }
        return null; // Không tìm thấy
    }
    public int timViTriHD(String mahd) {
        for (int i = 0; i < n; i++) {
            if (dshd[i] != null && dshd[i].getMaHoaDon().equalsIgnoreCase(mahd)) {
                return i; // Trả về vị trí (index)
            }
        }
        return -1; 
    }
    public boolean maDuyNhat(String mahd){
        for(int i = 0; i < n; i++)
            if(dshd[i].getMaHoaDon().equals(mahd))
                return false;
        return true;
    }
    public void themHoaDon(HoaDon hd) {
        if (maDuyNhat(hd.getMaHoaDon())) {
            dshd[n] = hd;
            n++; // Tăng số lượng
            System.out.println("-> Đã thêm Hóa Đơn " + hd.getMaHoaDon() + " vào danh sách.");
        } else {
            System.out.println("❌ Lỗi: Mã hóa đơn '" + hd.getMaHoaDon() + "' đã tồn tại. Không thể thêm.");
        }
    }
    public void xoaHoaDon() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã hóa đơn cần xóa: ");
        String mahd = sc.nextLine();
        int vitrixoa = timViTriHD(mahd);

        if (vitrixoa == -1) {
            System.out.println("❌ Không tìm thấy hóa đơn có mã: " + mahd);
            return;
        }
        for (int i = vitrixoa; i < n - 1; i++) {
            dshd[i] = dshd[i + 1];
        }
        dshd[n - 1] = null;
        n--;
        System.out.println("Đã xóa hóa đơn " + mahd);
    }
    public void xuat(){
        System.out.printf("%-10s %-10s %-10s %-15s %-10s\n","Ma HD","Ma NV","Ma KH","Ngay lap HD","Tong tien");
        for(int i = 0; i < n; i++){
            dshd[i].xuat();
        }
    }
//    public static void main(String[] args) {
//       // 1. Tạo nhân viên SẼ LẬP các hóa đơn
//        NhanVien nv1 = new NhanVien();
//        // (Bạn cũng nên có hàm nhap() cho NhanVien)
//        nv1.nhap();
//
//        // 2. Tạo danh sách
//        DSHD ds = new DSHD();
//
//        // 3. Gọi hàm nhập và TRUYỀN nhân viên 'nv1' vào
//        ds.nhap(nv1);
//
//        // 4. Xuất danh sách
//        ds.xuat();
//    }
}
