import java.util.Scanner;

public class DSCTHD { 
    int m;
    public ChiTietHoaDon[] dscthd = new ChiTietHoaDon[1];
    public DSCTHD(){
        m = 0;
        dscthd = new ChiTietHoaDon[0];
    }
    public DSCTHD(int m, ChiTietHoaDon[] dscthd){
        this.m = m;
        this.dscthd = dscthd;
    }
    public DSCTHD(DSCTHD other){
        this.m = other.m;
        this.dscthd = new ChiTietHoaDon[m];
        for (int i = 0; i < dscthd.length; i++){
            this.dscthd[i] = new ChiTietHoaDon(other.dscthd[i]);
        }
    }
    public void them() {
        Scanner sc = new Scanner(System.in);
        System.out.println("=== THÊM CHI TIẾT HÓA ĐƠN ===");
        ChiTietHoaDon cthd = new ChiTietHoaDon();
        cthd.nhap(); // giả sử ChiTietHoaDon có hàm nhập()

        // Tăng kích thước mảng
        ChiTietHoaDon[] temp = new ChiTietHoaDon[m + 1];
        for (int i = 0; i < m; i++) {
            temp[i] = dscthd[i];
        }
        temp[m] = cthd;
        dscthd = temp;
        m++;
        System.out.println(">> Đã thêm chi tiết hóa đơn mới thành công!");
    }
    public void sua() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm cần sửa: ");
        String ma = sc.nextLine();

        int index = -1;
        for (int i = 0; i < m; i++) {
            if (dscthd[i].getMaSP().equalsIgnoreCase(ma)) { // giả sử có getMaSP()
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(">> Không tìm thấy mã sản phẩm cần sửa!");
            return;
        }

        System.out.println("=== NHẬP LẠI THÔNG TIN MỚI ===");
        dscthd[index].nhap();
        System.out.println(">> Đã cập nhật chi tiết hóa đơn thành công!");
    }
    public void xoa() {
        Scanner sc = new Scanner(System.in);
        System.out.print("Nhập mã sản phẩm cần xóa: ");
        String ma = sc.nextLine();

        int index = -1;
        for (int i = 0; i < m; i++) {
            if (dscthd[i].getMaSP().equalsIgnoreCase(ma)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println(">> Không tìm thấy mã sản phẩm cần xóa!");
            return;
        }

        // Tạo mảng mới bỏ phần tử bị xóa
        ChiTietHoaDon[] temp = new ChiTietHoaDon[m - 1];
        int j = 0;
        for (int i = 0; i < m; i++) {
            if (i != index) {
                temp[j++] = dscthd[i];
            }
        }
        dscthd = temp;
        m--;
        System.out.println(">> Đã xóa chi tiết hóa đơn thành công!");
    }
}
